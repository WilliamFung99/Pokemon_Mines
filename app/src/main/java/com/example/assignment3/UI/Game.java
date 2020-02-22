package com.example.assignment3.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.assignment3.Model.ScanBoard;
import com.example.assignment3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Game board that combines logic model with UI
 */
public class Game extends AppCompatActivity {

    private int ROWS;
    private int COLUMNS;
    private int MINES;


    private boolean[][] mine;
    private int[][] mineNum;

    private static final int RESOLUTION = 200;

    private List<Integer> visibleColumns = new ArrayList<>();
    private List<Integer> visibleRows = new ArrayList<>();

    Button [][] buttons;

    private List<Integer> charzardIndex = new ArrayList<>();
    private List<Integer> scannedIndex = new ArrayList<>();
    private int pokemonFound = 0;
    private int index;
    ScanBoard board;
    Dialog dialog;
    Button ok;
    ImageView close;

    private int scans = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        int timesPlayed = Options.getTimesPlayed(this);
        timesPlayed++;
        Options.saveTimesPlayed(timesPlayed,this);

        populatePokeballs();
        mine = board.mineBoard();
        mineNum = board.numBoard(mine);

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void populatePokeballs() {
        ROWS = Options.getChosenRow(this);
        COLUMNS = Options.getChosenColumn(this);
        MINES = Options.getNumberOfMines(this);
        mine  = new boolean[COLUMNS][ROWS];
        mineNum = new int[COLUMNS][ROWS];
        buttons = new Button[ROWS][COLUMNS];
        board = new ScanBoard(ROWS,COLUMNS,MINES);

        createTotalPokemonCollectedView();

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
        MediaPlayer charzardSound = MediaPlayer.create(this,R.raw.charzard_sound_effect);

        boolean isPokemonFound = false;
        Button button = buttons[row][column];

        index = column + row * COLUMNS;

        if(mine[column][row]) {
            charzardIndex.add(index);

            //lock button sizes
            lockButtonSizes();
            //Does not scale Image

            //Scale Image to button

            int newWidth = button.getHeight();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.charzard);
            Bitmap scaleBitmap = Bitmap.createScaledBitmap(originalBitmap, RESOLUTION, RESOLUTION, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaleBitmap));

            //change text on button
            isPokemonFound = true;
        }else{

            //lock button sizes
            lockButtonSizes();
            //Does not scale Image

            //Scale Image to button
            int newWidth = button.getHeight();
            int newHeight = button.getHeight();

            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.open_pokeball);
            Bitmap scaleBitmap = Bitmap.createScaledBitmap(originalBitmap, RESOLUTION, RESOLUTION, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaleBitmap));


            for(int i = 0; i < charzardIndex.size(); i++){
                if(index == charzardIndex.get(i)){
                    originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.charzard);
                    scaleBitmap = Bitmap.createScaledBitmap(originalBitmap, RESOLUTION, RESOLUTION, true);
                    resource = getResources();
                    button.setBackground(new BitmapDrawable(resource, scaleBitmap));
                }
            }
            setScanTextOnScreen(index, column, row);
            String msgMineNum = Integer.toString(mineNum[column][row]);
            button.setText(msgMineNum);
        }
        if(!isPokemonFound) {
            visibleRows.add(row);
            visibleColumns.add(column);
        }
        if(isPokemonFound) {
            float sound = (float) 1.0;
            charzardSound.setVolume(sound, sound);
            charzardSound.start();
            pokemonFound++;
            update(column,row);

        }
        createTotalPokemonCollectedView();
        if(pokemonFound == MINES){

            int bestScore = Options.getBestScore(this);

            if(bestScore == 0){
                Options.saveBestScore(scans, this);
            }
            else{
                if(bestScore > scans){
                    Options.saveBestScore(scans, this);
                }

            }
            endGameScreen();
        }
    }

    private void endGameScreen(){

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.congrats_popup);
        close = (ImageView) dialog.findViewById(R.id.close);
        ok = (Button) dialog.findViewById(R.id.btnOk);
        TextView score = (TextView) dialog.findViewById(R.id.yourScoreTextView);
        String scoreText = ("Your Score is: " + scans);

        score.setText(scoreText);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Game.this, MainMenu.class));
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Game.this,MainMenu.class));
            }
        });

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window windowAlDl = dialog.getWindow();

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        windowAlDl.setAttributes(layoutParams);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void createTotalPokemonCollectedView(){
        TextView pokemonFoundView = findViewById(R.id.pokemonsFoundTextView);
        String pokemonText = ("Found " + pokemonFound + " of " + MINES + " Pokemons");
        pokemonFoundView.setText(pokemonText);
    }
    private void setScanTextOnScreen(int index, int column, int row){
        boolean isScanned = false;
        for(int indices = 0 ; indices < scannedIndex.size(); indices++) {
            if(index == scannedIndex.get(indices)){
                isScanned = true;
            }
        }
        if(!isScanned){
            scanningAnimation(row,column);

            scannedIndex.add(index);
            scans++;
            TextView numberScans = findViewById(R.id.numberOfScansUsedTextView);
            String msgScans = Integer.toString(scans);
            numberScans.setText(msgScans);
            playPokeBallSound(index);

        }

    }
    private void playPokeBallSound(int index){
        boolean isCharzardPresent = false;
        MediaPlayer pokeBallSound = MediaPlayer.create(this,R.raw.pokeball_opening);

        for (int i = 0; i < charzardIndex.size(); i++) {
            if (index == charzardIndex.get(i)) {
                isCharzardPresent = true;
            }
        }
        if(!isCharzardPresent){
            float lowerSound = (float) 0.2;
            pokeBallSound.setVolume(lowerSound, lowerSound);
            pokeBallSound.start();
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

    private void update(int column, int row){
        int index = column + row * COLUMNS;
        for(int i = 0 ; i < board.occupiedMineList.size(); i++) {
            if(index == board.occupiedMineList.get(i)){
                board.occupiedMineList.remove(i);
                break;
            }
        }
        mine = board.updateMineBoard();
        mineNum = board.numBoard(mine);

        for(int size = 0; size < visibleRows.size();size++) {
            for (int i = 0; i < COLUMNS; i++) {
                for (int j = 0; j < ROWS; j++) {
                    if(visibleColumns.get(size) == i && visibleRows.get(size) == j){
                        Button buttonUpdate = buttons[j][i];
                        String msgMineNumber = Integer.toString(mineNum[i][j]);
                        buttonUpdate.setText(msgMineNumber);
                    }
                }
            }
        }
    }

    private void scanningAnimation(int row,int col) {
        for (int left = 0; left < col; left++) {
            fadeAnimtion(row,left);
        }
        for (int right = col + 1; right < COLUMNS; right++) {
            fadeAnimtion(row,right);
        }
        for (int down = row + 1; down < ROWS; down++) {
            fadeAnimtion(down,col);
        }
        for (int up = row - 1; up >= 0; up--) {
            fadeAnimtion(up,col);
        }

    }

    private void fadeAnimtion(int row, int col){
        Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.furret_animation_disappear);
        buttons[row][col].setAnimation(fadeOut);

    }
}
