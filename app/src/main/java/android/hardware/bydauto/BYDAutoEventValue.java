package android.hardware.bydauto;

import android.support.annotation.Keep;

@Keep
public class BYDAutoEventValue {
    public static final int INVAILD_INT = -999999999;
    public static final double INVALID_DOUBLE = -9.99999999E8d;
    public static final float INVALID_FLOAT = -1.0E9f;
    public int intValue = INVAILD_INT;
    public float floatValue = -1.0E9f;
    public double doubleValue = -9.99999999E8d;
    public int[] intArrayValue = null;
    public float[] floatArrayValue = null;
    public byte[] bufferDataValue = null;
}