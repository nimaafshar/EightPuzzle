package com.nafshar.eightpuzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    Button[][] elements;
    Puzzle puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing element array
        elements = new Button[3][3];
        elements[0][0] = findViewById(R.id.button_0_0);
        elements[0][1] = findViewById(R.id.button_0_1);
        elements[0][2] = findViewById(R.id.button_0_2);
        elements[1][0] = findViewById(R.id.button_1_0);
        elements[1][1] = findViewById(R.id.button_1_1);
        elements[1][2] = findViewById(R.id.button_1_2);
        elements[2][0] = findViewById(R.id.button_2_0);
        elements[2][1] = findViewById(R.id.button_2_1);
        elements[2][2] = findViewById(R.id.button_2_2);

        elements[1][1].setVisibility(View.INVISIBLE);
        puzzle = new Puzzle();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i!=1 || j!=1){
                    elements[i][j].setText(String.valueOf(puzzle.board[i][j]));
                }
            }
        }
    }

    public void move(View view){
        String name = getResources().getResourceEntryName(view.getId());
        int row = Integer.valueOf(name.substring(7,8));
        int col = Integer.valueOf(name.substring(9));
//        Log.i("soemthing", "move"+row+" "+col);
        Coordinate lastEmpty = puzzle.move(row,col);
        if(lastEmpty != null){
            elements[puzzle.emptyRow][puzzle.emptyCol].setVisibility(View.INVISIBLE);
            elements[lastEmpty.row][lastEmpty.col].setVisibility(View.VISIBLE);
            elements[lastEmpty.row][lastEmpty.col].setText(String.valueOf(puzzle.board[lastEmpty.row][lastEmpty.col]));
            if(puzzle.hasWon()){
                Toast.makeText(this,getResources().getString(R.string.congrats_message),Toast.LENGTH_LONG).show();
            }
        }
    }

}
