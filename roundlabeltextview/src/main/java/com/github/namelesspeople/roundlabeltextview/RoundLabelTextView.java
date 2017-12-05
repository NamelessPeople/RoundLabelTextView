package com.github.namelesspeople.roundlabeltextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by namelesspeople on 2017/12/1
 */

public class RoundLabelTextView extends View {


    float labelLength;

    float radius;
    int backGroundColor;

    int contentTextColor;
    float contentTextSize;
    float contentMarginStart;
    float contentMarginBottom;
    float contentTextHeight;
    float contentTextWidth;
    int contentTextStyle;
    String contentText;


    float topTextSize;
    int topTextColor;
    float topMarginStart;
    float topMarginBottom;
    float topTextHeight;
    float topTextWidth;
    String topText;
    int topTextStyle;
    int angle;

    boolean textFlip;


    public RoundLabelTextView(Context context) {
        this(context, null);

    }

    public RoundLabelTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundLabelTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundLabelTextView);

        labelLength = typedArray.getDimension(R.styleable.RoundLabelTextView_labelLength, dp2px(50));
        radius = typedArray.getDimension(R.styleable.RoundLabelTextView_radius, dp2px(10));
        backGroundColor = typedArray.getColor(R.styleable.RoundLabelTextView_backGroundColor, Color.RED);
        angle = typedArray.getInt(R.styleable.RoundLabelTextView_angle, 2);
        textFlip= typedArray.getBoolean(R.styleable.RoundLabelTextView_textFlip, false);

        contentTextSize = typedArray.getDimension(R.styleable.RoundLabelTextView_contentTextSize, sp2px(15));
        contentTextColor = typedArray.getColor(R.styleable.RoundLabelTextView_contentTextColor, Color.BLACK);
        contentText = typedArray.getString(R.styleable.RoundLabelTextView_contentText);
        contentMarginStart = typedArray.getDimension(R.styleable.RoundLabelTextView_contentMarginStart, dp2px(0));
        contentMarginBottom = typedArray.getDimension(R.styleable.RoundLabelTextView_contentMarginBottom, dp2px(0));
        contentTextStyle = typedArray.getInt(R.styleable.RoundLabelTextView_contentTextStyle, 0);

        topTextSize = typedArray.getDimension(R.styleable.RoundLabelTextView_topTextSize, sp2px(15));
        topTextColor = typedArray.getColor(R.styleable.RoundLabelTextView_topTextColor, Color.BLACK);
        topText = typedArray.getString(R.styleable.RoundLabelTextView_topText);
        topMarginStart = typedArray.getDimension(R.styleable.RoundLabelTextView_topMarginStart, dp2px(0));
        topMarginBottom = typedArray.getDimension(R.styleable.RoundLabelTextView_topMarginBottom, dp2px(0));
        topTextStyle = typedArray.getInt(R.styleable.RoundLabelTextView_topTextStyle, 0);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) labelLength, (int) labelLength);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        switch (angle) {
            case 1:
                canvas.rotate(90, labelLength / 2, labelLength / 2);
                break;
            case 3:
                canvas.rotate(-90, labelLength / 2, labelLength / 2);
                break;
            case 4:
                canvas.rotate(180, labelLength / 2, labelLength / 2);
                break;
            case 2:
            default:

                break;
        }
        drawShape(canvas);
        drawContentText(canvas);
        drawTopText(canvas);
        canvas.restore();
    }


    private void drawShape(Canvas canvas) {

        float[] radii = {radius, radius, 0, 0, 0f, 0f, 0f, 0f};
        Paint paint = new Paint();
        paint.setColor(backGroundColor);
        //绘制三角形背景
        Path path = new Path();
        RectF roundRect = new RectF();
        roundRect.set(0, 0, radius, radius);
        path.addRoundRect(roundRect, radii, Path.Direction.CCW);
        path.moveTo(0, radius);
        path.lineTo(0, labelLength);
        path.lineTo(labelLength, 0);
        path.lineTo(radius, 0);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawContentText(Canvas canvas) {
        // 测量文字高度
        Rect rectText = new Rect();
        Paint contentPaint = new Paint();
        switch (contentTextStyle) {
            case 1:
                contentPaint.setTypeface(Typeface.SANS_SERIF);
                break;
            case 2:
                contentPaint.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            default:
                contentPaint.setTypeface(Typeface.DEFAULT);
                break;
        }
        contentPaint.setColor(contentTextColor);
        contentPaint.setTextSize(contentTextSize);
        contentPaint.getTextBounds(contentText, 0, contentText.length(), rectText);
        contentTextWidth = rectText.width();
        contentTextHeight = contentTextSize;
        if (textFlip && (angle == 3 || angle == 4)) {
            canvas.rotate(135, labelLength / 2, labelLength / 2);
            canvas.translate(0, labelLength / 2);
        } else {
            canvas.rotate(-45, labelLength / 2, labelLength / 2);
            canvas.translate(0, labelLength / 2 - contentTextHeight);
        }
        canvas.drawText(contentText, contentMarginStart + (labelLength - contentTextWidth) / 2, contentTextHeight - contentMarginBottom, contentPaint);

    }


    private void drawTopText(Canvas canvas) {
        // 测量文字高度
        Rect rectText = new Rect();
        Paint topPaint = new Paint();
        switch (topTextStyle) {
            case 1:
                topPaint.setTypeface(Typeface.SANS_SERIF);
                break;
            case 2:
                topPaint.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            default:
                topPaint.setTypeface(Typeface.DEFAULT);
                break;
        }
        topPaint.getTextBounds(topText, 0, topText.length(), rectText);
        topPaint.setColor(topTextColor);
        topPaint.setTextSize(topTextSize);
        topTextWidth = rectText.width();
        topTextHeight = topTextSize;
        if (textFlip && (angle == 3 || angle == 4)) {
            canvas.translate(-contentTextHeight, contentTextHeight);
        } else {
            canvas.translate(-contentTextHeight, -contentTextHeight);
        }
        canvas.drawText(topText, topMarginStart + (labelLength - topTextWidth) / 2, topTextHeight - topMarginBottom, topPaint);


    }

    public int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public float sp2px(float spValue) {
        final float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

    public void setLabelLength(float labelLength) {
        this.labelLength = labelLength;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public void setContentTextColor(int contentTextColor) {
        this.contentTextColor = contentTextColor;
    }

    public void setContentTextSize(float contentTextSize) {
        this.contentTextSize = contentTextSize;
    }

    public void setContentMarginStart(float contentMarginStart) {
        this.contentMarginStart = contentMarginStart;
    }

    public void setContentMarginBottom(float contentMarginBottom) {
        this.contentMarginBottom = contentMarginBottom;
    }

    public void setContentTextHeight(float contentTextHeight) {
        this.contentTextHeight = contentTextHeight;
    }

    public void setContentTextWidth(float contentTextWidth) {
        this.contentTextWidth = contentTextWidth;
    }

    public void setContentTextStyle(int contentTextStyle) {
        this.contentTextStyle = contentTextStyle;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public void setTopTextSize(float topTextSize) {
        this.topTextSize = topTextSize;
    }

    public void setTopTextColor(int topTextColor) {
        this.topTextColor = topTextColor;
    }

    public void setTopMarginStart(float topMarginStart) {
        this.topMarginStart = topMarginStart;
    }

    public void setTopMarginBottom(float topMarginBottom) {
        this.topMarginBottom = topMarginBottom;
    }

    public void setTopTextHeight(float topTextHeight) {
        this.topTextHeight = topTextHeight;
    }

    public void setTopTextWidth(float topTextWidth) {
        this.topTextWidth = topTextWidth;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }

    public void setTopTextStyle(int topTextStyle) {
        this.topTextStyle = topTextStyle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void show() {
        invalidate();
    }
}
