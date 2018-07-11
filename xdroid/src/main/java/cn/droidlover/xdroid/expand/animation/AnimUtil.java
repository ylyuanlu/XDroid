package cn.droidlover.xdroid.expand.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

/**
 * Created by yuanlu on 2017-05-23.
 */

public class AnimUtil {
    public static final int DURATION = 500;

    public static void startAnim(View view, int resId, AnimationListener listener) {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), resId);
        if (listener != null) {
            animation.setAnimationListener(listener);
        }
        view.startAnimation(animation);
    }

    public static void startAnim(View view, int resId) {
        startAnim(view, resId, null);
    }
}
