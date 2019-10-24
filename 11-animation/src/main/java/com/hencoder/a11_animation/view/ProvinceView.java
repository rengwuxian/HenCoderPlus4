package com.hencoder.a11_animation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a11_animation.Utils;

public class ProvinceView extends View {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

  String province = "北京市";

  public ProvinceView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  {
    paint.setTextSize(Utils.dp2px(50));
    paint.setTextAlign(Paint.Align.CENTER);
    setLayerType(LAYER_TYPE_HARDWARE, null);
    setLayerType(LAYER_TYPE_SOFTWARE, null);
    setLayerType(LAYER_TYPE_NONE, null);
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawText(province, getWidth() / 2, getHeight() / 2, paint);
  }
}
