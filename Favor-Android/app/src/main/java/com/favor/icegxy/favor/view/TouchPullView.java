package com.favor.icegxy.favor.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.favor.icegxy.favor.utils.Constant;

/**
 * Created by Icegx on 2017/10/14.
 */

public class TouchPullView extends View {

    private Paint m_oCirclePaint;//绘圆画笔
    private int m_iCircleRadius;//圆半径
    private float m_iCirclePointX, m_iCirclePointY;//圆心坐标
    private float m_fProgress;//进度值
    private int m_iDragHeight = 400;//可拖动高度
    private Paint m_oBezierPaint;//贝塞尔曲线画笔
    private Path m_oBezierPath;//贝塞尔曲线路径
    private int m_iTagetWidth = 200;//目标宽度
    private int m_iTagetGravityHeight;//重心点最终高度，决定控制点Y的坐标
    private int m_iTangentAngle = 120;//切线角度 0~135度

    public TouchPullView(Context context) {
        super(context);
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * 初始化方法
     */
    private void init() {
        m_oCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置抗锯齿
        m_oCirclePaint.setAntiAlias(true);
        //设置防抖动
        m_oCirclePaint.setDither(true);
        //设置填充方式
        m_oCirclePaint.setStyle(Paint.Style.FILL);
        //设置颜色
        m_oCirclePaint.setColor(0xFF000000);
        m_iCircleRadius = Constant.TOUCH_PULL_CIRCLE_RADIUS;

        m_oBezierPath = new Path();
        m_oBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 设置抗锯齿
        m_oBezierPaint.setAntiAlias(true);
        //设置防抖动
        m_oBezierPaint.setDither(true);
        //设置填充方式
        m_oBezierPaint.setStyle(Paint.Style.FILL);
//        m_oBezierPaint.setStrokeWidth(10);
        m_oBezierPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = canvas.save();
        float tranX = (getWidth() - getValueByLine(getWidth(), m_iTagetWidth, m_fProgress)) / 2;
        canvas.translate(tranX, 0);
        canvas.drawPath(m_oBezierPath, m_oBezierPaint);
        canvas.drawCircle(m_iCirclePointX, m_iCirclePointY, m_iCircleRadius, m_oCirclePaint);
        canvas.restoreToCount(count);
    }

    /**
     * 测量控件大小时触发方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int temp_widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int temp_width = MeasureSpec.getSize(widthMeasureSpec);
        int temp_heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int temp_height = MeasureSpec.getSize(heightMeasureSpec);

        int temp_measureWidth;
        int temp_minWidth = 2 * m_iCircleRadius + getPaddingLeft() + getPaddingRight();
        if (temp_widthMode == MeasureSpec.EXACTLY) {
            temp_measureWidth = temp_width;
        } else if (temp_widthMode == MeasureSpec.AT_MOST) {
            temp_measureWidth = Math.min(temp_minWidth, temp_width);
        } else {
            temp_measureWidth = temp_minWidth;
        }

        int temp_measureHeight;
        int temp_minHeight = (int) ((m_iDragHeight * m_fProgress + 0.5f) + getPaddingTop() + getPaddingBottom());
        if (temp_heightMode == MeasureSpec.EXACTLY) {
            temp_measureHeight = temp_height;
        } else if (temp_heightMode == MeasureSpec.AT_MOST) {
            temp_measureHeight = Math.min(temp_minHeight, temp_height);
        } else {
            temp_measureHeight = temp_minHeight;
        }

        //设置测量的高度和宽度
        setMeasuredDimension(temp_measureWidth, temp_measureHeight);
    }

    /**
     * 当控件大小改变时触发方法
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateLayout();
    }

    /**
     * 设置进度
     *
     * @param in_fProgress 进度
     */
    public void setProgress(float in_fProgress) {
        Log.e("TAG", "Progress" + in_fProgress);
        m_fProgress = in_fProgress;
        //请求重新进行布局的测量
        requestLayout();
    }

    private void updateLayout() {
        final float temp_progress = m_fProgress;
        final Path temp_path = m_oBezierPath;
        //获取可绘制区域的宽度和高度
        final float temp_width = getValueByLine(getWidth(), m_iTagetWidth, temp_progress);
        final float temp_height = getValueByLine(0, m_iDragHeight, temp_progress);
        final float temp_radius = m_iCircleRadius;//圆半径
        final float temp_pointX = temp_width / 2;//圆心的x坐标
        final float temp_pointY = temp_height - temp_radius;//圆心的x坐标
        final float temp_endControlY = m_iTagetGravityHeight;//控制点结束处Y坐标
        //更新圆坐标
        m_iCirclePointX = temp_pointX;
        m_iCirclePointY = temp_pointY;
        //复位
        temp_path.reset();
        temp_path.moveTo(0, 0);
        //左侧的结束点和控制点
        float lEndPointX, lEndPointY;
        float lControlPointX, lControlPointY;
        //获取当前切线的弧度
        double radian = Math.toRadians(getValueByLine(0, m_iTangentAngle, temp_progress));
        float x = (float) (Math.sin(radian) * temp_radius);
        float y = (float) (Math.cos(radian) * temp_radius);
        lEndPointX = temp_pointX - x;
        lEndPointY = temp_pointY + y;
        lControlPointY = getValueByLine(0, temp_endControlY, temp_progress);
        float temp_heightY = lControlPointY - lEndPointY;
        float temp_widthX = (float) (temp_heightY / Math.tan(radian));
        lControlPointX = lEndPointX - temp_widthX;
        //贝塞尔曲线
        temp_path.quadTo(lControlPointX, lControlPointY, lEndPointX, lEndPointY);
        temp_path.lineTo(temp_pointX + temp_pointX - lEndPointX, lEndPointY);
        temp_path.quadTo(temp_pointX + temp_pointX - lControlPointX, lControlPointY, temp_width, 0);
    }

    /**
     * 获取当前值
     *
     * @param start
     * @param end
     * @param progress
     * @return
     */
    private float getValueByLine(float start, float end, float progress) {
        return start + (end - start) * progress;
    }
}
