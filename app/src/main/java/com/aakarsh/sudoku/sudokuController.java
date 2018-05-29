package com.aakarsh.sudoku;

import com.aakarsh.sudoku.generator.generateSudoku;
import com.aakarsh.sudoku.view.sudokumatrix.SudokuValidationController;

import android.content.Context;

public class sudokuController {

    boolean sudoku_status = false;
    //singleton class
    private static sudokuController instance;

    private SudokuValidationController grid = null;

    private int selectedXaxis = -1, selectedYaxis = -1;

    private sudokuController() {
    }

    public static sudokuController getInstance() {
        if (instance == null) {
            instance = new sudokuController();
        }
        return instance;
    }

    //creating the sudoku grid based on the level information from sudokuActivity
    public void createGrid(Context context, int level) {

        //generate the sudoko grid with all numbers in the 9*9 matrix filled
        int[][] Sudoku = generateSudoku.getInstance().generateGrid();
        //removing the numbers from the grid based on the level information from sudokuActivity
        Sudoku = generateSudoku.getInstance().removeElements(Sudoku, level);
        //sending the grid data to SudokuValidationController to construct the UI of the sudoku.
        grid = new SudokuValidationController(context);
        grid.setGrid(Sudoku);
    }

    public SudokuValidationController getGrid() {
        return grid;
    }

    public void setSelectedPosition(int x, int y) {
        selectedXaxis = x;
        selectedYaxis = y;
    }

    //setting the number in the position selected the user
    public void setNumber(int number) {
        if (selectedXaxis != -1 && selectedYaxis != -1) {
            grid.setItem(selectedXaxis, selectedYaxis, number);
        }
        //to check if the user has solved the sudoku.
        sudoku_status = grid.checkGame();

    }
}
