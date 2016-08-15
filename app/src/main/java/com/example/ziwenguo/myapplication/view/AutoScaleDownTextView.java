package com.example.ziwenguo.myapplication.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Michael on 2016/8/15.
 */
public class AutoScaleDownTextView extends TextView{
    private static float DEFAULT_MIN_TEXT_SIZE = 10;
    private static float DEFAULT_MAX_TEXT_SIZE = 85;
    private float scaledDensity;
    private float density;

    // Attributes
    private Paint testPaint;
    private float minTextSize, maxTextSize;

    private String TAG = "AutoScaleDownTextView";
    public AutoScaleDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public AutoScaleDownTextView(Context context) {
        super(context);
        initialise();
    }

    public AutoScaleDownTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialise();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start,int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        refitText(text.toString(), this.getWidth());
    }
    private void initialise() {
        scaledDensity = getContext().getResources().getDisplayMetrics().scaledDensity;
        density = getContext().getResources().getDisplayMetrics().density;
        testPaint = new Paint();
        testPaint.set(this.getPaint());
        maxTextSize = this.getTextSize();
        if (maxTextSize > DEFAULT_MAX_TEXT_SIZE * scaledDensity) {
            maxTextSize = DEFAULT_MAX_TEXT_SIZE * scaledDensity;
        }
        minTextSize = DEFAULT_MIN_TEXT_SIZE * scaledDensity;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(this.getText().toString(), w);
        }
        super.onSizeChanged(w,h,oldw,oldh);
    }


    private void refitText(String text, int textWidth) {

        if (textWidth > 0) {
            int availableWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
            float trySize = maxTextSize;
            float scaled =  scaledDensity / density;
            testPaint.setTextSize(trySize*scaled);
            while ((trySize > minTextSize)&& (testPaint.measureText(text) > availableWidth))
            {
                trySize -= 2;
                if (trySize <= minTextSize) {
                    trySize = minTextSize;
                    break;
                }
                testPaint.setTextSize(trySize*scaled);
            }
            this.setTextSize(trySize / scaledDensity);
        }
    }
}
