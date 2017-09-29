package dimon.lightforrelay.light;


import android.content.Context;

import com.unitone.devices.DeviceData;

import dimon.lightforrelay.Pref;

public class GHChangeLight implements LightChange {

    private static GHChangeLight mInstance;
    private Context context;

    private GHChangeLight(Context context) {
        this.context = context;
    }

    public static GHChangeLight createGHChangeLight(Context context) {
        if (mInstance == null) {
            synchronized (GHChangeLight.class) {
                if (mInstance == null) {
                    mInstance = new GHChangeLight(context);
                }
            }
        }
        return mInstance;
    }


    @Override
    public void changeSwitch(DeviceData deviceData, int pos, boolean flag) {

//        deviceData.JNI_SetGpio(pos, onOrOff);
        Pref.getPref(context).setGHFlag(!flag);
    }
}
