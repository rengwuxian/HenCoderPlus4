package com.hencoder.a16_layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a16_layout.Utils;

public class CircleView extends View {
  private static final float RADIUS = Utils.dpToPixel(80);
  private static final float PADDING = Utils.dpToPixel(30);

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public CircleView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int size = (int) ((PADDING + RADIUS) * 2);
    int width = resolveSize(size, widthMeasureSpec);
    int height = resolveSize(size, heightMeasureSpec);

    setMeasuredDimension(width, height);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawColor(Color.RED);
    canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint);
  }
}
