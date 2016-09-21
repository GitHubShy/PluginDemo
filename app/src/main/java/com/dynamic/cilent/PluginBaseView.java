package com.dynamic.cilent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public abstract class PluginBaseView extends View {
	private Paint mPaint;
	private String mTitleText = "View";
	private Rect mBound;

	public PluginBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public PluginBaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PluginBaseView(Context context) {
		super(context);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setTextSize(10);
		mBound = new Rect();  
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);  
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mPaint.setColor(Color.YELLOW);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

		mPaint.setColor(Color.RED);
		canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2,
				getHeight() / 2 + mBound.height() / 2, mPaint);
	}
}
