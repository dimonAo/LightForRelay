package dimon.lightforrelay;

import android.app.Application;

import com.unitone.devices.DeviceData;


public class RelayApplication extends Application {
    public Pref pref;
    public DeviceData deviceData;
    public static final int GL = 1;
    public static final int CC = 2;
    public static final int CH = 3;
    public static final int GH = 4;
    public static final int GM = 5;

    public static final int ON = 1;
    public static final int OFF = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        pref = Pref.getPref(this);
        deviceData = new DeviceData();

        checkFirstInApp();

    }

    private void checkFirstInApp() {
        if (pref.getFirstIn()) {
            deviceData.JNI_SetGpio(GL, OFF);
            deviceData.JNI_SetGpio(GH, OFF);
            deviceData.JNI_SetGpio(GM, OFF);
            deviceData.JNI_SetGpio(CC, OFF);
            deviceData.JNI_SetGpio(CH, OFF);
            pref.setFirstIn(false);
            pref.setGHFlag(false);
            pref.setGMFlag(false);
            pref.setGLFlag(false);
            pref.setCCFlag(false);
            pref.setCHFlag(false);
        }
    }





}
