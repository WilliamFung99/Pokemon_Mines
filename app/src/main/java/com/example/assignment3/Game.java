package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    private static final int ROWS = 5;
    private static final int COLUMNS = 5;

    Button buttons[][] = new Button[ROWS][COLUMNS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        populatePokeballs();
    }

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
                //button.setBackgroundResource(R.drawable.pokemon_ball);
                button.setText(" " + c +  ", " +  r);

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

        Toast.makeText(this, "Button Clicked: " + column + "," + row, Toast.LENGTH_SHORT).show();

        Button button = buttons[row][column];

        //lock button sizes
        lockButtonSizes();


        //Does not scale Image
        //button.setBackgroundResource(R.drawable.pokemon_ball);

        //Scale Image to button

        int newWidth = button.getWidth();
        int newHeigt = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pokemon_ball);
        Bitmap scaleBitmap = Bitmap.createScaledBitmap(originalBitmap,newWidth,newHeigt,true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource,scaleBitmap));

        //change text on button
        button.setText("" + column);


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
