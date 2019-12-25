package com.example.tracecanarysample;


import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.github.moduth.blockcanary.internal.BlockInfo;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement : Looper.getMainLooper().getThread().getStackTrace()) {
            stringBuilder
                    .append(stackTraceElement.toString())
                    .append(BlockInfo.SEPARATOR);
        }

        Log.e(this.getClass().getName(), stringBuilder.toString());
    }

    public void onClick(View view) {
        a();
        b();
        c();
    }

    public void a() {
        final long start = System.currentTimeMillis();
        SystemClock.sleep(780);
        final long delta = System.currentTimeMillis() - start;
    }

    public void b() {
        SystemClock.sleep(2);
    }

    public void c() {
        SystemClock.sleep(200);
    }
}
