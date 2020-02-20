package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.assignment3.Model.ScanBoard;

public class Game extends AppCompatActivity {

    private static final int ROWS = 4;
    private static final int COLUMNS = 6;
    private static final int MINES = 2;

    private static boolean[][] mine = new boolean[COLUMNS][ROWS];
    private static int[][] mineNum = new int[COLUMNS][ROWS];

    private static final int RESOLUTION = 100;

    Button buttons[][] = new Button[ROWS][COLUMNS];

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ScanBoard board = new ScanBoard(ROWS,COLUMNS,MINES);
        mine = board.mineBoard();
        mineNum = board.numBoard(mine);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        populatePokeballs();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void populatePokeballs() {

        TableLayout table = (TableLayout)findViewById(R.id.tableForPokeballs);

        for(int r = 0; r < ROWS; r++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for(int c = 0; c < COLUMNS; c++){

                final int FINAL_COLUMN = c;
                final int FINAL_ROW = r;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));



                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.closed_pokeball);
                Bitmap scaleBitmap = Bitmap.createScaledBitmap(originalBitmap,RESOLUTION,RESOLUTION,true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource,scaleBitmap));

                //make text not clip on small buttons
                button.setPadding(0,0,0,0);

                button.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COLUMN, FINAL_ROW);

                    }
                });

                tableRow.addView(button);
                buttons[r][c] = button ;

            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void gridButtonClicked(int column, int row) {

        Toast.makeText(this, "Button Clicked: " + row + "," + column, Toast.LENGTH_SHORT).show();

        if(mine[column][row]) {
            Button button = buttons[row][column];

            //lock button sizes
            lockButtonSizes();
            //Does not scale Image
            //button.setBackgroundResource(R.drawable.pokemon_ball);

            //Scale Image to button
            int newWidth = button.getHeight();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.charzard);
            Bitmap scaleBitmap = Bitmap.createScaledBitmap(originalBitmap, RESOLUTION, RESOLUTION, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaleBitmap));

            //change text on button
            button.setText("0");
        }else{
            Button button = buttons[row][column];

            //lock button sizes
            lockButtonSizes();
            //Does not scale Image
            //button.setBackgroundResource(R.drawable.pokemon_ball);

            //Scale Image to button
            int newWidth = button.getHeight();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.open_pokeball);
            Bitmap scaleBitmap = Bitmap.createScaledBitmap(originalBitmap, RESOLUTION, RESOLUTION, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaleBitmap));

            //change text on button
            button.setText("" + mineNum[column][row]);

        }

    }

    private void lockButtonSizes() {
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLUMNS; c++){
                Button button = buttons[r][c];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);

            }
        }
    }

    public static Intent makeIntentForGame(Context context){
        return new Intent(context, Game.class);
    }


}
