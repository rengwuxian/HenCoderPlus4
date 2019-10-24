package com.hencoder.a12_bitmap_drawable.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a12_bitmap_drawable.drawable.CandleDrawable;

public class DetailedCandleView extends View {
  CandleDrawable candleDrawable;

  public DetailedCandleView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    candleDrawable.draw(canvas);

    // 绘制基本信息

    // 绘制详细信息
  }
}
