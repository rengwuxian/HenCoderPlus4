package com.hencoder.a36_arch.mvvm;

import android.widget.EditText;

import com.hencoder.a36_arch.data.DataCenter;

public class ViewModel {
  StringAttr data0 = new StringAttr();
  StringAttr data1 = new StringAttr();

  ViewModel(EditText editText0, EditText editText1) {
    ViewBinder.bind(editText0, data0);
    ViewBinder.bind(editText1, data1);
  }

  public void load() {
    String[] data = DataCenter.getData();
    data0.setValue(data[0]);
    data1.setValue(data[1]);
  }

  public static class StringAttr {
    private String value;
    OnChangeListener listener;

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
      if (listener != null) {
        listener.onChange(value);
      }
    }

    public void setOnChangeListener(OnChangeListener listener) {
      this.listener = listener;
    }

    interface OnChangeListener {
      void onChange(String newValue);
    }
  }
}
