package cn.droidlover.xdroid.expand.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation.AnimationListener;

/**
 * Created by yuanlu on 2017-05-23.
 */

public class AlphaAnimUtil {

    /**
     * 根据指定条件创建动画并启动动画
     * @param view
     * @param from
     * @param to
     * @param duration
     * @param fillAfter
     */
    public static void startAnim(View view, float from, float to, int duration, boolean fillAfter, AnimationListener listener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(from, to);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(fillAfter);
        if (listener != null) {
            alphaAnimation.setAnimationListener(listener);
        }
        view.startAnimation(alphaAnimation);
    }

    public static void startAnim(View view, float from, float to, int duration, boolean fillAfter) {
        startAnim(view, from, to, duration, fillAfter, null);
    }

    public static void startAnim(View view, float from, float to, int duration) {
        startAnim(view, from, to, duration, false);
    }

    public static void startAnim(View view, float from, float to) {
        startAnim(view, from, to, AnimUtil.DURATION, false);
    }
}
