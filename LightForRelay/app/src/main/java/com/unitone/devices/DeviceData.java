package com.unitone.devices;

public class DeviceData {

	private static final String JNITAG = "WK_JNI";


	private native int GetTempValue();


	private native int GetHumidityValue();


	private native int GetAirAdcValue();


	private native void SetGpio(int gpio, int onoff);

	private native void SetBL(int onoff);

	static {
		try {
			System.loadLibrary("SimpleSmart");
		} catch (UnsatisfiedLinkError ule) {
			System.err.println("WARNING: Could not load library!");
		}
	}

	public DeviceData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int JNI_GetTempValue() {
		return GetTempValue();
	}

	public int JNI_GetHumidityValue() {
		return GetHumidityValue();
	}

	public int JNI_GetAirAdcValue() {
		return GetAirAdcValue();
	}

	public void JNI_SetGpio(int gpio, int onoff) {
		SetGpio(gpio, onoff);
	}

	public void JNI_SetBL(int onoff) {
		SetBL(onoff);
	}

}
