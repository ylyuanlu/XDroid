package cn.droidlover.xdroid.expand.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;

/**
 * Created by yuanlu on 2017-05-23.
 */

public class ScaleAnimUtil {

    /**
     * 根据动画条件创建动画，并启动动画
     * @param view 应用该动画的视图控件
     * @param fromXScale
     * @param toXScale
     * @param fromYScale
     * @param toYScale
     * @param pivotX
     * @param pivotY
     * @param repeatCount
     * @param repeatMode
     * @param interpolatorResId
     * @param duration
     * @param startOffset
     */
    public static void startAnim(View view,
                                 float fromXScale, float toXScale,
                                 float fromYScale, float toYScale,
                                 float pivotX, float pivotY,
                                 int repeatCount, int repeatMode,
                                 int interpolatorResId,
                                 int duration, int startOffset,
                                 AnimationListener listener) {
        Animation scaleAnimation = new ScaleAnimation(fromXScale, toXScale, fromYScale, toYScale,
                Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF, pivotY);
        scaleAnimation.setDuration(duration);   //设置动画持续时间为500毫秒
        scaleAnimation.setFillAfter(true);  //如果fillAfter的值为true,则动画执行后，控件将停留在执行结束的状态
        scaleAnimation.setFillBefore(false);    //如果fillBefore的值为true，则动画执行后，控件将回到动画执行之前的状态
        scaleAnimation.setRepeatCount(repeatCount); //设置动画循环次数
        scaleAnimation.setRepeatMode(repeatMode);
        scaleAnimation.setStartOffset(startOffset);
        scaleAnimation.setInterpolator(view.getContext(), interpolatorResId);   //设置动画插入器
        if (listener != null) {
            scaleAnimation.setAnimationListener(listener);
        }
        view.startAnimation(scaleAnimation);
    }

    public static void startAnim(View view,
                                 float fromXScale, float toXScale,
                                 float fromYScale, float toYScale,
                                 float pivotX, float pivotY,
                                 int repeatCount, int repeatMode,
                                 int interpolatorResId) {
        startAnim(view, fromXScale, toXScale, fromYScale, toYScale, pivotX, pivotY,
                repeatCount, repeatMode, interpolatorResId, AnimUtil.DURATION, 0, null);
    }

    public static void startAnim(View view,
                                 float fromXScale, float toXScale,
                                 float fromYScale, float toYScale,
                                 float pivotX, float pivotY) {
        startAnim(view, fromXScale, toXScale, fromYScale, toYScale, pivotX, pivotY,
                0, Animation.REVERSE, android.R.anim.decelerate_interpolator, AnimUtil.DURATION, 0, null);
    }

    public static void startAnim(View view, float fromXScale, float toXScale,
                                 float fromYScale, float toYScale,
                                 float pivotX, float pivotY, AnimationListener listener) {
        startAnim(view, fromXScale, toXScale, fromYScale, toYScale, pivotX, pivotY,
                0, Animation.REVERSE, android.R.anim.decelerate_interpolator, AnimUtil.DURATION, 0, listener);
    }
}
