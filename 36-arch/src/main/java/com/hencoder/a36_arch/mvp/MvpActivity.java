package com.hencoder.a36_arch.mvp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hencoder.a36_arch.R;

public class MvpActivity extends AppCompatActivity implements Presenter.IView {
    EditText data0View;
    EditText data1View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data0View = findViewById(R.id.data0View);
        data1View = findViewById(R.id.data1View);

        new Presenter(this).load();
    }

    @Override
    public void showData(String datum0, String datum1) {
        data0View.setText(datum0);
        data1View.setText(datum1);
    }
}