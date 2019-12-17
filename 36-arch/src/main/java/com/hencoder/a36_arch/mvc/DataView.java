package com.hencoder.a36_arch.mvc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.hencoder.a36_arch.R;

public class DataView extends LinearLayout implements MvcActivity.IView {
  EditText data0View;
  EditText data1View;

  public DataView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    data0View = findViewById(R.id.data0View);
    data1View = findViewById(R.id.data1View);
  }

  @Override
  public void showData(String datum0, String datum1) {
    data0View.setText(datum0);
    data1View.setText(datum1);
  }
}
