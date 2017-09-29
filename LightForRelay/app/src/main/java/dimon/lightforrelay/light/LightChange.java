package dimon.lightforrelay.light;


import com.unitone.devices.DeviceData;

public interface LightChange {

    void changeSwitch(DeviceData deviceData,int pos,boolean flag);
}
