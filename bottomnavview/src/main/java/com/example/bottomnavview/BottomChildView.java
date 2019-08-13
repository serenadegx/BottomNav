package com.example.bottomnavview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.StaticLayout;
import android.util.AttributeSet;

public class BottomChildView extends AppCompatRadioButton {

    private Paint mCheckPaint;
    private Paint mUnCheckPaint;
    private Paint mTextPaint;
    private int mIconHeight;
    private int mIconWidth;
    private int mAlpha;
    private Bitmap mCheckBitmap;
    private Bitmap mUnCheckBitmap;
    private int mColor;
    private float mTextWidth;
    private float mFontHeight;
    private int mIconPadding;

    public BottomChildView(Context context) {
        super(context);
    }

    public BottomChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCheckIcon(canvas);
        drawUnCheckIcon(canvas);
        drawCheckText(canvas);
        drawUnCheckText(canvas);
    }

    private void drawUnCheckText(Canvas canvas) {
        //先设置颜色后设置Alpha，顺序不可调换
        mTextPaint.setColor(getCurrentTextColor());
        mTextPaint.setAlpha(255 - mAlpha);
        mTextPaint.setTextSize(getTextSize());
        canvas.drawText(getText().toString(), getWidth() / 2.0f - mTextWidth / 2.0f, getPaddingTop()
                + mIconPadding + mIconHeight + mFontHeight, mTextPaint);
    }

    private void drawCheckText(Canvas canvas) {
        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(mAlpha);
        mTextPaint.setTextSize(getTextSize());
        canvas.drawText(getText().toString(), getWidth() / 2.0f - mTextWidth / 2.0f, getPaddingTop()
                + mIconPadding + mIconHeight + mFontHeight, mTextPaint);
    }

    private void drawUnCheckIcon(Canvas canvas) {
        mUnCheckPaint.setAlpha(255 - mAlpha);
        canvas.drawBitmap(mUnCheckBitmap, (getWidth() - mIconWidth) / 2.0f, getPaddingTop(), mUnCheckPaint);
    }

    private void drawCheckIcon(Canvas canvas) {
        mCheckPaint.setAlpha(mAlpha);
        canvas.drawBitmap(mCheckBitmap, (getWidth() - mIconWidth) / 2.0f, getPaddingTop(), mCheckPaint);
    }

    private void init(Context context, AttributeSet attrs) {
        mCheckPaint = new Paint();
        mUnCheckPaint = new Paint();
        mTextPaint = new Paint();
        mCheckPaint.setAntiAlias(true);
        mUnCheckPaint.setAntiAlias(true);
        mTextPaint.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomNavView);
        Drawable checkDrawable = typedArray.getDrawable(R.styleable.BottomNavView_check_icon);
        Drawable uncheckDrawable = typedArray.getDrawable(R.styleable.BottomNavView_uncheck_icon);
        mColor = typedArray.getColor(R.styleable.BottomNavView_check_color, Color.BLACK);
        //释放资源
        typedArray.recycle();
        //设置按钮背景为空
        setButtonDrawable(null);
        //获取icon宽高及padding
        if (checkDrawable == null || uncheckDrawable == null) {
            throw new RuntimeException("check_icon and uncheck_icon should be defined");
        }
        setCompoundDrawablesWithIntrinsicBounds(null, checkDrawable, null, null);

        mIconHeight = checkDrawable.getIntrinsicHeight();
        mIconWidth = checkDrawable.getIntrinsicWidth();

        checkDrawable.setBounds(0, 0, mIconWidth, mIconHeight);
        uncheckDrawable.setBounds(0, 0, mIconWidth, mIconHeight);

        mIconPadding = getCompoundDrawablePadding();

        //创建选中状态的bitmap和未选中的bitmap
        mCheckBitmap = getBitmapFromDrawable(checkDrawable);
        mUnCheckBitmap = getBitmapFromDrawable(uncheckDrawable);

        //获取文本的宽高
        mTextWidth = StaticLayout.getDesiredWidth(getText(), getPaint());
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        mFontHeight = (float) Math.ceil(fontMetrics.descent - fontMetrics.ascent);

        if (isChecked())
            mAlpha = 255;

    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(mIconWidth, mIconHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (drawable instanceof BitmapDrawable) {
            drawable.draw(canvas);
            return bitmap;
        } else {
            throw new RuntimeException("the drawable must be an instance of BitmapDrawable");
        }
    }

    public void setRadioButtonChecked(boolean b) {
        setChecked(b);
        if (b) {
            mAlpha = 255;
        } else {
            mAlpha = 0;
        }
        //重新绘制
        invalidate();
    }

    public void updateAlpha(float alpha) {
        mAlpha = (int) alpha;
        //重新绘制
        invalidate();
    }

}
