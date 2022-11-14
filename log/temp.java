package android.hardware.bydauto.engine;

import android.content.Context;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.net.wifi.WifiEnterpriseConfig;
import android.util.Log;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class BYDAutoEngineDevice extends AbsBYDAutoDevice {
    private static final boolean DEBUG = true;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int ENGINE_CAPACITY_MAX = 250;
    public static final int ENGINE_CAPACITY_MIN = 0;
    public static final int ENGINE_COMMAND_BUSY = -2147482647;
    public static final int ENGINE_COMMAND_FAILED = -2147482648;
    public static final int ENGINE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int ENGINE_COMMAND_SUCCESS = 0;
    public static final int ENGINE_COMMAND_TIMEOUT = -2147482646;
    public static final int ENGINE_COOLANT_LEVEL_LOW = 2;
    public static final int ENGINE_COOLANT_LEVEL_NORMAL = 1;
    public static final int ENGINE_COOLING_FAN_TEMPERATURE_MAX = 1;
    public static final int ENGINE_COOLING_FAN_TEMPERATURE_MIN = 0;
    public static final double ENGINE_DISPLACEMENT_MAX = 25.5d;
    public static final double ENGINE_DISPLACEMENT_MIN = 0.0d;
    private static final String ENGINE_GET_PERM = "android.permission.BYDAUTO_ENGINE_GET";
    public static final int ENGINE_IDLING_STATE_OFF = 0;
    public static final int ENGINE_IDLING_STATE_ON = 1;
    public static final double ENGINE_MILEAGE_MAX = 1677721.5d;
    public static final double ENGINE_MILEAGE_MIN = 0.0d;
    public static final int ENGINE_OIL_MAX = 254;
    public static final int ENGINE_OIL_MIN = 0;
    public static final int ENGINE_POWER_MAX = 300;
    public static final int ENGINE_POWER_MIN = -100;
    private static final String ENGINE_SET_PERM = "android.permission.BYDAUTO_ENGINE_SET";
    public static final int ENGINE_SIMULATOR_VOICE_SOURCE_1 = 1;
    public static final int ENGINE_SIMULATOR_VOICE_SOURCE_2 = 2;
    public static final int ENGINE_SIMULATOR_VOICE_SOURCE_3 = 3;
    public static final int ENGINE_SPEED_MAX = 8000;
    public static final int ENGINE_SPEED_MIN = 0;
    public static final int ENGINE_SPEED_WARNING_NO = 1;
    public static final int ENGINE_SPEED_WARNING_YES = 0;
    public static final int ENGINE_STATE_BREAKDOWN = 2;
    public static final int ENGINE_STATE_NORMAL = 1;
    public static final int ENGINE_TARGET_IDLING_VALUE_MAX = 2540;
    public static final int ENGINE_TARGET_IDLING_VALUE_MIN = 0;
    public static final String ENGINE_TYPE1 = "371QA";
    public static final String ENGINE_TYPE10 = "488QA";
    public static final String ENGINE_TYPE11 = "4G15";
    public static final String ENGINE_TYPE12 = "4G18";
    public static final String ENGINE_TYPE13 = "4G69";
    public static final String ENGINE_TYPE14 = "473QE";
    public static final String ENGINE_TYPE15 = "471ZQA";
    public static final String ENGINE_TYPE2 = "473QB";
    public static final String ENGINE_TYPE3 = "473QC";
    public static final String ENGINE_TYPE4 = "473QD";
    public static final String ENGINE_TYPE5 = "476ZQA";
    public static final String ENGINE_TYPE6 = "483QA";
    public static final String ENGINE_TYPE7 = "483QB";
    public static final String ENGINE_TYPE8 = "483QB CNG";
    public static final String ENGINE_TYPE9 = "487ZQA";
    public static final int ENGINE_VOICE_SIMULATOR_OFF = 0;
    public static final int ENGINE_VOICE_SIMULATOR_ON = 1;
    public static final int ENGINE_WATER_TEMPERATURE_ABNORMAL = 255;
    public static final int ENGINE_WATER_TEMPERATURE_MAX = 194;
    public static final int ENGINE_WATER_TEMPERATURE_MIN = -60;
    public static final String FEATURE_ENGINE_VOICE_SIMULATOR = "EngineVoiceSimulator";
    public static final String FEATURE_ENGINE_VOICE_SOURCE = "EngineVoiceSource";
    protected static final String TAG = "BYDAutoEngineDevice";
    private static int mDeviceType = 1012;
    private static BYDAutoEngineDevice mInstance = null;
    private final Context mContext;

    private BYDAutoEngineDevice(Context con) {
        super(con);
        this.mContext = con;
    }

    public static synchronized BYDAutoEngineDevice getInstance(Context con) {
        BYDAutoEngineDevice bYDAutoEngineDevice;
        synchronized (BYDAutoEngineDevice.class) {
            if (mInstance == null && con != null) {
                mInstance = new BYDAutoEngineDevice(con);
            }
            bYDAutoEngineDevice = mInstance;
        }
        return bYDAutoEngineDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return mDeviceType;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return ENGINE_SET_PERM;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return ENGINE_GET_PERM;
    }

    public byte[] getEngineState() {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        byte[] state = super.getBuffer(mDeviceType, 607);
        return state;
    }

    public int hasFeature(String feature) {
        int featureCmd;
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        if (feature.equals(FEATURE_ENGINE_VOICE_SIMULATOR)) {
            featureCmd = BYDAutoFeatureIds.ENGINE_HAS_ENGINE_VOICE_SIMULATOR;
        } else if (feature.equals(FEATURE_ENGINE_VOICE_SOURCE)) {
            featureCmd = BYDAutoFeatureIds.ENGINE_HAS_ENGINE_VOICE_SOURCE;
        } else {
            return -2147482645;
        }
        int value = super.get(mDeviceType, featureCmd);
        Log.d(TAG, "hasFeature: If has the feature(" + feature + "): " + value);
        return value;
    }

    public int getEngineVoiceSimulatorState() {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        int state = super.get(mDeviceType, BYDAutoFeatureIds.ENGINE_VOICE_SIMULATOR_STATE);
        Log.d(TAG, "getEngineVoiceSimulatorState: The state of engine voice simulator is " + state);
        return state;
    }

    public int setEngineVoiceSimulatorState(int state) {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_SET_PERM, null);
        Log.d(TAG, "setEngineVoiceSimulatorState: the state of engine voice simulator is " + state);
        if (state != 1 && state != 0) {
            return -2147482645;
        }
        int result = super.set(mDeviceType, BYDAutoFeatureIds.ENGINE_VOICE_SIMULATOR_STATE_SET, state);
        return result;
    }

    public double getEngineDisplacement() {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        double value = super.getDouble(mDeviceType, BYDAutoFeatureIds.ENGINE_DISPLACEMENT);
        Log.d(TAG, "getEngineDisplacement: The engine displacement is " + value);
        return value;
    }

    public String getEngineCode() {
        String code;
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        int value = super.get(mDeviceType, BYDAutoFeatureIds.ENGINE_CODE);
        Log.d(TAG, "getEngineCode: The engine value is " + value);
        switch (value) {
            case 0:
                code = ENGINE_TYPE1;
                break;
            case 1:
                code = ENGINE_TYPE2;
                break;
            case 2:
                code = ENGINE_TYPE3;
                break;
            case 3:
                code = ENGINE_TYPE4;
                break;
            case 4:
                code = ENGINE_TYPE5;
                break;
            case 5:
                code = ENGINE_TYPE6;
                break;
            case 6:
                code = ENGINE_TYPE7;
                break;
            case 7:
                code = ENGINE_TYPE8;
                break;
            case 8:
                code = ENGINE_TYPE9;
                break;
            case 9:
                code = ENGINE_TYPE10;
                break;
            case 10:
                code = ENGINE_TYPE11;
                break;
            case 11:
                code = ENGINE_TYPE12;
                break;
            case 12:
                code = ENGINE_TYPE13;
                break;
            case 13:
                code = ENGINE_TYPE14;
                break;
            case 14:
                code = ENGINE_TYPE15;
                break;
            default:
                return WifiEnterpriseConfig.EMPTY_VALUE;
        }
        Log.d(TAG, "getEngineCode: The engine code is " + code);
        return code;
    }

    public int getEnginePower() {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        int value = super.get(mDeviceType, BYDAutoFeatureIds.ENGINE_POWER);
        Log.d(TAG, "getEnginePower: The engine power is " + value);
        return value;
    }

    public int getEngineSpeed() {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        int value = super.get(mDeviceType, BYDAutoFeatureIds.ENGINE_SPEED);
        Log.d(TAG, "getEngineSpeed: The engine speed is " + value);
        return value;
    }

    public int getEngineCoolantLevel() {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        int state = super.get(mDeviceType, BYDAutoFeatureIds.ENGINE_COOLANT_LEVEL);
        Log.d(TAG, "getEngineCoolantLevel: The engine coolant level is " + state);
        return state;
    }

    public int getOilLevel() {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        int value = super.get(mDeviceType, BYDAutoFeatureIds.ENGINE_OIL_LEVEL);
        Log.d(TAG, "getOilLevel: The engine oil level is " + value);
        return value;
    }

    public int getEngineSimulatorVoiceSource() {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        int type = super.get(mDeviceType, BYDAutoFeatureIds.ENGINE_SIMULATOR_SOURCE_TYPE);
        Log.d(TAG, "getEngineSimulatorVoiceSource: The engine simulator voice source type is " + type);
        return type;
    }

    public int setEngineSimulatorVoiceSource(int type) {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_SET_PERM, null);
        Log.d(TAG, "setEngineSimulatorVoiceSource: the type of engine voice source is " + type);
        if (type != 1 && type != 2 && type != 3) {
            return -2147482645;
        }
        int result = super.set(mDeviceType, BYDAutoFeatureIds.ENGINE_SIMULATOR_SOURCE_TYPE_SET, type);
        return result;
    }

    public int getEngineSpeedWarningValue() {
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        int value = super.get(mDeviceType, BYDAutoFeatureIds.ENGINE_SPEED_WARNING);
        Log.d(TAG, "getEngineSpeedWarningValue: value " + value);
        return value;
    }

    public void setAllStatus() {
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        Log.d(TAG, "postEvent device_type: " + device_type + ", event_type =" + Integer.toHexString(event_type) + ", value = " + Arrays.toString(value));
        return onPostEvent(new BYDAutoEngineEvent(device_type, event_type, value, data));
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 1012;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return null;
    }

    public void registerListener(AbsBYDAutoEngineListener l) {
        Log.i(TAG, "registerListener");
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        if (l != null) {
            super.registerListener((IBYDAutoListener) l);
        }
    }

    public void registerListener(AbsBYDAutoEngineListener l, int[] featureIDs) {
        Log.i(TAG, "registerListener2");
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        if (l != null) {
            super.registerListener((IBYDAutoListener) l, featureIDs);
        }
    }

    public void unregisterListener(AbsBYDAutoEngineListener l) {
        Log.i(TAG, "unregisterListener");
        this.mContext.enforceCallingOrSelfPermission(ENGINE_GET_PERM, null);
        if (l != null) {
            super.unregisterListener((IBYDAutoListener) l);
        }
    }
}