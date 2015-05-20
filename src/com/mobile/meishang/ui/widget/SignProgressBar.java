package com.mobile.meishang.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.mobile.meishang.R;

public class SignProgressBar extends View {

	private float mImgWidth;

	private float mImgHei;

	private Paint mPaint;

	private Resources res;

	private Bitmap bm;

	private int textsize = 50;

	private int mPosition;

	public SignProgressBar(Context context) {
		super(context);
		init();
	}

	public SignProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SignProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	// 初始化
	private void init() {
		res = getResources();
		initBitmap();
		initDraw();
	}

	private void initBitmap() {
		bm = BitmapFactory.decodeResource(res, R.drawable.ic_sign_location);
		if (bm != null) {
			mImgWidth = bm.getWidth();
			mImgHei = bm.getHeight();
		} else {
			mImgWidth = 0;
			mImgHei = 0;
		}
	}

	private void initDraw() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setTypeface(Typeface.DEFAULT);
		mPaint.setTextSize(textsize);
		mPaint.setColor(0xff23fc4f);
	}

	protected synchronized void onDraw(Canvas canvas) {
		try {
			super.onDraw(canvas);
			float mTextWidth = mPaint.measureText("3") / 2;
			float xImg;
			if (mPosition > 0) {
				if (mPosition > 7) {
					xImg = (7 * (getWidth() / 8)) - (getBitmapWidth() / 2)
							+ mTextWidth;
				} else {
					xImg = (mPosition * (getWidth() / 8))
							- (getBitmapWidth() / 2) + mTextWidth;
				}
			} else {
				xImg = 0;
			}

			float yImg = 0;
			// float xText = 10;
			// float yText = 30;
			canvas.drawBitmap(bm, xImg, yImg, mPaint);
			mPaint.setStrokeWidth(5);
			mPaint.setColor(res.getColor(R.color.white));
			canvas.drawLine(10, getBitmapHeigh(), getWidth(), getBitmapHeigh(),
					mPaint);
			mPaint.setColor(res.getColor(R.color.orange1));
			if (mPosition < 7) {

				canvas.drawLine(10, getBitmapHeigh(), mPosition
						* (getWidth() / 8) + mTextWidth, getBitmapHeigh(),
						mPaint);
			} else {
				canvas.drawLine(10, getBitmapHeigh(), 7 * (getWidth() / 8)
						+ mTextWidth, getBitmapHeigh(), mPaint);
			}
			for (int i = 0; i < 8; i++) {
				if (i != 0) {
					if (mPosition > 7) {
						canvas.drawText((i + mPosition - 7) + "", i
								* (getWidth() / 8), getBitmapHeigh() * 2 + 5,
								mPaint);
					} else {
						canvas.drawText(i + "", i * (getWidth() / 8),
								getBitmapHeigh() * 2 + 5, mPaint);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getBitmapWidth() {
		return (int) Math.ceil(mImgWidth);
	}

	private int getBitmapHeigh() {
		return (int) Math.ceil(mImgHei);
	}

	public void setmPosition(int mPosition) {
		this.mPosition = mPosition;
	}

	public int getmPosition() {
		return mPosition;
	}

}
