package com.aakarsh.sudoku.view.sudokumatrix;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BaseSudokuViewConstruction extends View{

	
	private int value;
    private Paint mpaint;
	private boolean modifiable = true;
	
	public BaseSudokuViewConstruction(Context context) {
		super(context);
	}

	//determine the size requirements for this view and all of its children.
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}

	//setting modifiable to false if the value is generated by sudokugenerator.
	public void setNotModifiable(){
		modifiable = false;
	}
	
	public void setInitValue(int value){
		this.value = value;
		//to update the screen i.e redraw on screen
		invalidate();
	}

	//setting the value if the modifiable is true.
	public void setValue( int value ){
		if( modifiable ){
			this.value = value;
		}
		//to update the screen i.e redraw on screen
		invalidate();
	}


	
	public int getValue(){
		return value;
	}
}
