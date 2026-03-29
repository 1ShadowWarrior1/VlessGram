package org.telegram.vless;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Manages extraction and start/stop of libvless core.
 *
 * libvless.so is bundled inside the APK at:
 *   assets/libvless/libvless.so   (arm64-v8a)
 *
 * On first launch (or after an app update) the file is extracted to
 * getFilesDir()/vless/libvless.so and loaded from there via dlopen.
 */
public final class VlessCoreManager {
    private VlessCoreManager() {
    }

    private static final int MIN_SO_SIZE_BYTES = 1024;
    private static final String ASSET_PATH = "libvless/libvless.so";

    public static int allocateFreePort() {
        try (java.net.ServerSocket socket = new java.net.ServerSocket(0)) {
            socket.setReuseAddress(true);
            return socket.getLocalPort();
        } catch (Exception e) {
            return 20808;
        }
    }

    /**
     * Returns the extracted libvless.so, re-extracting from assets when the app
     * version has changed or the file is missing/corrupt.
     */
    public static File ensureExtractedSo(Context context) throws Exception {
        File dir = new File(context.getFilesDir(), "vless");
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
        File so = new File(dir, "libvless.so");
        File verFile = new File(dir, "libvless.ver");

        long appVersion = getVersionCode(context);
        if (isValidElfSo(so) && isCacheValid(verFile, appVersion)) {
            return so;
        }

        // Extract fresh copy from bundled assets.
        if (so.exists()) {
            //noinspection ResultOfMethodCallIgnored
            so.delete();
        }
        extractAsset(context, ASSET_PATH, so);

        if (!isValidElfSo(so)) {
            //noinspection ResultOfMethodCallIgnored
            so.delete();
            throw new IllegalStateException(
                    "libvless.so bundled in assets is not a valid ELF. " +
                    "Make sure you placed the correct arm64-v8a build at " +
                    "TMessagesProj/src/main/assets/libvless/libvless.so");
        }

        // Write version marker so we don't re-extract on every launch.
        try (FileOutputStream ver = new FileOutputStream(verFile)) {
            ver.write(String.valueOf(appVersion).getBytes(StandardCharsets.US_ASCII));
        }
        return so;
    }

    private static volatile boolean coreRunning = false;

    public static void startCore(Context context, String configJson, int localPort) throws Exception {
        File so = ensureExtractedSo(context);
        try {
            VlessCoreBridge.loadCoreLibrary(so.getAbsolutePath());
        } catch (Throwable firstLoadError) {
            // Extracted file may be corrupt — wipe and retry once.
            //noinspection ResultOfMethodCallIgnored
            so.delete();
            so = ensureExtractedSo(context);
            VlessCoreBridge.loadCoreLibrary(so.getAbsolutePath());
        }

        String err = VlessCoreBridge.start(configJson);
        if (err != null && !err.trim().isEmpty()) {
            throw new IllegalStateException("Core error: " + err.trim());
        }

        // Wait up to ~10 seconds for the SOCKS port to become available.
        if (!waitForPort("127.0.0.1", localPort, 50, 200)) {
            throw new IllegalStateException("Timeout waiting for local SOCKS port");
        }
        
        coreRunning = true;
    }

    public static boolean isCoreRunning() {
        return coreRunning;
    }

    public static void stopCore() {
        try {
            VlessCoreBridge.stop();
        } catch (Throwable ignored) {
        }
        coreRunning = false;
    }

    // ── private helpers ──────────────────────────────────────────────────────

    private static void extractAsset(Context context, String assetPath, File dest) throws Exception {
        try (InputStream in = context.getAssets().open(assetPath);
             FileOutputStream out = new FileOutputStream(dest)) {
            byte[] buf = new byte[65536];
            int read;
            while ((read = in.read(buf)) >= 0) {
                out.write(buf, 0, read);
            }
            out.flush();
        }
    }

    private static boolean isCacheValid(File verFile, long expectedVersion) {
        if (!verFile.exists()) return false;
        try (FileInputStream in = new FileInputStream(verFile)) {
            byte[] buf = new byte[32];
            int n = in.read(buf);
            if (n <= 0) return false;
            long stored = Long.parseLong(
                    new String(buf, 0, n, StandardCharsets.US_ASCII).trim());
            return stored == expectedVersion;
        } catch (Exception e) {
            return false;
        }
    }

    private static long getVersionCode(Context context) {
        try {
            PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                return info.getLongVersionCode();
            } else {
                //noinspection deprecation
                return info.versionCode;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    private static boolean waitForPort(String host, int port, int attempts, long sleepMs) {
        for (int i = 0; i < attempts; i++) {
            if (isPortOpen(host, port, 200)) return true;
            try {
                Thread.sleep(sleepMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    private static boolean isPortOpen(String host, int port, int timeoutMs) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeoutMs);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private static boolean isValidElfSo(File file) {
        if (file == null || !file.exists() || file.length() < MIN_SO_SIZE_BYTES) {
            return false;
        }
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] ident = new byte[5];
            int read = in.read(ident);
            if (read < ident.length) return false;
            return (ident[0] == 0x7F)
                    && (ident[1] == 'E')
                    && (ident[2] == 'L')
                    && (ident[3] == 'F')
                    && (ident[4] == 1 || ident[4] == 2);
        } catch (Exception e) {
            return false;
        }
    }
}
