package com.hencoder.a10_text_and_transformation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a10_text_and_transformation.Utils;

public class ImageTextView extends View {
  private static final float IMAGE_WIDTH = Utils.dp2px(150);
  private static final float IMAGE_OFFSET = Utils.dp2px(100);

  String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean justo sem, sollicitudin in maximus a, vulputate id magna. Nulla non quam a massa sollicitudin commodo fermentum et est. Suspendisse potenti. Praesent dolor dui, dignissim quis tellus tincidunt, porttitor vulputate nisl. Aenean tempus lobortis finibus. Quisque nec nisl laoreet, placerat metus sit amet, consectetur est. Donec nec quam tortor. Aenean aliquet dui in enim venenatis, sed luctus ipsum maximus. Nam feugiat nisi rhoncus lacus facilisis pellentesque nec vitae lorem. Donec et risus eu ligula dapibus lobortis vel vulputate turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In porttitor, risus aliquam rutrum finibus, ex mi ultricies arcu, quis ornare lectus tortor nec metus. Donec ultricies metus at magna cursus congue. Nam eu sem eget enim pretium venenatis. Duis nibh ligula, lacinia ac nisi vestibulum, vulputate lacinia tortor.";

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;
  float[] cutWidth = new float[1];
  Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

  public ImageTextView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  {
    paint.setTextSize(Utils.dp2px(16));
    paint.getFontMetrics(fontMetrics);
    bitmap = Utils.getAvatar(getResources(), (int) Utils.dp2px(150));
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    // 绘制图片
    canvas.drawBitmap(bitmap, getWidth() - IMAGE_WIDTH, IMAGE_OFFSET, paint);

    // 绘制文字
    int length = text.length();
    float fontSpacing = paint.getFontSpacing();
    float verticalOffset = fontSpacing;
    for (int start = 0, count; start < length; start += count, verticalOffset += fontSpacing) {
      float usableWidth;
      float textTop = verticalOffset + fontMetrics.top;
      float textBottom = verticalOffset + fontMetrics.bottom;
      if (textTop > IMAGE_OFFSET && textTop < (IMAGE_OFFSET + IMAGE_WIDTH)
          || textBottom > IMAGE_OFFSET && textBottom < (IMAGE_OFFSET + IMAGE_WIDTH)) {
        usableWidth = getWidth() - IMAGE_WIDTH;
      } else {
        usableWidth = getWidth();
      }
      count = paint.breakText(text, start, length, true, usableWidth, cutWidth);
      canvas.drawText(text, start, start + count, 0, verticalOffset, paint);
    }
  }
}
