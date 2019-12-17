package com.hencoder.a36_arch.mvvm;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

public class ViewBinder {
  public static void bind(final EditText editText, final ViewModel.StringAttr data) {
    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        if (!TextUtils.equals(s, data.getValue())) {
          data.setValue(s.toString());
          System.out.println("ViewBinder: 字符串被关联改变了！" + s.toString());
        }
      }
    });
    data.setOnChangeListener(new ViewModel.StringAttr.OnChangeListener() {
      @Override
      public void onChange(String newValue) {
        if (!TextUtils.equals(editText.getText(), data.getValue())) {
          editText.setText(data.getValue());
          System.out.println("ViewBinder: 界面被关联改变了！" + newValue);
        }
      }
    });
  }
}
