package com.hencoder.a36_arch;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hencoder.a36_arch.data.DataCenter;

public class MainActivity extends AppCompatActivity {
    EditText data0View;
    EditText data1View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data0View = findViewById(R.id.data0View);
        data1View = findViewById(R.id.data1View);

        String[] data = DataCenter.getData();
        data0View.setText(data[0]);
        data1View.setText(data[1]);
    }
}