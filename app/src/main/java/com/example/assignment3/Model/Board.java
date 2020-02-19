package com.example.assignment3.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Sets up initial board with mines
 */

public class Board {

    private int ROWS;   //y
    private int COLUMNS; //x
    private int MINES;
    private Random rand = new Random();
    private boolean[][] mineBoard;
    private final boolean MINE = true;
    private final boolean NOTMINE = false;

    public List<Points> minesList = new ArrayList<>();
    public List<Integer> occupiedMinesList = new ArrayList<>(); //changes where the bombs are

    public Board(int ROWS, int COLS, int MINES){
        this.ROWS = ROWS;
        this.COLUMNS = COLS;
        this.MINES = MINES;

        mineBoard = new boolean[COLS][ROWS];    //x by y
    }

    public boolean[][] initializeBoardWithMines(){
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLUMNS; col++){
                mineBoard[col][row] = NOTMINE;
            }
        }
        initializeMineList();
        for(int i = 0; i < MINES; i++){
            placeMine();
        }
        return mineBoard;
    }

    public boolean[][] updateBoard(){
        for(int i = 0; i < occupiedMinesList.size(); i++) {
            int occupiedMineX = minesList.get(occupiedMinesList.get(i)).getxDirection();
            int occupiedMineY = minesList.get(occupiedMinesList.get(i)).getyDirection();
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLUMNS; col++) {
                    if (row == occupiedMineY && col == occupiedMineX) {
                        mineBoard[col][row] = MINE;
                    }
                }
            }
        }

        return mineBoard;
    }

    private void initializeMineList(){
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLUMNS; col++){
                minesList.add(new Points(col,row));
            }
        }
    }

    private void placeMine(){
        int i = 0;

        int randomIndexMine = rand.nextInt(minesList.size());

        while(i < occupiedMinesList.size()){
            if(randomIndexMine == occupiedMinesList.get(i)){
                randomIndexMine = rand.nextInt(minesList.size());
                i = 0;
            }else{
                i++;
            }
        }
        occupiedMinesList.add(randomIndexMine);  //adds the index in where the mine is located
        int randomColumnMine = minesList.get(randomIndexMine).getxDirection();
        int randomRowMine = minesList.get(randomIndexMine).getyDirection();
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLUMNS; col++){
                if(row == randomRowMine && col == randomColumnMine){
                    mineBoard[col][row] = MINE;
                }
            }
        }
    }
}
