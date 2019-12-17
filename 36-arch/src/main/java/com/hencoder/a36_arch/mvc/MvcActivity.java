package com.hencoder.a36_arch.mvc;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hencoder.a36_arch.R;
import com.hencoder.a36_arch.data.DataCenter;

public class MvcActivity extends AppCompatActivity {
    IView iView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvc);

        iView = findViewById(R.id.dataView);

        String[] data = DataCenter.getData();
        iView.showData(data[0], data[1]);
    }

    interface IView {
        void showData(String datum0, String datum1);
    }
}