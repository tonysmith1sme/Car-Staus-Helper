package android.os;

//import android.annotation.SystemApi;
//import android.annotation.UnsupportedAppUsage;

import android.util.MutableInt;

import java.util.ArrayList;
import java.util.HashMap;
//import libcore.util.HexEncoding;

//@SystemApi
/* loaded from: classes2.dex */
public class SystemProperties {
//    @UnsupportedAppUsage
    public static final int PROP_NAME_MAX = Integer.MAX_VALUE;
    public static final int PROP_VALUE_MAX = 91;
    private static final String TAG = "SystemProperties";
    private static final boolean TRACK_KEY_ACCESS = false;
//    @UnsupportedAppUsage
//    @GuardedBy({"sChangeCallbacks"})
    private static final ArrayList<Runnable> sChangeCallbacks = new ArrayList<>();
//    @GuardedBy({"sRoReads"})
    private static final HashMap<String, MutableInt> sRoReads = null;

    private static native void native_add_change_callback();

//    @UnsupportedAppUsage
    private static native String native_get(String str);

    private static native String native_get(String str, String str2);

    private static native boolean native_get_boolean(String str, boolean z);

    private static native int native_get_int(String str, int i);

//    @UnsupportedAppUsage
    private static native long native_get_long(String str, long j);

    private static native void native_report_sysprop_change();

    private static native void native_set(String str, String str2);

    private static void onKeyAccess(String key) {
    }

//    @SystemApi
    public static String get(String key) {
//        return native_get(key);
        throw new RuntimeException("Stub!");
    }

//    @SystemApi
    public static String get(String key, String def) {
//        return native_get(key, def);
        throw new RuntimeException("Stub!");
    }

//    @SystemApi
    public static int getInt(String key, int def) {
//        return native_get_int(key, def);
        throw new RuntimeException("Stub!");
    }

//    @SystemApi
    public static long getLong(String key, long def) {
//        return native_get_long(key, def);
        throw new RuntimeException("Stub!");
    }

//    @SystemApi
    public static boolean getBoolean(String key, boolean def) {
//        return native_get_boolean(key, def);
        throw new RuntimeException("Stub!");
    }

//    @UnsupportedAppUsage
    public static void set(String key, String val) {
//        if (val != null && !val.startsWith("ro.") && val.length() > 91) {
//            throw new IllegalArgumentException("value of system property '" + key + "' is longer than 91 characters: " + val);
//        }
//        native_set(key, val);
        throw new RuntimeException("Stub!");
    }

//    @UnsupportedAppUsage
    public static void addChangeCallback(Runnable callback) {
//        synchronized (sChangeCallbacks) {
//            if (sChangeCallbacks.size() == 0) {
//                native_add_change_callback();
//            }
//            sChangeCallbacks.add(callback);
//        }
        throw new RuntimeException("Stub!");
    }

    private static void callChangeCallbacks() {
//        synchronized (sChangeCallbacks) {
//            if (sChangeCallbacks.size() == 0) {
//                return;
//            }
//            ArrayList<Runnable> callbacks = new ArrayList<>(sChangeCallbacks);
//            long token = Binder.clearCallingIdentity();
//            for (int i = 0; i < callbacks.size(); i++) {
//                callbacks.get(i).run();
//            }
//            Binder.restoreCallingIdentity(token);
//        }
        throw new RuntimeException("Stub!");
    }

//    @UnsupportedAppUsage
    public static void reportSyspropChanged() {
//        native_report_sysprop_change();
        throw new RuntimeException("Stub!");
    }

    public static String digestOf(String... keys) {
//        Arrays.sort(keys);
//        try {
//            MessageDigest digest = MessageDigest.getInstance(KeyProperties.DIGEST_SHA1);
//            for (String key : keys) {
//                String item = key + "=" + get(key) + "\n";
//                digest.update(item.getBytes(StandardCharsets.UTF_8));
//            }
//            return HexEncoding.encodeToString(digest.digest()).toLowerCase();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
        throw new RuntimeException("Stub!");
    }

//    @UnsupportedAppUsage
    private SystemProperties() {
    }
}