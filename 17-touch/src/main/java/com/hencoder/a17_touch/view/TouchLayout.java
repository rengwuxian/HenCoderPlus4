package com.hencoder.a17_touch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class TouchLayout extends ViewGroup {
  int downY;
  int SLOP;

  public TouchLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean shouldDelayChildPressedState() {
    return false;
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {

  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
      downY = (int) ev.getY();
    }
    int delta = (int) (ev.getY() - downY);
    if (Math.abs(delta) < SLOP) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    return super.onTouchEvent(event);
  }
}
