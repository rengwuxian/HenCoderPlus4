package com.hencoder.a09_draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a09_draw.Utils;


public class DashboardView extends View {
  private static final float RADIUS = Utils.dp2px(150);
  private static final float STROKE_WIDTH = Utils.dp2px(3);
  private static final float OPEN_ANGLE = 120;
  private static final float POINTER_LENGTH = Utils.dp2px(100);

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Path dash = new Path();
  PathEffect effect;
  Path path = new Path();
  PathMeasure pathMeasure;

  public DashboardView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  {
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(STROKE_WIDTH);
    dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CCW);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);

    path.reset();
    path.addArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS,
        getWidth() / 2f + RADIUS, getHeight() / 2f + RADIUS, 90 + OPEN_ANGLE / 2,
        360 - OPEN_ANGLE);
    pathMeasure = new PathMeasure(path, false);
    effect = new PathDashPathEffect(dash, (pathMeasure.getLength() - Utils.dp2px(2)) / 20f, 0, PathDashPathEffect.Style.ROTATE);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    // 画弧
    canvas.drawArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS,
        getWidth() / 2f + RADIUS, getHeight() / 2f + RADIUS, 90 + OPEN_ANGLE / 2,
        360 - OPEN_ANGLE, false, paint);

    // 画刻度
    paint.setPathEffect(effect);
    canvas.drawArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS,
        getWidth() / 2f + RADIUS, getHeight() / 2f + RADIUS, 90 + OPEN_ANGLE / 2,
        360 - OPEN_ANGLE, false, paint);
    paint.setPathEffect(null);

    // 画指针
    canvas.drawLine(getWidth() / 2f, getHeight() / 2f,
        getWidth() / 2f + POINTER_LENGTH * (float) Math.cos(getAngleFromMark(3)),
        getHeight() / 2f + POINTER_LENGTH * (float) Math.sin(getAngleFromMark(3)),
        paint);
  }

  private double getAngleFromMark(int mark) {
    return Math.toRadians(90 + OPEN_ANGLE / 2 + (360 - OPEN_ANGLE) / 20f * mark);
  }
}
