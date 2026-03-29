/*
 * VlessGram Background Permission Helper
 * Запрашивает разрешение на работу в фоне (игнорирование оптимизации батареи)
 * для стабильной работы VLESS/VPN подключения.
 */

package org.telegram.messenger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;

/**
 * Helper class for requesting background execution permission (ignore battery optimizations).
 * Required for VLESS/VPN functionality to work reliably in background.
 */
public class BackgroundPermissionHelper {

    public static final int REQUEST_CODE_IGNORE_BATTERY_OPTIMIZATION = 1001;

    /**
     * Проверяет, есть ли разрешение на работу в фоне.
     *
     * @param context контекст приложения
     * @return true если разрешение уже предоставлено
     */
    public static boolean hasBackgroundPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (pm != null) {
                return pm.isIgnoringBatteryOptimizations(context.getPackageName());
            }
            return false;
        }
        // На Android < 6.0 разрешение не требуется
        return true;
    }

    /**
     * Запрашивает разрешение на работу в фоне.
     * Вызывайте из Activity.
     *
     * @param activity текущая Activity
     */
    public static void requestBackgroundPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
            if (pm != null && !pm.isIgnoringBatteryOptimizations(activity.getPackageName())) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                    intent.setData(Uri.parse("package:" + activity.getPackageName()));
                    activity.startActivityForResult(intent, REQUEST_CODE_IGNORE_BATTERY_OPTIMIZATION);
                } catch (Exception e) {
                    FileLog.e("Error requesting battery optimization permission: " + e.getMessage());
                    // Fallback: открыть настройки приложения
                    openAppSettings(activity);
                }
            }
        }
    }

    /**
     * Запрашивает разрешение на работу в фоне (тихая версия, без UI).
     * Открывает настройки приложения для ручной настройки.
     *
     * @param activity текущая Activity
     */
    public static void requestBackgroundPermissionSilent(Activity activity) {
        openAppSettings(activity);
    }

    /**
     * Открывает настройки приложения для ручной настройки разрешений.
     *
     * @param activity текущая Activity
     */
    public static void openAppSettings(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
            activity.startActivity(intent);
        } catch (Exception e) {
            FileLog.e("Error opening app settings: " + e.getMessage());
        }
    }

    /**
     * Проверяет результат запроса разрешения.
     * Вызывайте из onActivityResult в Activity.
     *
     * @param context     контекст
     * @param requestCode код запроса
     * @param resultCode  код результата
     * @return true если разрешение предоставлено
     */
    public static boolean onRequestResult(Context context, int requestCode, int resultCode) {
        if (requestCode == REQUEST_CODE_IGNORE_BATTERY_OPTIMIZATION) {
            boolean granted = hasBackgroundPermission(context);
            FileLog.e("Battery optimization permission " + (granted ? "granted" : "denied"));
            return granted;
        }
        return false;
    }

    /**
     * Показывает, нужно ли запрашивать разрешение.
     *
     * @param context контекст
     * @return true если нужно запросить разрешение
     */
    public static boolean shouldRequestPermission(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }
        return !hasBackgroundPermission(context);
    }

    /**
     * Получает описание разрешения для UI.
     *
     * @return строка с описанием
     */
    public static String getPermissionDescription() {
        return "Разрешение на работу в фоне необходимо для стабильной работы VLESS/VPN подключения. " +
                "Без этого разрешения подключение может разрываться когда приложение находится в фоне.";
    }
}
