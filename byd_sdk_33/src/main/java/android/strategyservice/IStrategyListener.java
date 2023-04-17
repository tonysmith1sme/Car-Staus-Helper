package android.strategyservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.Keep;

import java.util.List;

@Keep
public interface IStrategyListener extends IInterface {
    void onStrategyChange(String str, List<String> list) throws RemoteException;

    @Keep
    public static abstract class Stub extends Binder implements IStrategyListener {
        private static final String DESCRIPTOR = "android.strategyservice.IStrategyListener";
        static final int TRANSACTION_onStrategyChange = 1;

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IStrategyListener asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        @Override // android.p009os.IInterface
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.p009os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Keep
        private static class Proxy implements IStrategyListener {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                throw new RuntimeException("Stub!");
            }

            @Override // android.p009os.IInterface
            public IBinder asBinder() {
                throw new RuntimeException("Stub!");
            }

            public String getInterfaceDescriptor() {
                throw new RuntimeException("Stub!");
            }

            @Override // android.strategyservice.IStrategyListener
            public void onStrategyChange(String name, List<String> changeItems) throws RemoteException {
                throw new RuntimeException("Stub!");
            }
        }
    }
}