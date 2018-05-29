package com.aakarsh.sudoku.generator;

import java.util.ArrayList;
import java.util.Random;

public class generateSudoku {
    private static generateSudoku instance;

    //arraylist to store numbers 1-9 for each cell
    private ArrayList<ArrayList<Integer>> Available = new ArrayList<ArrayList<Integer>>();

    private Random rand = new Random();

    private generateSudoku() {
    }

    public static generateSudoku getInstance() {
        if (instance == null) {
            instance = new generateSudoku();
        }
        return instance;
    }

    /**
     * Return sudoku grid
     * @return
     */

    public int[][] generateGrid() {
        int[][] Sudoku = new int[9][9];

        int currentPos = 0;


        while (currentPos < 81) {
            if (currentPos == 0) {
                //clear all the values before generating the numbers in the grid
                clearGrid(Sudoku);
            }

                //generting each number and storing it in the cells after performing validations
            if (Available.get(currentPos).size() != 0) {
                //generate random position from the Available arraylist(random numbers:1-9)
                int i = rand.nextInt(Available.get(currentPos).size());
                //extracting the number from the random position generated.
                int number = Available.get(currentPos).get(i);
                /*checking for the conflicts while placing the numbers in sudoku grid and
                  if no conflit arises then place the number in respected cell.*/
                if (!checkConflict(Sudoku, currentPos, number)) {
                    //getting the x,y coordinates for placing each number from 0-81 in the cells.
                    int xPos = currentPos % 9;
                    int yPos = currentPos / 9;
                    //place the number in the respected cell
                    Sudoku[xPos][yPos] = number;
                    //after placing the number remove the number from available numbers list.
                    Available.get(currentPos).remove(i);
                    //increment to place the number in the next cell
                    currentPos++;
                } else {
                    /*if conflit arises then the number is already present in row/column.So remove the number
                    from available numbers list.*/
                    Available.get(currentPos).remove(i);
                }

            } else {
                //if the availabe list is empty then load the list with numbers 1-9 and goback one cell and re-iterate.
                for (int i = 1; i <= 9; i++) {
                    Available.get(currentPos).add(i);
                }
                currentPos--;
            }
        }

         // returing sudoku grid with all the 81 numbers filled.
        return Sudoku;
    }


    //removing the elements from sudoku grid based on level information from sudoku activity
    public int[][] removeElements(int[][] Sudoku, int level) {
        int i = 0;

        /*create random  x,y co-ordinates based on 'level' value from sudokuActivity and
          replace the corresponding grid values with zeros.*/
        while (i < level) {
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);

            if (Sudoku[x][y] != 0) {
                Sudoku[x][y] = 0;
                i++;
            }
        }
        return Sudoku;

    }

    //to replace all the grid values with -1
    private void clearGrid(int[][] Sudoku) {
        //clear the arraylist
        Available.clear();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Sudoku[x][y] = -1;
            }
        }

        //storing 1-9 values for all 1-81 cells in the grid.
        for (int x = 0; x < 81; x++) {
            Available.add(new ArrayList<Integer>());
            for (int i = 1; i <= 9; i++) {
                Available.get(x).add(i);
            }
        }
    }

    /*check if any conflicts/duplicates arises while placing the cell and
      return true if conflit arises and false if there is no conflict
     * @param Sudoku
     * @param currentPos
     * @param number
     * @return
      */
    private boolean checkConflict(int[][] Sudoku, int currentPos, final int number) {
        int xPos = currentPos % 9;
        int yPos = currentPos / 9;

        if (checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number)) {
            return true;
        }

        return false;
    }

    /**
     * Return true if there is a conflict
     *
     * @param Sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return
     */
    //check if there is any number duplicated in each row
    private boolean checkHorizontalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        for (int x = xPos - 1; x >= 0; x--) {
            if (number == Sudoku[x][yPos]) {
                return true;
            }
        }

        return false;
    }
    /**
     * Return true if there is a conflict
     *
     * @param Sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return
     */
    //check if there is any number duplicated in each column
    private boolean checkVerticalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        for (int y = yPos - 1; y >= 0; y--) {
            if (number == Sudoku[xPos][y]) {
                return true;
            }
        }

        return false;
    }
    /**
     * Return true if there is a conflict
     *
     * @param Sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return
     */
    //check if there is any number duplicated in each region(3*3)
    private boolean checkRegionConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        int xRegion = xPos / 3;
        int yRegion = yPos / 3;

        for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {
            for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {
                if ((x != xPos || y != yPos) && number == Sudoku[x][y]) {
                    return true;
                }
            }
        }

        return false;
    }
}
