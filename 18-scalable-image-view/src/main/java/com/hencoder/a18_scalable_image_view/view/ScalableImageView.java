package com.hencoder.a18_scalable_image_view.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;

import com.hencoder.a18_scalable_image_view.Utils;

public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {
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
  float scaleFraction;
  float maxOffsetX;
  float maxOffsetY;

  GestureDetectorCompat detector;
  ObjectAnimator scaleAnimator;
  OverScroller scroller;

  public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    detector = new GestureDetectorCompat(context, this);
    scroller = new OverScroller(context);
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
    maxOffsetX = (bitmap.getWidth() * bigScale - getWidth()) / 2;
    maxOffsetY = (bitmap.getHeight() * bigScale - getHeight()) / 2;
  }

  public float getScaleFraction() {
    return scaleFraction;
  }

  public void setScaleFraction(float scaleFraction) {
    this.scaleFraction = scaleFraction;
    invalidate();
  }

  private ObjectAnimator getScaleAnimator() {
    if (scaleAnimator == null) {
      scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
    }
    return scaleAnimator;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    return detector.onTouchEvent(event);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.translate(offsetX, offsetY);
    float scale = smallScale + (bigScale - smallScale) * scaleFraction;
    canvas.scale(scale, scale, getWidth() / 2f, getHeight() / 2f);
    canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint);
  }

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
      offsetX = Math.max(Math.min(offsetX, maxOffsetX), -maxOffsetX);
      offsetY -= distanceY;
      offsetY = Math.max(Math.min(offsetY, maxOffsetY), -maxOffsetY);
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
      ViewCompat.postOnAnimation(this, this);
    }
    return false;
  }

  @Override
  public void run() {
    if (scroller.computeScrollOffset()) {
      offsetX = scroller.getCurrX();
      offsetY = scroller.getCurrY();
      invalidate();
      postOnAnimation(this);
    }
  }

  @Override
  public boolean onSingleTapConfirmed(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onDoubleTap(MotionEvent e) {
    big = !big;
    if (big) {
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
