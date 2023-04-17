package android.strategyservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.Keep;

import java.util.List;

@Keep
public interface IStrategyManagerService extends IInterface {
    void getStrategy(String str, List<String> list, Parcel parcel) throws RemoteException;

    void registerListener(String str, List<String> list, IStrategyListener iStrategyListener) throws RemoteException;

    void setStrategy(String str, String str2) throws RemoteException;

    void unregisterListener(String str, List<String> list, IStrategyListener iStrategyListener) throws RemoteException;

    @Keep
    public static abstract class Stub extends Binder implements IStrategyManagerService {
        private static final String DESCRIPTOR = "android.strategyservice.IStrategyManagerService";
        static final int TRANSACTION_getStrategy = 3;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_setStrategy = 4;
        static final int TRANSACTION_unregisterListener = 2;

        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IStrategyManagerService asInterface(IBinder obj) {
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
        public static class Proxy implements IStrategyManagerService {
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

            @Override // android.strategyservice.IStrategyManagerService
            public void registerListener(String name, List<String> items, IStrategyListener listener) throws RemoteException {
                throw new RuntimeException("Stub!");
            }

            @Override // android.strategyservice.IStrategyManagerService
            public void unregisterListener(String name, List<String> items, IStrategyListener listener) throws RemoteException {
                throw new RuntimeException("Stub!");
            }

            @Override // android.strategyservice.IStrategyManagerService
            public void getStrategy(String name, List<String> items, Parcel parcel) throws RemoteException {
                throw new RuntimeException("Stub!");
            }

            @Override // android.strategyservice.IStrategyManagerService
            public void setStrategy(String name, String value) throws RemoteException {
                throw new RuntimeException("Stub!");
            }
        }
    }
}