package com.hencoder.a18_scalable_image_view.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ScaleGestureDetectorCompat;
import androidx.core.view.ViewCompat;

import com.hencoder.a18_scalable_image_view.Utils;

public class ScalableImageView extends View {
  private static final float IMAGE_WIDTH = Utils.dpToPixel(300);
  private static final float OVER_SCALE_FACTOR = 1.5f;

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;

  float originalOffsetX;
  float originalOffsetY;
  float offsetX;
  float offsetY;
  float bigScale;
  float smallScale;
  boolean big;
  float currentScale;
  float maxOffsetX;
  float maxOffsetY;

  GestureDetectorCompat detector;
  ObjectAnimator scaleAnimator;
  OverScroller scroller;
  GestureDetector.OnGestureListener henGestureListener = new HenGestureListener();
  Runnable henAnimationRunner = new HenRunner();
  ScaleGestureDetector scaleDetector;
  ScaleGestureDetector.OnScaleGestureListener henScaleListener = new HenScaleListener();

  public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    detector = new GestureDetectorCompat(context, henGestureListener);
    scroller = new OverScroller(context);
    scaleDetector = new ScaleGestureDetector(context, henScaleListener);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);

    originalOffsetX = (getWidth() - bitmap.getWidth()) / 2f;
    originalOffsetY = (getHeight() - bitmap.getHeight()) / 2f;

    if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {
      smallScale = (float) getWidth() / bitmap.getWidth();
      bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
    } else {
      smallScale = (float) getHeight() / bitmap.getHeight();
      bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
    }
    currentScale = smallScale;
    maxOffsetX = (bitmap.getWidth() * bigScale - getWidth()) / 2;
    maxOffsetY = (bitmap.getHeight() * bigScale - getHeight()) / 2;
  }

  public float getCurrentScale() {
    return currentScale;
  }

  public void setCurrentScale(float currentScale) {
    this.currentScale = currentScale;
    invalidate();
  }

  private ObjectAnimator getScaleAnimator() {
    if (scaleAnimator == null) {
      scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0);
    }
    scaleAnimator.setFloatValues(smallScale, bigScale);
    return scaleAnimator;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    boolean result = scaleDetector.onTouchEvent(event);
    if (!scaleDetector.isInProgress()) {
      result = detector.onTouchEvent(event);
    }
    return result;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
    canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
    canvas.scale(currentScale, currentScale, getWidth() / 2f, getHeight() / 2f);
    canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint);
  }

  private void fixOffsets() {
    offsetX = Math.max(Math.min(offsetX, maxOffsetX), -maxOffsetX);
    offsetY = Math.max(Math.min(offsetY, maxOffsetY), -maxOffsetY);
  }

  private class HenGestureListener extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onDown(MotionEvent e) {
      return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
      return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
      if (big) {
        offsetX -= distanceX;
        offsetY -= distanceY;
        fixOffsets();
        invalidate();
      }
      return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      if (big) {
        scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
            -(int) maxOffsetX, (int) maxOffsetX, -(int) maxOffsetY, (int) maxOffsetY,
            (int) Utils.dpToPixel(50), (int) Utils.dpToPixel(50));
        ViewCompat.postOnAnimation(ScalableImageView.this, henAnimationRunner);
      }
      return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
      return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
      big = !big;
      if (big) {
        offsetX = (e.getX() - getWidth() / 2f) * (1 - bigScale / smallScale);
        offsetY = (e.getY() - getHeight() / 2f) * (1 - bigScale / smallScale);
        fixOffsets();
        getScaleAnimator().start();
      } else {
        getScaleAnimator().reverse();
      }
      return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
      return false;
    }
  }

  private class HenRunner implements Runnable {
    @Override
    public void run() {
      if (scroller.computeScrollOffset()) {
        offsetX = scroller.getCurrX();
        offsetY = scroller.getCurrY();
        invalidate();
        postOnAnimation(this);
      }
    }
  }

  private class HenScaleListener implements ScaleGestureDetector.OnScaleGestureListener {
    private float initialCurrentScale;

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
      currentScale = initialCurrentScale * detector.getScaleFactor();
      invalidate();
      return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
      initialCurrentScale = currentScale;
      return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }
  }
}
