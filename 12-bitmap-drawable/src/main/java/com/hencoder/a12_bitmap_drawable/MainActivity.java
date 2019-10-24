package com.hencoder.a12_bitmap_drawable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hencoder.a12_bitmap_drawable.view.MaterialEditText;

public class MainActivity extends AppCompatActivity {
  MaterialEditText met;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    met = findViewById(R.id.met);
  }
}
