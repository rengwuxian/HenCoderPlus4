package com.example.app;

import com.example.app.samples.View;
import com.example.core.BaseApplication;
import com.example.core.utils.DpUtils;
import com.example.core.utils.Utils;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Java {

    void func() {
        DpUtils.dp2px(10f);

        BaseApplication.currentApplication();


        Utils.toast("aa");

        final View view = new View();
        view.setOnClickListener(new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view) {
                return null;
            }
        });
    }
}
