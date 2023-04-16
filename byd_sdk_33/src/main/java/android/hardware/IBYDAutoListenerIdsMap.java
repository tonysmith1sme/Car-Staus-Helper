package android.hardware;

import android.util.ArraySet;

import androidx.annotation.Keep;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Keep
public class IBYDAutoListenerIdsMap<T> {
    private static final boolean DEBUG = false;
    private static final String TAG = "IBYDAutoListenerIdsMap";
    private Map<T, ArraySet<Integer>> mListenerIdMap = new ConcurrentHashMap();
    private ArraySet<T> mListenerAllIds = new ArraySet<>();
    private Map<Integer, ArraySet<T>> mIdListenerMap = new ConcurrentHashMap();

    public void addToMap(T listener, int[] ids) {
        throw new RuntimeException("Stub!");
    }

    public void addToMap(T listener) {
        throw new RuntimeException("Stub!");
    }

    public void addToListenerIdMap(T listener, int[] ids) {
        throw new RuntimeException("Stub!");
    }

    public void addToIdListenerMap(T listener, int[] ids) {
        throw new RuntimeException("Stub!");
    }

    public void removeFromMap(T listener) {
        throw new RuntimeException("Stub!");
    }

    public void removeFromIdMap(T listener) {
        throw new RuntimeException("Stub!");
    }

    public boolean containsId(int id) {
        throw new RuntimeException("Stub!");
    }

    public boolean containsListener(T listener) {
        throw new RuntimeException("Stub!");
    }

    public ArraySet<T> getIdListeners(int id) {
        throw new RuntimeException("Stub!");
    }

    public ArraySet<Integer> getListenerIds(T listener) {
        return this.mListenerIdMap.get(listener);
    }

    public ArraySet<T> getListeners() {
        throw new RuntimeException("Stub!");
    }

    public boolean isListenerAllIdsEmpty() {
        throw new RuntimeException("Stub!");
    }

    public boolean isListenerEmpty() {
        throw new RuntimeException("Stub!");
    }

    String toString(int[] a) {
        throw new RuntimeException("Stub!");
    }
}