package com.example.tracecanarysample;

import android.app.Application;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;

import hugo.weaving.DebugLog;

public class App extends Application {
    public String getHenCoder() {
        return "henCoder";
    }

    @DebugLog
    @Override
    public void onCreate() {
        super.onCreate();

        BlockCanary.install(this, new BlockCanaryContext()).start();
    }
}
