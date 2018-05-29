package com.aakarsh.sudoku.view.sudokumatrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

public class SudokuViewConstruction extends BaseSudokuViewConstruction {

	private Paint mPaint;

	int counter=0;
	public SudokuViewConstruction(Context context ){
		super(context);
		
		mPaint = new Paint();

		
	}

	//specifies what to draw (numbers and lines)
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawNumber(canvas);
		drawLines(canvas);
	}

	//specifying how to draw numbers
	private void drawNumber(Canvas canvas){


		mPaint.setColor(Color.BLUE);
		mPaint.setTextSize(60);
		mPaint.setStyle(Style.FILL);
		
		Rect bounds = new Rect();
		mPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);
		
		if( getValue() != 0 ){

			canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width())/2, (getHeight() + bounds.height())/2	, mPaint);
		}
	}

	//specifying how to draw lines
	private void drawLines(Canvas canvas) {


		mPaint.setColor(Color.BLACK);
		mPaint.setStrokeWidth(10);
		mPaint.setStyle(Style.STROKE);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
	}
}
