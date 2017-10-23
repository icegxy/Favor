package com.favor.icegxy.favor.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Icegx on 2017/10/14.
 * 实现贝塞尔曲线
 */

public class BezierView extends View {

    private Paint m_oBezierPaint;
    private Path m_oBezierPath;

    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        m_oBezierPath = new Path();
        m_oBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置抗锯齿
        m_oBezierPaint.setAntiAlias(true);
        //设置防抖动
        m_oBezierPaint.setDither(true);
        //设置填充方式
        m_oBezierPaint.setStyle(Paint.Style.STROKE);
        m_oBezierPaint.setStrokeWidth(10);

        initBezier();
    }

    private void initBezier() {
        float[] xPoints = new float[]{0, 300, 200, 500, 700};
        float[] yPoints = new float[]{0, 300, 700, 1200, 200};

        int fps = 3000;
        for (int i = 0; i <= fps; i++) {
            float progress = i / (float) fps;
            float x = calBezier(progress, xPoints);
            float y = calBezier(progress, yPoints);
            m_oBezierPath.lineTo(x, y);
        }

    }

    /**
     * 计算某时刻贝塞尔所处的值(x或y)
     *
     * @param time  时间（0~1）
     * @param value 贝塞尔集合点（x或y）
     * @return 当前时刻的贝塞尔所处点
     */
    private float calBezier(float time, float... value) {
        final int len = value.length;
        for (int i = len - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                value[j] = value[j] + (value[j + 1] - value[j]) * time;
            }
        }

        return value[0];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(m_oBezierPath, m_oBezierPaint);
    }
}
