package cn.droidlover.xdroid.expand.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by yuanlu on 2017-05-23.
 */

public class FrameAnimUtil {
    public static final int DURATION = 200;
    private static AnimationDrawable sAnimationDrawable;

    /**
     * 通过系列图片和指定参数定义动画
     * @param imageViews
     * @param resIds
     * @param oneShot
     * @param duration
     */
    public static void startAnim(ImageView imageViews, int[] resIds, boolean oneShot, int duration) {
        if (sAnimationDrawable == null) {
            sAnimationDrawable = new AnimationDrawable();
        }

        for (int i = 0; i < resIds.length; i++) {
            Drawable drawable = imageViews.getContext().getResources().getDrawable(resIds[i]);
            sAnimationDrawable.addFrame(drawable, duration);
        }

        sAnimationDrawable.setOneShot(oneShot);
        imageViews.setImageDrawable(sAnimationDrawable);
        sAnimationDrawable.start();
    }

    public static void startAnim(ImageView imageViews, int[] resIds, boolean oneShot) {
        startAnim(imageViews, resIds, oneShot, DURATION);
    }

    public static void startAnim(ImageView imageViews, int[] resIds) {
        startAnim(imageViews, resIds, true, DURATION);
    }

    public static void startAnim(ImageView imageViews, int[] resIds, int duration) {
        startAnim(imageViews, resIds, true, duration);
    }

    /**
     * 通过动画资源ID指定动画效果
     * @param imageViews
     * @param resId
     */
    public static void startAnim(ImageView imageViews, int resId) {
        imageViews.setImageResource(resId);
        sAnimationDrawable = (AnimationDrawable) imageViews.getDrawable();
        sAnimationDrawable.start();
    }

    public static void stopAnim() {
        if (sAnimationDrawable != null) {
            sAnimationDrawable.stop();
        }
        sAnimationDrawable = null;
    }
}
