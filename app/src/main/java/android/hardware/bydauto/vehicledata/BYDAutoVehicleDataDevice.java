package android.hardware.bydauto.vehicledata;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

public class BYDAutoVehicleDataDevice extends AbsBYDAutoDevice {
    BYDAutoVehicleDataDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoVehicleDataDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int getType() {
//        return 0;
        throw new RuntimeException("Stub!");
    }

    public void sendRegisterTable(int a, byte[] data) {
        throw new RuntimeException("Stub!");
    }

}
