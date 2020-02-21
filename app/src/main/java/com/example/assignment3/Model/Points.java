package com.example.assignment3.Model;

/**
 * Points system for the matrix
 */
public class Points {
    private int xDirection;
    private int yDirection;

    Points(int xDirection, int yDirection){
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

}
