package cn.droidlover.xdroid.expand.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;

/**
 * Created by yuanlu on 2017-05-23.
 */

public class RotateAnimUtil {

    /**
     * 根据指定条件动态创建动画，并启动动画
     * @param view
     * @param fromDegrees
     * @param toDegrees
     * @param pivotX
     * @param pivotY
     * @param duration
     * @param interpolator
     */
    public static void startAnim(View view, int fromDegrees, int toDegrees, float pivotX, float pivotY,
                                 int duration, int interpolator, AnimationListener listener) {
        Animation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF, pivotY);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(view.getContext(), interpolator);//设置动画插入器
        if (listener != null) {
            rotateAnimation.setAnimationListener(listener);
        }
        view.startAnimation(rotateAnimation);
    }

    public static void startAnim(View view, int fromDegrees, int toDegrees, float pivotX, float pivotY, int duratoin) {
        startAnim(view, fromDegrees, toDegrees, pivotX, pivotY, duratoin, android.R.anim.accelerate_decelerate_interpolator, null);
    }

    public static void startAnim(View view, int fromDegrees, int toDegrees, float pivotX, float pivotY) {
        startAnim(view, fromDegrees, toDegrees, pivotX, pivotY, AnimUtil.DURATION,
                android.R.anim.accelerate_decelerate_interpolator, null);
    }

    public static void startAnim(View view, int fromDegrees, int toDegrees, float pivotX, float pivotY, AnimationListener listener) {
        startAnim(view, fromDegrees, toDegrees, pivotX, pivotY, AnimUtil.DURATION, android.R.anim.accelerate_decelerate_interpolator, listener);
    }
}
