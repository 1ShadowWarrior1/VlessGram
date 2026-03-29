package org.telegram.vless;

/**
 * JNI bridge for libvless core (StartCore/StopCore).
 *
 * Native code should dlopen the downloaded libvless.so and call exported symbols.
 */
public final class VlessCoreBridge {
    static {
        // Provided by our small JNI wrapper (vless_core_jni).
        System.loadLibrary("vless_core_jni");
    }

    private VlessCoreBridge() {
    }

    public static void loadCoreLibrary(String soPath) {
        if (soPath == null || soPath.isEmpty()) {
            throw new IllegalArgumentException("soPath is empty");
        }
        nativeLoadCore(soPath);
    }

    /**
     * Starts core with provided config JSON.
     * Returns empty string or error string (depending on lib implementation).
     */
    public static String start(String configJson) {
        if (configJson == null) configJson = "";
        return nativeStartCore(configJson);
    }

    public static void stop() {
        nativeStopCore();
    }

    private static native void nativeLoadCore(String soPath);

    private static native String nativeStartCore(String configJson);

    private static native void nativeStopCore();
}

