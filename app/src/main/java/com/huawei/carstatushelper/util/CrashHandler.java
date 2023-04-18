package com.huawei.carstatushelper.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.huawei.carstatushelper.BuildConfig;
import com.huawei.carstatushelper.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = CrashHandler.class.getSimpleName();
    private static CrashHandler INSTANCE = new CrashHandler();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    @Override
    public void uncaughtException(Thread t, Throwable e) {//当发生exception时候会回调该方法
        dumpToSDCard(t, e);//dump trace 信息到sd卡
        //todo 上传服务器
        e.printStackTrace();
        if (mDefaultExceptionHandler != null) { //交给系统的UncaughtExceptionHandler处理
            mDefaultExceptionHandler.uncaughtException(t, e);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid()); //主动杀死进程
        }
    }

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();//获取当前默认ExceptionHandler，保存在全局对象
        Thread.setDefaultUncaughtExceptionHandler(this);//替换默认对象为当前对象
    }

    private void dumpToSDCard(final Thread t, final Throwable e) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i(TAG, "no sdcard skip dump ");
            return;
        }

        String mLogPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        File file = new File(mLogPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        String time = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
        Log.i(TAG, mLogPath + "crash_" + time + ".txt");
        File logFile = new File(mLogPath, "crash_" + time + ".log");

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(logFile)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            e.printStackTrace(pw);
            pw.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) {
        //应用的版本名称和版本号
        pw.print("App Name:");
        pw.println(mContext.getString(R.string.app_name));

        pw.print("App Package:");
        pw.println(mContext.getPackageName());

        pw.print("App Version: ");
        pw.print(BuildConfig.VERSION_NAME);
        pw.print('_');
        pw.println(BuildConfig.VERSION_CODE);

        //android版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        //手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);

        //手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);

        //cpu架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }

    private void zip(String src, String dest) throws IOException { //压缩文件夹，为上传做准备。节省流量。
        ZipOutputStream out = null;
        File outFile = new File(dest);
        File fileOrDirectory = new File(src);
        out = new ZipOutputStream(new FileOutputStream(outFile));
        if (fileOrDirectory.isFile()) {
            zipFileOrDirectory(out, fileOrDirectory, "");
        } else {
            File[] entries = fileOrDirectory.listFiles();
            for (int i = 0; i < entries.length; i++) {
                zipFileOrDirectory(out, entries[i], "");
            }
        }
        if (null != out) {
            out.close();
        }
    }

    private static void zipFileOrDirectory(ZipOutputStream out, File fileOrDirectory, String curPath) throws IOException {
        FileInputStream in = null;
        if (!fileOrDirectory.isDirectory()) {
            byte[] buffer = new byte[4096];
            int bytes_read;
            in = new FileInputStream(fileOrDirectory);
            ZipEntry entry = new ZipEntry(curPath + fileOrDirectory.getName());
            out.putNextEntry(entry);
            while ((bytes_read = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytes_read);
            }
            out.closeEntry();
        } else {
            File[] entries = fileOrDirectory.listFiles();
            for (int i = 0; i < entries.length; i++) {
                zipFileOrDirectory(out, entries[i], curPath + fileOrDirectory.getName() + "/");
            }
        }
        if (null != in) {
            in.close();
        }
    }
}