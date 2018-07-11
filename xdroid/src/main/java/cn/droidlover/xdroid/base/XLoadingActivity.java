package cn.droidlover.xdroid.base;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.droidlover.xdroid.R;

/**
 * Created by onekey on 2016-10-28.
 */
public class XLoadingActivity extends AppCompatActivity {
    public static final String SHOW_PROGRESS = "show_progress";
    public static final String DISMISS_PROGRESS = "dismiss_progress";

    private ProgressDialog mProgressDialog;

    /**
     * 显示进度，关闭自动显示开关，防止每次请求都显示一个进度
     */
    public static void showProgress() {
        postEvent(SHOW_PROGRESS);
    }

    /**
     * 隐藏进度，打开自动显示开关
     */
    public static void hideProgress() {
        postEvent(DISMISS_PROGRESS);
    }

    public static void postEvent(String message) {
        EventBus.getDefault().post(new MessageEvent(message));
    }

    public static void postEvent(String message, Object object) {
        EventBus.getDefault().post(new MessageEvent(message, object));
    }

    /**
     * 默认对显示和隐藏加载对话框操作，子类可以覆盖该方法
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        if (event.isMessage(SHOW_PROGRESS)) {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
                mProgressDialog.setContentView(R.layout.loading_layout);
            }
        } else if (event.isMessage(DISMISS_PROGRESS)){
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressDialog();
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this, R.style.LoadingDialogStyle);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(true);
    }

    public static class MessageEvent {
        public String message;
        public Object data;

        public MessageEvent(String message) {
            this.message = message;
        }

        public MessageEvent(String message, Object data) {
            this.message = message;
            this.data = data;
        }

        public boolean isMessage(String message) {
            return this.message.equals(message);
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
