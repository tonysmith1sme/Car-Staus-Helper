package android.strategyservice;

import android.content.Context;
import android.os.IBinder;

import androidx.annotation.Keep;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Keep
public final class StrategyManager implements IBinder.DeathRecipient {
    public static final String ACQUISITION_STRATEGY = "acquisition";
    public static final String CFS_ACQ_FREQ = "AcqFreq";
    public static final String CFS_APLOG_ENABLE = "APLogEnable";
    public static final String CFS_AUTOSERVICE_LOG_LEVEL = "AutoServiceLogLevel";
    public static final String CFS_BOOST_VOLTAGE = "BoostVoltage";
    public static final String CFS_BOOST_VOLTAGE_VALUE = "BoostVoltageValue";
    public static final String CFS_CPU_FREQ = "CpuFreq";
    public static final String CFS_CRASHLOG_ENABLE = "CrashLogEnable";
    public static final String CFS_CRASHUPLOAD_FREQ = "CrashInfoUploadFreq";
    public static final String CFS_CRASH_INFO = "CrashInfo";
    public static final String CFS_DEFAULT_PLEVEL = "DefaultPLevel";
    public static final String CFS_EPB_STATE = "EPBState";
    public static final String CFS_FRONT_HATCH = "FrontHatch";
    public static final String CFS_LOGSAVE_FREQ = "LogSaveFreq";
    public static final String CFS_LOGSAVE_MAX = "LogSaveMax";
    public static final String CFS_LOGUPLOAD_FREQ = "LogUploadFreq";
    public static final String CFS_LOG_SAVE = "LogSave";
    public static final String CFS_LOG_SCOPE = "LogScope";
    public static final String CFS_LOG_TIME_START_END = "TimePeriod";
    public static final String CFS_LOG_UPLOAD = "LogUpload";
    public static final String CFS_MAGICWINDOW = "MagicWindow";
    public static final String CFS_MONITOR_AUTOSERVICE = "MonitorAutoService";
    public static final String CFS_ONLY_TRACE_ENABLE = "TraceEnable";
    public static final String CFS_OTA_TIME = "OTATime";
    public static final String CFS_POWER_VOLTAGE_VALUE = "PowerVoltageValue";
    public static final String CFS_RAM_FREQ = "RamFreq";
    public static final String CFS_RECOVERY_LOG_UPLOAD = "RecoveryLogUpload";
    public static final String CFS_RECOVERY_LOG_UPLOAD_ENABLE = "RecoveryLogUploadEnable";
    public static final String CFS_SPEED = "Speed";
    public static final String CFS_STALL = "Stall";
    public static final String CFS_SUPERUI_ACQ = "SuperUIAcq";
    public static final String CFS_SYSTEMINFO_ACQ = "SystemInfoAcq";
    public static final String CFS_THIRDPARTY_ACQ = "ThirdPartyAcq";
    public static final String CFS_UPLOAD_ALL_LOG = "UploadAllLogEnable";
    public static final String CFS_USERDATA_FREQ = "UserdataFreq";
    public static final String CFS_VEHICLE_CONFIG = "VehicleConfig";
    public static final String CONFIG_STRATEGY = "config";
    public static final String PERMISSION_STRATEGY = "permission";
    public static final String PMS_ACCOFFWHITE = "AccOffWhite";
    public static final String PMS_AFBLACK = "AudioFocusBlack";
    public static final String PMS_AFREJECT = "AudioFocusReject";
    public static final String PMS_AFWHITE = "AudioFocusWhite";
    public static final String PMS_AUDIOFORCED = "AudioForced";
    public static final String PMS_AUTOAPIBLACK = "AutoApiBlack";
    public static final String PMS_AUTOSTARTBLACK = "AutoStartBlack";
    public static final String PMS_AUTOSTARTWHITE = "AutoStartWhite";
    public static final String PMS_BCMSUPPORT = "BCMSupport";
    public static final String PMS_BOOSTVOLTAGENOSUPPORT = "BoostVoltageNoSupport";
    public static final String PMS_INSTALLFORBIDDEN = "InstallForbidden";
    public static final String PMS_KALAOKAPP = "KalaOkApp";
    public static final String PMS_LFCHANNEL = "LeftFrontChannel";
    public static final String PMS_MAGICWINDOWBLACK = "MagicWindowBlack";
    public static final String PMS_MAGICWINDOWHORIZONTALFULL = "MagicWindowHorizontalFull";
    public static final String PMS_MAGICWINDOWLIST = "MagicWindowList";
    public static final String PMS_MEDIAAPP = "MediaApp";
    public static final String PMS_PROCESSSUPER = "ProcessSuper";
    public static final String PMS_TRASHSUPER = "TrashSuper";
    private static final String TAG = "StrategyManager";
    private IStrategyManagerService mService;
    private Lock mLock = new ReentrantLock();
    private HashMap<String, List<Listener>> mListeners = new HashMap<>();

    /* loaded from: classes2.dex */
    public final class Listener {
        public List<String> items;
        public IStrategyListener listener;

        public Listener(List<String> styItems, IStrategyListener styListener) {
            this.items = styItems;
            this.listener = styListener;
        }
    }

    public StrategyManager(Context context) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.p009os.IBinder.DeathRecipient
    public void binderDied() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(String name, List<String> items, IStrategyListener listener) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(String name, List<String> items, IStrategyListener listener) {
        throw new RuntimeException("Stub!");
    }

    public Strategy getStrategy(String name, List<String> items) {
        throw new RuntimeException("Stub!");
    }

    public void setStrategy(String name, Strategy strategy) {
        throw new RuntimeException("Stub!");
    }

    private boolean getStrategyService() {
        throw new RuntimeException("Stub!");
    }

    private int isListenerRegistered(List<Listener> listeners, List<String> items, IStrategyListener listener) {
        throw new RuntimeException("Stub!");
    }
}