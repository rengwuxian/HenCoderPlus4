package com.hencoder.a12_bitmap_drawable.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.hencoder.a12_bitmap_drawable.R;
import com.hencoder.a12_bitmap_drawable.Utils;

public class MaterialEditText extends androidx.appcompat.widget.AppCompatEditText {
  private static final float TEXT_SIZE = Utils.dp2px(12);
  private static final float TEXT_MARGIN = Utils.dp2px(8);
  private static final float VERTICAL_OFFSET = Utils.dp2px(38);
  private static final float HORIZONTAL_OFFSET = Utils.dp2px(5);
  private static final float EXTRA_OFFSET = Utils.dp2px(16);

  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  boolean floatingLabelShown;
  float floatingLabelFraction;
  boolean useFloatingLabel;
  ObjectAnimator animator;
  Rect backgroundPaddings = new Rect();

  public MaterialEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs){
    TypedArray typedArray = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.colorAccent
        , android.R.attr.layout_width, R.attr.useFloatingLabel});
    int colorPrimary = typedArray.getColor(1, Color.BLACK);
    useFloatingLabel = typedArray.getBoolean(2, true);
    typedArray.recycle();

    refreshPadding();

    paint.setTextSize(TEXT_SIZE);

    addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
          floatingLabelShown = true;
          getAnimator().start();
        } else if (floatingLabelShown && TextUtils.isEmpty(s)) {
          floatingLabelShown = false;
          getAnimator().reverse();
        }
      }
    });
  }

  private ObjectAnimator getAnimator() {
    if (animator == null) {
      animator = ObjectAnimator.ofFloat(MaterialEditText.this, "floatingLabelFraction", 1);
    }
    return animator;
  }

  public float getFloatingLabelFraction() {
    return floatingLabelFraction;
  }

  public void setFloatingLabelFraction(float floatingLabelFraction) {
    this.floatingLabelFraction = floatingLabelFraction;
    invalidate();
  }

  public void setUseFloatingLabel(boolean useFloatingLabel) {
    if (this.useFloatingLabel != useFloatingLabel) {
      this.useFloatingLabel = useFloatingLabel;
      refreshPadding();
    }
  }

  private void refreshPadding() {
    getBackground().getPadding(backgroundPaddings);
    if (useFloatingLabel) {
      setPadding(backgroundPaddings.left, (int) (backgroundPaddings.top + TEXT_SIZE + TEXT_MARGIN), backgroundPaddings.right, backgroundPaddings.bottom);
    } else {
      setPadding(backgroundPaddings.left, backgroundPaddings.top, backgroundPaddings.right, backgroundPaddings.bottom);
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (useFloatingLabel) {
      paint.setAlpha((int) (floatingLabelFraction * 0xff));
      float extraOffset = - EXTRA_OFFSET * floatingLabelFraction;
      canvas.drawText(getHint().toString(), HORIZONTAL_OFFSET, VERTICAL_OFFSET + extraOffset, paint);
    }
  }
}
