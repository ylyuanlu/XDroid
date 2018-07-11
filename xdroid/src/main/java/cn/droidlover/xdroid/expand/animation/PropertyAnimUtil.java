package cn.droidlover.xdroid.expand.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by yuanlu on 2017-06-01.
 */

public class PropertyAnimUtil {

    /**
     * 使用ObjectAnimator需要view的property实现getter和setter方法，如果未实现这两个方法，只能
     * 通过updateListener完成操作
     * @param view
     * @param property
     * @param duration
     * @param interpolator
     * @param repeat
     * @param repeatMode
     * @param listener
     * @param updateListener
     * @param values
     * @param <T>
     * @return
     */
    public static <T> Animator startObjectAnimator(final View view, String property, int duration,
                                                   TimeInterpolator interpolator, int repeat, int repeatMode,
                                                   Animator.AnimatorListener listener,
                                                   ValueAnimator.AnimatorUpdateListener updateListener, T...values) {
        ObjectAnimator animator = null;
        if (int.class.isInstance(values[0])) {
            int[] values1 = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                values1[i] = (Integer) values[i];
            }
            animator = ObjectAnimator.ofInt(view, property, values1);
        } else if (float.class.isInstance(values[0])) {
            float[] values1 = new float[values.length];
            for (int i = 0; i < values.length; i++) {
                values1[i] = (Float) values[i];
            }
            animator = ObjectAnimator.ofFloat(view, property, values1);
        } else if (PropertyValuesHolder.class.isInstance(values[0])) {
            PropertyValuesHolder[] values1 = new PropertyValuesHolder[values.length];
            for (int i = 0; i < values.length; i++) {
                values1[i] = (PropertyValuesHolder) values[i];
            }
            animator = ObjectAnimator.ofPropertyValuesHolder(view, values1);
        }
        initAnimator(animator, duration, interpolator, repeat, repeatMode, listener, updateListener);
        animator.start();
        return animator;
    }

    /**
     * ObjectAnimator.ofPropertyValuesHolder
     * @param view
     * @param duration
     * @param interpolator
     * @param repeat
     * @param repeatMode
     * @param listener
     * @param updateListener
     * @param values
     * @return
     */
    public static Animator startObjectAnimator(final View view, int duration, TimeInterpolator interpolator,
                                                   int repeat, int repeatMode,
                                                   Animator.AnimatorListener listener,
                                                   ValueAnimator.AnimatorUpdateListener updateListener,
                                                   PropertyValuesHolder...values) {
        return startObjectAnimator(view, null, duration, interpolator, repeat, repeatMode, listener, updateListener, values);
    }

    /**
     * 使用ObjectAnimator需要view的property实现getter和setter方法
     * @param view
     * @param property
     * @param duration
     * @param interpolator
     * @param repeat
     * @param repeatMode
     * @param listener
     * @param invalidate
     * @param values
     * @param <T>
     * @return
     */
    public static <T> Animator startObjectAnimator(final View view, String property, int duration,
                                                   TimeInterpolator interpolator, int repeat, int repeatMode,
                                                   Animator.AnimatorListener listener, boolean invalidate, T...values) {
        ValueAnimator.AnimatorUpdateListener updateListener = null;
        if (invalidate) {
            updateListener = new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    view.invalidate();
                }
            };
        }
        return startObjectAnimator(view, property, duration, interpolator,
                repeat, repeatMode, listener, updateListener, values);
    }

    /**
     * ObjectAnimator.ofPropertyValuesHolder
     * @param view
     * @param duration
     * @param interpolator
     * @param repeat
     * @param repeatMode
     * @param listener
     * @param invalidate
     * @param values
     * @return
     */
    public static Animator startObjectAnimator(final View view, int duration, TimeInterpolator interpolator,
                                                   int repeat, int repeatMode, Animator.AnimatorListener listener,
                                                   boolean invalidate, PropertyValuesHolder...values) {
        return startObjectAnimator(view, null, duration, interpolator,
                repeat, repeatMode, listener, invalidate, values);
    }

    public static <T> Animator startObjectAnimator(final View view, String property, int duration,
                                               Animator.AnimatorListener listener, T...values) {
        return startObjectAnimator(view, property, duration, null, 0, -1, listener, false, values);
    }

    public static <T> Animator startObjectAnimator(final View view, String property, int duration, T...values) {
        return startObjectAnimator(view, property, duration, null, values);
    }

    public static <T> Animator startObjectAnimator(final View view, String property,
                                                   ValueAnimator.AnimatorUpdateListener listener, T...values) {
        return startObjectAnimator(view, property, AnimUtil.DURATION, null, 0, -1, null, listener, values);
    }

    public static <T> Animator startObjectAnimator(final View view, String property, boolean invalidate, T...values) {
        return startObjectAnimator(view, property, AnimUtil.DURATION, null, 0, -1, null, invalidate, values);
    }

    public static <T> Animator startObjectAnimator(final View view, String property, T...values) {
        return startObjectAnimator(view, property, null, values);
    }

    public static <T> Animator startObjectAnimator(final View view, T...values) {
        return startObjectAnimator(view, null, values);
    }

    /**
     * 初始化动画方法
     * @param animator
     * @param duration
     * @param interpolator
     * @param repeat
     * @param repeatMode
     * @param listener
     * @param updateListener
     */
    private static void initAnimator(ObjectAnimator animator, int duration, TimeInterpolator interpolator,
                                     int repeat, int repeatMode,
                                     Animator.AnimatorListener listener,
                                     ValueAnimator.AnimatorUpdateListener updateListener) {
        if (animator != null) {
            animator.setDuration(duration);

            if (interpolator != null) {
                animator.setInterpolator(interpolator);
            }

            animator.setRepeatCount(repeat);
            if (repeat != -1) {
                animator.setRepeatMode(repeatMode);
            }

            if (listener != null) {
                animator.addListener(listener);
            }

            /**
             * 如果property属性的变化附带其他操作，可以通过updateListener完成
             */
            if (updateListener != null) {
                animator.addUpdateListener(updateListener);
            }
        }
    }
}
