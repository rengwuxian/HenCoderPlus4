package com.hencoder.a10_text_and_transformation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a10_text_and_transformation.Utils;

public class CameraView extends View {
  private static final float IMAGE_WIDTH = Utils.dp2px(200);
  private static final float IMAGE_OFFSET = Utils.dp2px(100);

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;
  Camera camera = new Camera();

  public CameraView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  {
    bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    camera.rotateX(30);
    camera.setLocation(0, 0, Utils.getZForCamera()); // -8 * 72
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.save();
    canvas.translate(IMAGE_OFFSET + IMAGE_WIDTH / 2f, IMAGE_OFFSET + IMAGE_WIDTH / 2f);
    canvas.rotate(-30);
    canvas.clipRect(- IMAGE_WIDTH, - IMAGE_WIDTH, IMAGE_WIDTH, 0);
    canvas.rotate(30);
    canvas.translate(- (IMAGE_OFFSET + IMAGE_WIDTH / 2f), - (IMAGE_OFFSET + IMAGE_WIDTH / 2f));
    canvas.drawBitmap(bitmap, IMAGE_OFFSET, IMAGE_OFFSET, paint);
    canvas.restore();

    canvas.save();
    canvas.translate(IMAGE_OFFSET + IMAGE_WIDTH / 2f, IMAGE_OFFSET + IMAGE_WIDTH / 2f);
    canvas.rotate(-30);
    camera.applyToCanvas(canvas);
    canvas.clipRect(- IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
    canvas.rotate(30);
    canvas.translate(- (IMAGE_OFFSET + IMAGE_WIDTH / 2f), - (IMAGE_OFFSET + IMAGE_WIDTH / 2f));
    canvas.drawBitmap(bitmap, IMAGE_OFFSET, IMAGE_OFFSET, paint);
    canvas.restore();
  }
}
