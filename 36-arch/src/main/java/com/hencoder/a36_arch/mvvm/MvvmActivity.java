package com.hencoder.a36_arch.mvvm;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hencoder.a36_arch.R;
import com.hencoder.a36_arch.mvp.Presenter;

public class MvvmActivity extends AppCompatActivity {
    EditText data0View;
    EditText data1View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data0View = findViewById(R.id.data0View);
        data1View = findViewById(R.id.data1View);

        new ViewModel(data0View, data1View).load();
    }
}