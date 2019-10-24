package com.hencoder.a11_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.hencoder.a11_animation.view.CameraView;
import com.hencoder.a11_animation.view.CircleView;
import com.hencoder.a11_animation.view.PointView;
import com.hencoder.a11_animation.view.ProvinceView;

public class MainActivity extends AppCompatActivity {
  ProvinceView view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    view = findViewById(R.id.view);

    /*ObjectAnimator animator = ObjectAnimator.ofFloat(view, "radius", Utils.dp2px(200));
    animator.setStartDelay(2000);
    animator.start();*/

    /*ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 45);
    bottomFlipAnimator.setDuration(1000);
    bottomFlipAnimator.setStartDelay(1000);

    ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", -45);
    topFlipAnimator.setDuration(1000);
    topFlipAnimator.setStartDelay(1000);

    ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 270);
    flipRotationAnimator.setDuration(1000);
    flipRotationAnimator.setStartDelay(1000);

    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator);
    animatorSet.setStartDelay(1000);
    animatorSet.start();*/

    /*PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 45);
    PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -45);
    PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 270);
    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, bottomFlipHolder, topFlipHolder, flipRotationHolder);
    animator.setDuration(1000);
    animator.setStartDelay(1000);
    animator.start();*/

    /*ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", Utils.dp2px(200));
    animator.setStartDelay(2000);
    animator.setDuration(2000);
    animator.start();*/

    /*float length = Utils.dp2px(300);
    Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
    Keyframe keyframe2 = Keyframe.ofFloat(0.1f, 1.5f * length);
    Keyframe keyframe3 = Keyframe.ofFloat(0.9f, 0.6f * length);
    Keyframe keyframe4 = Keyframe.ofFloat(1f, 1f * length);
    PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("translationX",
        keyframe1, keyframe2, keyframe3, keyframe4);
    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, valuesHolder);
    animator.setStartDelay(2000);
    animator.setDuration(2000);
    animator.setInterpolator(new LinearInterpolator());
    animator.start();*/

    /*PointF targetPoint = new PointF(Utils.dp2px(300), Utils.dp2px(200));
    ObjectAnimator animator = ObjectAnimator.ofObject(view, "point", new PointFEvaluator(), targetPoint);
    animator.setStartDelay(2000);
    animator.setDuration(2000);
    animator.start();*/

    ObjectAnimator animator = ObjectAnimator.ofObject(view, "province", new ProvinceUtil.ProvinceEvaluator(), "澳门特别行政区");
    animator.setStartDelay(2000);
    animator.setDuration(10000);
    animator.start();

    view.animate()
        .translationX(200)
        .rotation(180)
        .withLayer()
        .alpha(0.5f);
  }

  static class PointFEvaluator implements TypeEvaluator<PointF> {
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
      float x = startValue.x + (endValue.x - startValue.x) * fraction;
      float y = startValue.y + (endValue.y - startValue.y) * fraction;
      return new PointF(x, y);
    }
  }
}
