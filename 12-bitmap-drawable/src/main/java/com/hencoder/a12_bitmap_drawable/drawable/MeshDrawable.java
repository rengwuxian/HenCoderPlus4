package com.hencoder.a12_bitmap_drawable.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hencoder.a12_bitmap_drawable.Utils;

public class MeshDrawable extends Drawable {
  private static final float INTERVAL = Utils.dp2px(80);

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

  {
    paint.setColor(Color.RED);
    paint.setStrokeWidth(Utils.dp2px(5));
  }

  @Override
  public void draw(@NonNull Canvas canvas) {
    Rect bounds = getBounds();
    for (float x = bounds.left; x < bounds.right; x += INTERVAL) {
      canvas.drawLine(x, bounds.top, x, bounds.bottom, paint);
    }
    for (float y = bounds.top; y < bounds.bottom; y += INTERVAL) {
      canvas.drawLine(bounds.left, y, bounds.right, y, paint);
    }
  }

  @Override
  public void setAlpha(int alpha) {
    paint.setAlpha(alpha);
  }

  @Override
  public int getAlpha() {
    return paint.getAlpha();
  }

  @Override
  public void setColorFilter(@Nullable ColorFilter colorFilter) {
    paint.setColorFilter(colorFilter);
  }

  @Override
  public int getOpacity() {
    return paint.getAlpha() == 0 ? PixelFormat.TRANSPARENT :
        paint.getAlpha() == 0xff ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;
  }
}
