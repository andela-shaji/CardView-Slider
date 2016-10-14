package com.suadahaji.cardviewslider.helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.suadahaji.cardviewslider.R;


public class CircleView extends View {

    private Context context;
    private Paint activeCircleIndicator;
    private Paint inactiveCircleIndicator;
    private int radius;
    private int size;
    private int position;
    private int indicatorsCount;

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        activeCircleIndicator = new Paint();
        activeCircleIndicator.setColor(ContextCompat.getColor(context, R.color.active_circle));
        activeCircleIndicator.setAntiAlias(true);
        inactiveCircleIndicator = new Paint();
        inactiveCircleIndicator.setColor(ContextCompat.getColor(context, R.color.inactive_circle));
        inactiveCircleIndicator.setAntiAlias(true);
        radius = getResources().getDimensionPixelSize(R.dimen.circle_size);
        size = radius * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < indicatorsCount; i++) {
            canvas.drawCircle(radius + (size * i), radius, radius / 2, inactiveCircleIndicator);
        }
        canvas.drawCircle(radius + (size * position), radius, radius / 2, activeCircleIndicator);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    public void setCurrentPage(int position) {
        this.position = position;
        invalidate();
    }

    public void setPageIndicators(int size) {
        this.indicatorsCount = size;
        invalidate();
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = size * indicatorsCount;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 2 * radius + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public void setInactiveIndicatorColor(@ColorRes int color) {
        inactiveCircleIndicator.setColor(ContextCompat.getColor(context, color));
        invalidate();
    }

    public void setActiveIndicatorColor(@ColorRes int color) {
        activeCircleIndicator.setColor(ContextCompat.getColor(context, color));
        invalidate();
    }

}
