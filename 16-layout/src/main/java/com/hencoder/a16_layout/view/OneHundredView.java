package com.hencoder.a16_layout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class OneHundredView extends View {
  public OneHundredView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public void layout(int l, int t, int r, int b) {
    super.layout(l, t, l + 100, t + 100);
  }
}
