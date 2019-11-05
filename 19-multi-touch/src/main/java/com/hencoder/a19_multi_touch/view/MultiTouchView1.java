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

public class MultiTouchView1 extends View {
  private static final int IMAGE_WIDTH = (int) Utils.dpToPx(200);

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;

  float offsetX;
  float offsetY;
  float downX;
  float downY;
  float originalOffsetX;
  float originalOffsetY;
  int trackingPointerId;

  public MultiTouchView1(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    bitmap = Utils.getAvatar(getResources(), IMAGE_WIDTH);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    for (int i = 0; i < event.getPointerCount(); i++) {
      event.getX(i);
    }

    switch (event.getActionMasked()) {
      case MotionEvent.ACTION_DOWN:
        trackingPointerId = event.getPointerId(0);
        downX = event.getX();
        downY = event.getY();
        originalOffsetX = offsetX;
        originalOffsetY = offsetY;
        break;
      case MotionEvent.ACTION_MOVE:
        int index = event.findPointerIndex(trackingPointerId);
        offsetX = event.getX(index) - downX + originalOffsetX;
        offsetY = event.getY(index) - downY + originalOffsetY;
        invalidate();
        break;
      case MotionEvent.ACTION_POINTER_DOWN:
        int actionIndex = event.getActionIndex();
        trackingPointerId = event.getPointerId(actionIndex);
        downX = event.getX(actionIndex);
        downY = event.getY(actionIndex);
        originalOffsetX = offsetX;
        originalOffsetY = offsetY;
        break;
      case MotionEvent.ACTION_POINTER_UP:
        actionIndex = event.getActionIndex();
        int pointerId = event.getPointerId(actionIndex);
        if (pointerId == trackingPointerId) { // 0 1 2
          int newIndex;
          if (actionIndex == event.getPointerCount() - 1) {
            newIndex = event.getPointerCount() - 2;
          } else {
            newIndex = event.getPointerCount() - 1;
          }
          trackingPointerId = event.getPointerId(newIndex);
          downX = event.getX(newIndex);
          downY = event.getY(newIndex);
          originalOffsetX = offsetX;
          originalOffsetY = offsetY;
        }
        break;
    }
    return true;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
  }
}
