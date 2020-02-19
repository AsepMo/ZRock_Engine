package zrock.application.engine.animation;

import zrock.application.engine.R;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.view.View;

import java.lang.ref.WeakReference;

@SuppressWarnings("Unused")
public class CardsTransitionController extends TransitionController {

    CardsTransitionController(WeakReference<Activity> mainActivityWeakReference, AnimatorBuilder animatorBuilder) {
        super(mainActivityWeakReference, animatorBuilder);
    }

    public static TransitionController newInstance(Activity activity) {
        WeakReference<Activity> mainActivityWeakReference = new WeakReference<>(activity);
        AnimatorBuilder animatorBuilder = AnimatorBuilder.newInstance(activity);
        return new CardsTransitionController(mainActivityWeakReference, animatorBuilder);
    }

    @Override
    protected void enterInputMode(Activity activity) {
        View inputView = activity.findViewById(R.id.input_view);
        View inputDone = activity.findViewById(R.id.input_done);
        View translation = activity.findViewById(R.id.translation_panel);
        View toolbar = activity.findViewById(R.id.toolbar);

        inputDone.setVisibility(View.VISIBLE);

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorBuilder animatorBuilder = getAnimatorBuilder();
        Animator moveInputView = animatorBuilder.buildTranslationYAnimator(inputView, 0, -toolbar.getHeight());
        Animator showInputDone = animatorBuilder.buildShowAnimator(inputDone);
        Animator hideTranslation = animatorBuilder.buildHideAnimator(translation);
        animatorSet.playTogether(moveInputView, showInputDone, hideTranslation);
        animatorSet.start();
    }

    @Override
    protected void exitInputMode(Activity activity) {
        final View inputView = activity.findViewById(R.id.input_view);
        View inputDone = activity.findViewById(R.id.input_done);
        View translation = activity.findViewById(R.id.translation_panel);
        View toolbar = activity.findViewById(R.id.toolbar);

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorBuilder animatorBuilder = getAnimatorBuilder();
        Animator moveInputView = animatorBuilder.buildTranslationYAnimator(inputView, -toolbar.getHeight(), 0);
        Animator hideInputDone = animatorBuilder.buildHideAnimator(inputDone);
        Animator showTranslation = animatorBuilder.buildShowAnimator(translation);
        animatorSet.playTogether(moveInputView, hideInputDone, showTranslation);
        animatorSet.addListener(new ImeCloseListener(inputDone));
        animatorSet.start();
    }
}
