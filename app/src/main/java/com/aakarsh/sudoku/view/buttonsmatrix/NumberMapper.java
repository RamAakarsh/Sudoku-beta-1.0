package com.aakarsh.sudoku.view.buttonsmatrix;

import com.aakarsh.sudoku.sudokuController;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NumberMapper extends Button implements OnClickListener{

	private int number;
	
	public NumberMapper(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(this);
	}

	//setting the user specified number in the sudoku grid by passing it to the sudokuController
	@Override
	public void onClick(View v) {

		sudokuController.getInstance().setNumber(number);
	}
	
	public void setNumber(int number){
		this.number = number;
	}
}
