package com.hencoder.a11_animation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a11_animation.Utils;

public class CameraView extends View {
  private static final float IMAGE_WIDTH = Utils.dp2px(200);
  private static final float IMAGE_OFFSET = Utils.dp2px(100);

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;
  Camera camera = new Camera();

  float topFlip = 0;
  float bottomFlip = 0;
  float flipRotation = 0;

  public CameraView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  {
    bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    camera.setLocation(0, 0, Utils.getZForCamera()); // -8 * 72
  }

  public float getTopFlip() {
    return topFlip;
  }

  public void setTopFlip(float topFlip) {
    this.topFlip = topFlip;
    invalidate();
  }

  public float getBottomFlip() {
    return bottomFlip;
  }

  public void setBottomFlip(float bottomFlip) {
    this.bottomFlip = bottomFlip;
    invalidate();
  }

  public float getFlipRotation() {
    return flipRotation;
  }

  public void setFlipRotation(float flipRotation) {
    this.flipRotation = flipRotation;
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.save();
    canvas.translate(IMAGE_OFFSET + IMAGE_WIDTH / 2f, IMAGE_OFFSET + IMAGE_WIDTH / 2f);
    canvas.rotate(-flipRotation);
    camera.save();
    camera.rotateX(topFlip);
    camera.applyToCanvas(canvas);
    camera.restore();
    canvas.clipRect(- IMAGE_WIDTH, - IMAGE_WIDTH, IMAGE_WIDTH, 0);
    canvas.rotate(flipRotation);
    canvas.translate(- (IMAGE_OFFSET + IMAGE_WIDTH / 2f), - (IMAGE_OFFSET + IMAGE_WIDTH / 2f));
    canvas.drawBitmap(bitmap, IMAGE_OFFSET, IMAGE_OFFSET, paint);
    canvas.restore();

    canvas.save();
    canvas.translate(IMAGE_OFFSET + IMAGE_WIDTH / 2f, IMAGE_OFFSET + IMAGE_WIDTH / 2f);
    canvas.rotate(-flipRotation);
    camera.save();
    camera.rotateX(bottomFlip);
    camera.applyToCanvas(canvas);
    camera.restore();
    canvas.clipRect(- IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
    canvas.rotate(flipRotation);
    canvas.translate(- (IMAGE_OFFSET + IMAGE_WIDTH / 2f), - (IMAGE_OFFSET + IMAGE_WIDTH / 2f));
    canvas.drawBitmap(bitmap, IMAGE_OFFSET, IMAGE_OFFSET, paint);
    canvas.restore();
  }
}
