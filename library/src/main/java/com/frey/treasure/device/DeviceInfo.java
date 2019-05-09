package com.frey.treasure.device;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.RequiresPermission;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import com.frey.treasure.annotations.ScreenResolution;

import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


public class DeviceInfo {

    private static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";

    public static final int SCREEN_RESOLUTION_WIDTH_FIRST = 1001;
    public static final int SCREEN_RESOLUTION_HEIGHT_FIRST = 1002;

    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.getImei();
            } else {
                return tm.getDeviceId();
            }
        }
        return "";
    }

    public static String getImsi(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return tm.getSubscriberId();
        }
        return "";
    }

    public static String getMacAddress(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            // 判断当前wifi状态是否为开启状态
            if (!wifiManager.isWifiEnabled()) {
                // 打开wifi 有些设备需要授权
                wifiManager.setWifiEnabled(true);
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null)
                return connectionInfo.getMacAddress().toUpperCase();
            else
                return DEFAULT_MAC_ADDRESS;
        } else {
            return getMacFromHardware();
        }
    }

    private static String getMacFromHardware() {
        try {
            List<NetworkInterface> networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : networkInterfaces) {
                if (!networkInterface.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = networkInterface.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder sb = new StringBuilder();
                for (byte b : macBytes) {
                    sb.append(String.format("%02X:", b));
                }

                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString().toUpperCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DEFAULT_MAC_ADDRESS;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * return the screen resolution
     * example:
     * 1280x720 if flag == {@link #SCREEN_RESOLUTION_HEIGHT_FIRST } or <br/>
     * 720x1280 if flag == {@link #SCREEN_RESOLUTION_WIDTH_FIRST}
     *
     * @param context
     * @param flag    order of width and height {@link #SCREEN_RESOLUTION_HEIGHT_FIRST} or {@link #SCREEN_RESOLUTION_WIDTH_FIRST}
     * @return screen resolution
     */
    public static String getScreenResolution(Context context, @ScreenResolution int flag) {
        int width = getScreenWidth(context);
        int height = getScreenHeight(context);
        if (flag == SCREEN_RESOLUTION_HEIGHT_FIRST)
            return height + "x" + width;
        else
            return width + "x" + height;
    }


    public static String getBrand() {
        return android.os.Build.BRAND;
    }

    public static String getModel() {
        return android.os.Build.MODEL;
    }

    public static String getReleaseVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * return baseband version
     *
     * @return baseband version
     */

    public static String getBasebandVersion() {
        String version = "";
        try {
            Class cl = Class.forName("android.os.SystemProperties");
            Object invoker = cl.newInstance();
            Method m = cl.getMethod("get", new Class[]{String.class, String.class});
            Object result = m.invoke(invoker, new Object[]{"gsm.version.baseband", "no message"});
            version = (String) result;
        } catch (Exception e) {
        }
        return version;
    }


}
