package android.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

public class BydDialog extends AlertDialog {

    protected BydDialog(@NonNull Context context) {
        super(context);
    }

    protected BydDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BydDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        public Builder(@NonNull Context context) {
            throw new RuntimeException("Stub!");
        }

        public Builder setTitle(@StringRes int titleId) {
            throw new RuntimeException("Stub!");
        }

        public Builder setTitle(@Nullable CharSequence title) {
            throw new RuntimeException("Stub!");
        }

        public Builder setMessage(@StringRes int messageId) {
            throw new RuntimeException("Stub!");
        }

        public Builder setMessage(@Nullable CharSequence message) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPositiveButton(@StringRes int textId, final OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPositiveButton(CharSequence text, final OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setNegativeButton(@StringRes int textId, final OnClickListener listener) {
            throw new RuntimeException("Stub!");
        }

        public Builder setNegativeButton(CharSequence text, final OnClickListener listener) {
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
