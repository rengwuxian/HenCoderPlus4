package com.hencoder.a10_text_and_transformation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hencoder.a10_text_and_transformation.Utils;

public class SportView extends View {
    private static final float RING_WIDTH = Utils.dp2px(20);
    private static final float RADIUS = Utils.dp2px(150);
    private static final int CIRCLE_COLOR = Color.parseColor("#90A4AE");
    private static final int HIGHLIGHT_COLOR = Color.parseColor("#FF4081");

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String text = "abab";
    private Rect bounds = new Rect();
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setTextSize(Utils.dp2px(100));
        paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Quicksand-Regular.ttf"));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制环
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(CIRCLE_COLOR);
        paint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, RADIUS, paint);

        // 绘制进度条
        paint.setColor(HIGHLIGHT_COLOR);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth() / 2f - RADIUS, getHeight() / 2f - RADIUS, getWidth() / 2f + RADIUS, getHeight() / 2f + RADIUS, -90, 225, false, paint);
        paint.setStrokeCap(Paint.Cap.BUTT);

        // 绘制文字
        paint.setStyle(Paint.Style.FILL);
//        paint.getTextBounds(text, 0, text.length(), bounds);
//        int offset = (bounds.top + bounds.bottom) / 2;
        paint.getFontMetrics(fontMetrics);
        float offset = (fontMetrics.descent + fontMetrics.ascent) / 2;
        canvas.drawText(text, getWidth() / 2f, getHeight() / 2f - offset, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), bounds);
        paint.setTextSize(Utils.dp2px(150));
        canvas.drawText(text, - bounds.left, - bounds.top, paint);
        paint.setTextSize(Utils.dp2px(15));
        paint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, - bounds.left, - bounds.top + paint.getFontSpacing(), paint);
    }
}
