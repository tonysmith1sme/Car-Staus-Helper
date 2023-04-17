package android.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.Keep;

import java.util.LinkedHashMap;

@Keep
public class BydDialog implements DialogInterface {
    public static final int LAYOUT_HINT_NONE = 0;
    public static final int LAYOUT_HINT_SIDE = 1;
    public static final int THEME_DEVICE_DEFAULT_DARK = 4;
    public static final int THEME_DEVICE_DEFAULT_LIGHT = 5;
    public static final int THEME_HOLO_DARK = 2;
    public static final int THEME_HOLO_LIGHT = 3;
    public static final int THEME_TRADITIONAL = 1;

    protected BydDialog(Context context) {
        throw new RuntimeException("Stub!");
    }

    protected BydDialog(Context context, int theme) {
        throw new RuntimeException("Stub!");
    }

    BydDialog(Context context, int theme, boolean createThemeContextWrapper) {
        throw new RuntimeException("Stub!");
    }

    protected BydDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        throw new RuntimeException("Stub!");
    }

    static int resolveDialogTheme(Context context, int resid) {
        throw new RuntimeException("Stub!");
    }

    public Button getButton(int whichButton) {
        throw new RuntimeException("Stub!");
    }

    public ListView getListView() {
        throw new RuntimeException("Stub!");
    }

    public void setTitle(CharSequence title) {
        throw new RuntimeException("Stub!");
    }

    public void setTitle(String title) {
        throw new RuntimeException("Stub!");
    }

    public void setTitle(int title) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomTitle(View customTitleView) {
        throw new RuntimeException("Stub!");
    }

    public void setMessage(CharSequence message) {
        throw new RuntimeException("Stub!");
    }

    public void setMessage(String message) {
        throw new RuntimeException("Stub!");
    }

    public void setMessage(int message) {
        throw new RuntimeException("Stub!");
    }

    public void setView(View view) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomerView(View view) {
        throw new RuntimeException("Stub!");
    }

    public void setCustomerView(View view, RelativeLayout.LayoutParams params) {
        throw new RuntimeException("Stub!");
    }

    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        throw new RuntimeException("Stub!");
    }

    void setButtonPanelLayoutHint(int layoutHint) {
        throw new RuntimeException("Stub!");
    }

    public void setButton(int whichButton, CharSequence text, Message msg) {
        throw new RuntimeException("Stub!");
    }

    public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public void setButton(CharSequence text, Message msg) {
        setButton(-1, text, msg);
    }

    @Deprecated
    public void setButton2(CharSequence text, Message msg) {
        setButton(-2, text, msg);
    }

    @Deprecated
    public void setButton3(CharSequence text, Message msg) {
        setButton(-3, text, msg);
    }

    @Deprecated
    public void setButton(CharSequence text, OnClickListener listener) {
        setButton(-1, text, listener);
    }

    @Deprecated
    public void setButton2(CharSequence text, OnClickListener listener) {
        setButton(-2, text, listener);
    }

    @Deprecated
    public void setButton3(CharSequence text, OnClickListener listener) {
        setButton(-3, text, listener);
    }

    public void setIcon(int resId) {
        throw new RuntimeException("Stub!");
    }

    public void setIcon(Drawable icon) {
        throw new RuntimeException("Stub!");
    }

    public void setDialogHeight(int height) {
        throw new RuntimeException("Stub!");
    }

    public void setBydTheme() {
        throw new RuntimeException("Stub!");
    }

    public void setIconAttribute(int attrId) {
        throw new RuntimeException("Stub!");
    }

    public void setInverseBackgroundForced(boolean forceInverseBackground) {
        throw new RuntimeException("Stub!");
    }

    public void onCreate(Bundle savedInstanceState) {
        throw new RuntimeException("Stub!");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        throw new RuntimeException("Stub!");
    }

    public void setBuilder(Builder dialogBuilder) {
        throw new RuntimeException("Stub!");
    }

    public Builder getDialogBuilder() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void cancel() {
        throw new RuntimeException("Stub!");
    }

    @Override
    public void dismiss() {
        throw new RuntimeException("Stub!");
    }

    @Keep
    public static class Builder {

        public Builder(Context context) {
            throw new RuntimeException("Stub!");
        }

        public Builder(Context context, int theme) {
            throw new RuntimeException("Stub!");
        }

        public Context getContext() {
            throw new RuntimeException("Stub!");
        }

        public Builder setTitle(int titleId) {
            throw new RuntimeException("Stub!");
        }

        public Builder setTitle(CharSequence title) {
            throw new RuntimeException("Stub!");
        }

        public Builder setTitle(String title) {
            throw new RuntimeException("Stub!");
        }

        public Builder setCustomTitle(View customTitleView) {
            throw new RuntimeException("Stub!");
        }

        public Builder setDialogHeight(int height) {
            throw new RuntimeException("Stub!");
        }

        public Builder setMessage(int messageId) {
            throw new RuntimeException("Stub!");
        }

        public Builder setMessage(CharSequence message) {
            throw new RuntimeException("Stub!");
        }

        public Builder setMessage(String message) {
            throw new RuntimeException("Stub!");
        }

        public Builder setIcon(int iconId) {
            throw new RuntimeException("Stub!");
        }

        public Builder setIcon(Drawable icon) {
            throw new RuntimeException("Stub!");
        }

        public Builder setIconAttribute(int attrId) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPositiveButton(int textId, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPositiveButton(CharSequence text, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPositiveButton(String text, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setNegativeButton(int textId, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setNegativeButton(String text, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setNeutralButton(int textId, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setNeutralButton(CharSequence text, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setNeutralButton(String text, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setCancelable(boolean cancelable) {
            throw new RuntimeException("Stub!");
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setItems(int itemsId, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setItems(CharSequence[] items, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setListItem(LinkedHashMap<String, String> listItems) {
            throw new RuntimeException("Stub!");
        }

        public Builder setAdapter(ListAdapter adapter, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setCursor(Cursor cursor, OnClickListener listener, String labelColumn) {
            throw new RuntimeException("Stub!");
        }

        public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, OnMultiChoiceClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setSingleChoiceItems(int itemsId, int checkedItem, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setView(int layoutResId) {
            throw new RuntimeException("Stub!");
        }

        public Builder setView(View view) {
            throw new RuntimeException("Stub!");
        }

        public Builder setCustomerView(View view) {
            throw new RuntimeException("Stub!");
        }

        public Builder setCustomerView(View view, RelativeLayout.LayoutParams params) {
            throw new RuntimeException("Stub!");
        }

        public Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
            throw new RuntimeException("Stub!");
        }

        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            throw new RuntimeException("Stub!");
        }

        public Builder setRecycleOnMeasureEnabled(boolean enabled) {
            throw new RuntimeException("Stub!");
        }

        public Button getButton(int which) {
            throw new RuntimeException("Stub!");
        }

        public void setBydTheme() {
            throw new RuntimeException("Stub!");
        }

        public BydDialog create() {
            throw new RuntimeException("Stub!");
        }

        public BydDialog show() {
            throw new RuntimeException("Stub!");
        }
    }
}