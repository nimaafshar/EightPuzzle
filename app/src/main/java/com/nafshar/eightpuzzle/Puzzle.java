package com.nafshar.eightpuzzle;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Puzzle {
    public int emptyRow = 1;
    public int emptyCol = 1;

    public int[][] board;
    ArrayList<Integer> flatList=new ArrayList<>();

    static int[][] winningBoard = new int[3][3];
    static {
        int k = 1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i==1 && j==1){
                    winningBoard[i][j] = 0;
                }else {
                    winningBoard[i][j] = k++;
                }
            }
        }

    }


    public Puzzle(){
        board = new int[3][3];
        for(int i=1;i<9;i++){
            flatList.add(i);
        }
        this.start();
    }

    private void start() {
        board[1][1] = 0;
        Collections.shuffle(flatList);
        int k=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i!=1||j!=1){
                    board[i][j] = flatList.get(k++);
                }
            }
        }
        if(this.hasWon())
            this.start();
        Log.println(1,"start",flatList.toString());
    }

    public Coordinate move(int row,int col){
        Log.i("MOVE", "called for "+row + " "+col);
        Coordinate lastEmpty = new Coordinate(emptyRow,emptyCol);
        if(Math.abs(row - emptyRow) + Math.abs(col - emptyCol) == 1){
            board[emptyRow][emptyCol] = board[row][col];
            board[row][col] = 0;
            emptyCol = col;
            emptyRow = row;
            Log.i("MOVE", "accepted. new empty: "+emptyRow + " "+emptyCol);
            return lastEmpty;
        }
        return null;
    }

    public boolean hasWon(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j] != winningBoard[i][j])
                    return false;
            }
        }
        return true;
    }
}
