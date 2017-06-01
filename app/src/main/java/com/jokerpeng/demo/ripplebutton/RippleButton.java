package com.jokerpeng.demo.ripplebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


/**
 * Created by PengXiaoJie on 2017/6/1.14 06..
 */

public class RippleButton extends TextView {
    private Paint mPaint;
    private int centerX;
    private int centerY;
    private boolean isAnimating;
    private int mStepSize;
    private int mMaxRadius;
    private int mRadius;
    private OnBeforeClickedListender mListender;

    public RippleButton(Context context) {
        this(context,null,0);
    }

    public RippleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        resolveAttrs(context,attrs,0);
    }

    public RippleButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        resolveAttrs(context,attrs,defStyleAttr);
    }

    private void resolveAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,R.styleable.bar,defStyleAttr,0);
        mPaint.setColor(typedArray.getColor(R.styleable.bar_ripple_color, Color.TRANSPARENT));
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMaxRadius = Math.max(getMeasuredHeight(),getMeasuredWidth()) / 2 ;
        centerX = getMeasuredWidth()/2;
        centerY = getMeasuredHeight()/2;
        mStepSize = mMaxRadius/20;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == event.ACTION_DOWN){
            if(null != mListender){
                mListender.onBeforeClicked(this);

            }
            mRadius = 0;
            isAnimating = true;
            postInvalidate();
        }
        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(!isAnimating){
            super.onDraw(canvas);
            return;
        }
        if(isAnimating && mRadius > mMaxRadius){
            isAnimating = false;
            mRadius = 0;
            performClick();
            super.onDraw(canvas);
            return;
        }
        mRadius += mStepSize;
        canvas.drawCircle(centerX,centerY,mRadius,mPaint);
        super.onDraw(canvas);
        postInvalidate();
    }

    public void setRippleColor(int rippleColor){
        mPaint.setColor(rippleColor);
    }

    public void setmListender(OnBeforeClickedListender mListender) {
        this.mListender = mListender;
    }

    public interface OnBeforeClickedListender{
        void onBeforeClicked(View view);
    }
}
