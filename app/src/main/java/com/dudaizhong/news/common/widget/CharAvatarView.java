package com.dudaizhong.news.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class CharAvatarView extends ImageView {
    private static final String TAG = CharAvatarView.class.getName();

    // 颜色画板集
    private static final int[] colors = {
            0xff1abc9c, 0xff16a085, 0xfff1c40f, 0xfff39c12, 0xff2ecc71,
            0xff27ae60, 0xffe67e22, 0xffd35400, 0xff3498db, 0xff2980b9,
            0xffe74c3c, 0xffc0392b, 0xff9b59b6, 0xff8e44ad, 0xffbdc3c7,
            0xff34495e, 0xff2c3e50, 0xff95a5a6, 0xff7f8c8d, 0xffec87bf,
            0xffd870ad, 0xfff69785, 0xff9ba37e, 0xffb49255, 0xffb49255, 0xffa94136
    };

    private Paint mPaintBackground;
    private Paint mPaintText;
    private Rect mRect;
    private String text;
    private int hash;

    public CharAvatarView(Context context) {
        this(context, null);
    }

    public CharAvatarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CharAvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //长宽一致
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null != text) {
            //背景
            int color = colors[hash % colors.length];
            mPaintBackground.setColor(color);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaintBackground);

            //写字
            mPaintText.setColor(Color.WHITE);
            mPaintText.setTextSize((getWidth() / 3));
            mPaintText.setStrokeWidth(3);
            mPaintText.getTextBounds(text, 0, 1, mRect);

            // 垂直居中
            Paint.FontMetricsInt fontMetrics = mPaintText.getFontMetricsInt();
            int baseline = (getMeasuredHeight() - fontMetrics.bottom - fontMetrics.top) / 2;
            // 左右居中
            mPaintText.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(text, getWidth() / 2, baseline, mPaintText);
        }
    }

    public void setText(String content) {
        if (content == null) {
            content = " ";
        }
        char chs[] = content.toCharArray();
        if (chs.length > 1) {
            this.text = String.valueOf(chs[chs.length - 2]) + String.valueOf(chs[chs.length - 1]);
        } else {
            this.text = String.valueOf(chs[0]);
        }
        this.text = text.toUpperCase();
        Log.e(TAG, text);
        hash = this.text.hashCode();

        invalidate();
    }

}