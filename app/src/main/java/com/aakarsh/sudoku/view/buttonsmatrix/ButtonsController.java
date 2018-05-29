package com.aakarsh.sudoku.view.buttonsmatrix;

import com.aakarsh.sudoku.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class ButtonsController extends GridView{

	// bridging the UI component and data source
	public ButtonsController(Context context , AttributeSet attrs ){
		super(context , attrs);
		
		ButtonsGridViewAdapter gridViewAdapter = new ButtonsGridViewAdapter(context);
		
		setAdapter(gridViewAdapter);
	}

	//mapping number with the buttons in UI
	class ButtonsGridViewAdapter extends BaseAdapter {
		
		private Context context;
		
		public ButtonsGridViewAdapter(Context context) {
			this.context = context;
		}

		//total number of buttons to be displayed in the screen
		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}


		@Override
		public long getItemId(int position) {
			return position;
		}

		//setting the layout for list numbers using LayoutInflater class and then add the numbers to the views
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			
			if( v == null ){
				LayoutInflater inflater = ((Activity) context).getLayoutInflater();
				v = inflater.inflate(R.layout.button, parent , false);
				
				NumberMapper number_button;
				number_button = (NumberMapper)v;

				number_button.setTextSize(10);
				number_button.setId(position);
				
				if( position != 9 ){
					number_button.setText(String.valueOf(position + 1));
					number_button.setNumber(position + 1);
				}else{
					number_button.setText("Delete");
					number_button.setNumber(0);
				}
				return number_button;
			}
			
			return v;
		}
		
	}
}
