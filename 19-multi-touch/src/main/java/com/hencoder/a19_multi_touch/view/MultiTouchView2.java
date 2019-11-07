package com.hencoder.a19_multi_touch.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a19_multi_touch.Utils;

public class MultiTouchView2 extends View {
  private static final int IMAGE_WIDTH = (int) Utils.dpToPx(200);

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;

  float offsetX;
  float offsetY;
  float downX;
  float downY;
  float originalOffsetX;
  float originalOffsetY;

  public MultiTouchView2(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    bitmap = Utils.getAvatar(getResources(), IMAGE_WIDTH);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float sumX = 0;
    float sumY = 0;
    boolean isPointerUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
    for (int i = 0; i < event.getPointerCount(); i++) {
      if (!isPointerUp || i != event.getActionIndex()) {
        sumX += event.getX(i);
        sumY += event.getY(i);
      }
    }
    int pointerCount = event.getPointerCount();
    if (isPointerUp) {
      pointerCount--;
    }
    float focusX = sumX / pointerCount;
    float focusY = sumY / pointerCount;
    switch (event.getActionMasked()) {
      case MotionEvent.ACTION_DOWN:
      case MotionEvent.ACTION_POINTER_DOWN:
      case MotionEvent.ACTION_POINTER_UP:
        downX = focusX;
        downY = focusY;
        originalOffsetX = offsetX;
        originalOffsetY = offsetY;
        break;
      case MotionEvent.ACTION_MOVE:
        offsetX = focusX - downX + originalOffsetX;
        offsetY = focusY - downY + originalOffsetY;
        invalidate();
        break;
    }
    return true;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
  }
}
