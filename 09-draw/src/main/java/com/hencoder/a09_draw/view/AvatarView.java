package com.hencoder.a09_draw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a09_draw.R;
import com.hencoder.a09_draw.Utils;

public class AvatarView extends View {
  private static final float WIDTH = Utils.dp2px(300);
  private static final float OFFSET = Utils.dp2px(50);

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;
  Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
  RectF bounds = new RectF();

  public AvatarView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  {
    bitmap = getAvatar((int) WIDTH);
    bounds.set(OFFSET, OFFSET, OFFSET + WIDTH, OFFSET + WIDTH);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    float radius = WIDTH / 2f;
    canvas.drawCircle(OFFSET + radius, OFFSET + radius, radius + Utils.dp2px(10), paint);
    int saved = canvas.saveLayer(bounds, paint);
    canvas.drawCircle(OFFSET + radius, OFFSET + radius, radius, paint);
    paint.setXfermode(xfermode);
    canvas.drawBitmap(bitmap, OFFSET, OFFSET, paint);
    paint.setXfermode(null);
    canvas.restoreToCount(saved);
  }

  Bitmap getAvatar(int width) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
    options.inJustDecodeBounds = false;
    options.inDensity = options.outWidth;
    options.inTargetDensity = width;
    return BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
  }
}
