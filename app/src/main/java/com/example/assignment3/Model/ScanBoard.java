package com.example.assignment3.Model;

import java.util.List;

/**
 * Numbers associated for number of pokemon in horizontal and vertical direction of matrix
 */

public class ScanBoard {
    private int ROWS;
    private int COLS;
    private int MINES;
    private int [][] numbersOnBoard;
    private int mineCounter;
    private Board board;
    private boolean[][] mineboard;

    public List<Integer> occupiedMineList;
    public List<Points> minesList;

    public ScanBoard(int ROWS, int COLS, int MINES){
        this.ROWS = ROWS;
        this.COLS = COLS;
        this.MINES = MINES;
        numbersOnBoard = new int[COLS][ROWS];
        board = new Board(ROWS,COLS,MINES);
        occupiedMineList = board.occupiedMinesList;
        minesList = board.minesList;
    }

    public boolean[][] mineBoard(){
        mineboard = board.initializeBoardWithMines();
        return mineboard;
    }
    public boolean[][] updateMineBoard(){
        mineboard = board.updateBoard();
        return mineboard;
    }

    public int[][] numBoard(boolean[][] mineBoard) {

        for(int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                numbersOnBoard[col][row] = scanHorizontalAndVerticalForMines(mineBoard, col + row*COLS);
            }
        }
        return numbersOnBoard;
    }
    private int scanHorizontalAndVerticalForMines(boolean[][] mineBoard, int incrementer){
        mineCounter = 0;
        int col = board.minesList.get(incrementer).getxDirection();
        int row = board.minesList.get(incrementer).getyDirection();
        // System.out.println("col " + col + " row " + row);

        for(int left = 0; left < col; left++){
            if(mineBoard[left][row]){
                //System.out.println("left");
                mineCounter++;
            }
        }
        for(int right = col + 1; right < COLS; right++){
            if(mineBoard[right][row]){
                //System.out.println("right");
                mineCounter++;
            }
        }
        for(int down = row + 1; down < ROWS; down++){
            if(mineBoard[col][down]){
                //System.out.println("down");
                mineCounter++;
            }
        }
        for(int up = row - 1; up >= 0; up--){
            if(mineBoard[col][up]) {
                //System.out.println("up");
                mineCounter++;
            }
        }
        return mineCounter;
    }
}


