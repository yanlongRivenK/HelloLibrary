package com.yanlongrivenk.simpleprogressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yanlongRivenK on 2017/8/25.
 */

public class CircleProgressView extends View {

    private Paint mPaint;
    private RectF mRectF;
    private StringBuilder text = new StringBuilder("");
    protected Rect mTextBounds;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        mPaint.setColor(circleColor);
        mPaint.setStrokeWidth(circleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mPaint.setColor(circleColor);
        mRectF = new RectF(circleWidth, circleWidth, mViewWidth - circleWidth, mViewHeight - circleWidth);
        canvas.drawArc(mRectF, startAngle, sweepAngle, false, mPaint);

        mPaint.setColor(progressColor);
        canvas.drawArc(mRectF, startAngle, sweepAngle * progressPercent, false, mPaint);

        mPaint.setStrokeWidth(1);
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);

        text.setLength(0);
        text.append((int)(progressPercent * 100) + "%");

        Rect textBound = new Rect();
        mPaint.getTextBounds(text.toString(), 0, text.length(), textBound);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        int x = (getWidth() - textBound.width()) / 2;
        //baseline
        int baseline = (int)(getHeight() / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
        canvas.drawText(text.toString(), x, baseline, mPaint);

    }

    public void drawProgress(float progressPercent) {
        this.progressPercent = progressPercent;
        invalidate();
    }

    private float progressPercent = 0;
    //进度园的颜色
    private int progressColor = Color.parseColor("#ffff00ff");
    //绘制起点,绘制角度
    private float startAngle = -180;
    private float sweepAngle = 360;
    //圆弧宽高
    private int mViewWidth = 400;
    private int mViewHeight = 400;
    //画笔宽度
    private int circleWidth = 10;
    //画笔颜色
    private int circleColor = Color.parseColor("#ff00ff00");
    //文字
    private int textColor = Color.parseColor("#fff45678");
    private int textSize = 200;

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }


    public void setProgressColor(int color) {
        this.progressColor = color;
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    public void setCircleWidth(int circleWidth) {
        mPaint.setStrokeWidth(circleWidth);
        this.circleWidth = circleWidth;
    }

    public void setCircleColor(int circleColor) {
        mPaint.setColor(circleColor);
        this.circleColor = circleColor;
    }

    public void setWidth(int width) {
        mViewWidth = width;
    }

    public void setHeight(int height) {
        mViewHeight = height;
    }

    public int getProgress(){
        return ((int) (progressPercent * 100));
    }
}
