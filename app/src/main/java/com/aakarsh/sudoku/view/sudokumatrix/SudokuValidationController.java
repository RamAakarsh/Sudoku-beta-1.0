package com.aakarsh.sudoku.view.sudokumatrix;

import android.content.Context;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.aakarsh.sudoku.validator.checkSudoku;

public class SudokuValidationController {

    TextToSpeech talker;

    private SudokuViewConstruction[][] Sudoku = new SudokuViewConstruction[9][9];

    private Context context;

    public SudokuValidationController(Context context) {
        this.context = context;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Sudoku[x][y] = new SudokuViewConstruction(context);
            }
        }
    }

    //setting the values into the grid and blocking the access to modify the generated numbers
    public void setGrid(int[][] grid) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Sudoku[x][y].setInitValue(grid[x][y]);
                if (grid[x][y] != 0) {
                   // blocking the access to modify the generated numbers
                    Sudoku[x][y].setNotModifiable();
                }
            }
        }
    }



    public SudokuViewConstruction[][] getGrid() {
        return Sudoku;
    }

    //retreving the values based on x,y co-ordinates
    public SudokuViewConstruction getItem(int x, int y) {
        return Sudoku[x][y];
    }
    //retreving the values based on position
    public SudokuViewConstruction getItem(int position) {
        int x = position % 9;
        int y = position / 9;

        return Sudoku[x][y];
    }
    //setting the value based on x,y co-ordinates
    public void setItem(int x, int y, int number) {
        Sudoku[x][y].setValue(number);
    }

    /**
     * Return true if user has solved sudoku
     * @return
     */
    public boolean checkGame() {
        boolean solved = false;
        int[][] sudGrid = new int[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudGrid[x][y] = getItem(x, y).getValue();
            }
        }
        //returns true if user has solved sudoku
        if (checkSudoku.getInstance().checkSudoku(sudGrid)) {

            solved = true;
            Toast.makeText(context, "You Did it !!.", Toast.LENGTH_LONG).show();
        }
        //prints a toast message if the used failed to solve sudoku
        else {
            int counter = 0;
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    sudGrid[x][y] = getItem(x, y).getValue();
                    if (sudGrid[x][y] != 0) {
                        counter++;
                    }
                }
            }

            if (counter == 81) {
                solved = false;
                Toast.makeText(context, "Try again. Better Luck !!", Toast.LENGTH_LONG).show();
            }
        }
        return solved;
    }


}
