package cn.droidlover.xdroid.expand.animation;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.view.ViewGroup;

/**
 * Created by yuanlu on 2017-06-05.
 */

public class LayoutAnimUtil {

    public static void startAnim(ViewGroup viewGroup, Animator... animators) {
        viewGroup.setLayoutTransition(getLayoutTransition(animators));
    }

    public static LayoutTransition getLayoutTransition(Animator...animators) {
        LayoutTransition transition = new LayoutTransition();
        initLayoutTransition(transition, animators);
        return transition;
    }

    private static void initLayoutTransition(LayoutTransition transition, Animator... animators) {
        transition.setAnimator(LayoutTransition.APPEARING, transition.getAnimator(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, transition.getAnimator(LayoutTransition.CHANGE_APPEARING));
        transition.setAnimator(LayoutTransition.DISAPPEARING, transition.getAnimator(LayoutTransition.DISAPPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, transition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));

        switch (animators.length) {
            case 4:
                if (animators[3] != null) {
                    transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, animators[3]);
                }
            case 3:
                if (animators[2] != null) {
                    transition.setAnimator(LayoutTransition.DISAPPEARING, animators[2]);
                }
            case 2:
                if (animators[1] != null) {
                    transition.setAnimator(LayoutTransition.CHANGE_APPEARING, animators[1]);
                }
            case 1:
                if (animators[0] != null) {
                    transition.setAnimator(LayoutTransition.APPEARING, animators[0]);
                }
        }
    }
}
