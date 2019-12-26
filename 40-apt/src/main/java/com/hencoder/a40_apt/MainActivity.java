package com.hencoder.a40_apt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hencoder.a40_lib.Binding;
import com.hencoder.lib.BindView;

/**
 * Comment
 * 我喜欢这个类
 */
public class MainActivity extends AppCompatActivity {
  @BindView(R.id.textView) TextView textView;
  @BindView(R.id.parentView) ViewGroup parentView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Binding.bind(this);

    textView.setText("HenCoder Plus!!!!!");
    parentView.setBackgroundColor(Color.CYAN);
  }
}
