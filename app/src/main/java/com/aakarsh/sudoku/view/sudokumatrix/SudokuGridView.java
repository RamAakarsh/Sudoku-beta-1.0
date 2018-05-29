package com.aakarsh.sudoku.view.sudokumatrix;

import com.aakarsh.sudoku.sudokuController;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class SudokuGridView extends GridView{

	private final Context context;
	//generating the x,y co-ordinates in the grid the user has selected.
	public SudokuGridView(final Context context , AttributeSet attrs) {
		super(context,attrs);
		
		this.context = context;
		
		SudokuGridViewAdapter gridViewAdapter = new SudokuGridViewAdapter(context);
		
		setAdapter(gridViewAdapter);
        //generating the co-ordinates based on clicking
		setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int x = position % 9;
				int y = position / 9;
				//setting the x,y co-odinates in sudokuController
				sudokuController.getInstance().setSelectedPosition(x, y);
			}
		});
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}

	// bridging the UI component and data source
	class SudokuGridViewAdapter extends BaseAdapter{

		private Context context;
		
		public SudokuGridViewAdapter(Context context) {
			this.context = context;
		}
		//total number of cells on the screen
		@Override
		public int getCount() {
			return 81;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		/* retreiving the numbers from sudokucontoller and setting the layout
		for list numbers using LayoutInflater class and then add the numbers to the cells*/
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return sudokuController.getInstance().getGrid().getItem(position);
		}
	}
}
