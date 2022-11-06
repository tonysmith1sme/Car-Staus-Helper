package android.hardware.bydauto.bigdata;

public abstract class AbsBYDAutoBigDataListener {
//    public AbsBYDAutoBigDataListener() {
//        throw new RuntimeException("Stub!");
//    }

//    public void onNeedRendRegisterTable(int value) {
//        throw new RuntimeException("Stub!");
//    }

//    public void onWholeFrameDataChanged(byte[] data) {
//        throw new RuntimeException("Stub!");
//    }


//    public final void onDataChanged(android.hardware.IBYDAutoEvent event) {
//        throw new RuntimeException("Stub!");
//    }

    public void onDataEventChanged(int a, android.hardware.bydauto.BYDAutoEventValue value) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int a, String error) {
        throw new RuntimeException("Stub!");
    }
}
