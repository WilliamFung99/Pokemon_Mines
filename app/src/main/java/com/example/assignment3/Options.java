package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Options extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int chosenRow;
    private int chosenColumn;
    private int chosenMineNumber;

    Spinner boardSizeSpinner;
    Spinner numberOfMinesSpinner;

    List<String> boardSizes = new ArrayList<>();
    List<String> minesSizes = new ArrayList<>();

    ArrayAdapter<String> boardSizeAdapter;
    ArrayAdapter<String> numberOfMinesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        setUpBoardSizeSpinner();
        setUpNumberOfMinesSpinner();
        refreshTimesPlayed();
        refreshBestScore();
        setUpResetTimesPlayedButton(this);
        setUpResetBestScoreButton(this);
    }

    private void setUpResetBestScoreButton(final Context context) {
        final Button resetBestScore = (Button) findViewById(R.id.btnResetBestScore);

        resetBestScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int defaultScore = context.getResources().getInteger(R.integer.default_best_score);
                saveBestScore(defaultScore,context);
                refreshBestScore();

            }
        });


    }

    private void setUpResetTimesPlayedButton(final Context context) {
        Button resetTimesPlayed = (Button)findViewById(R.id.btnResetTimesPlayed);

        resetTimesPlayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int defaultTimesPlayed = context.getResources().getInteger(R.integer.default_times_played);
                saveTimesPlayed(defaultTimesPlayed, context);
                refreshTimesPlayed();

            }
        });

    }

    private void refreshBestScore() {
        TextView bestScoreText = (TextView) findViewById(R.id.playerbestScoreTextView);
        int bestScore = getBestScore(this);
        bestScoreText.setText(Integer.toString(bestScore));
    }

    private void refreshTimesPlayed(){
        TextView numberOfTimesPlayed = (TextView) findViewById(R.id.playerNumTimesPlayedTextView);
        int timesPlayed = getTimesPlayed(this);
        numberOfTimesPlayed.setText(Integer.toString(timesPlayed));
    }

    private void setUpBoardSizeSpinner(){

        for(int i = 0; i< getResources().getIntArray(R.array.row_sizes).length; i++){
            boardSizes.add( getResources().getIntArray(R.array.row_sizes)[i] + " by " +  getResources().getIntArray(R.array.col_sizes)[i]);

        }

        boardSizeSpinner = (Spinner) findViewById(R.id.boardSizeSpinner);
        boardSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, boardSizes);
        boardSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardSizeSpinner.setAdapter(boardSizeAdapter);

        for(int i = 0; i < boardSizes.size(); i++){
            int rowSizedSelected = getResources().getIntArray(R.array.row_sizes)[i];
            boardSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            chosenRow = getResources().getIntArray(R.array.row_sizes)[0];
                            chosenColumn = getResources().getIntArray(R.array.col_sizes)[0];

                            saveBoardSize(chosenRow, chosenColumn);

                            break;

                        case 1:
                            chosenRow = getResources().getIntArray(R.array.row_sizes)[1];
                            chosenColumn = getResources().getIntArray(R.array.col_sizes)[1];
                            saveBoardSize(chosenRow, chosenColumn);

                            break;
                        case 2:
                            chosenRow = getResources().getIntArray(R.array.row_sizes)[2];
                            chosenColumn = getResources().getIntArray(R.array.col_sizes)[2];
                            saveBoardSize(chosenRow, chosenColumn);

                            break;

                        default:
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        if( rowSizedSelected == getChosenRow(this)){
            boardSizeSpinner.setSelection(i);
        }


        }
    }

    private void setUpNumberOfMinesSpinner(){

        final int[] numberOfMines = getResources().getIntArray(R.array.numbers_of_mines);
        for(int i = 0; i < numberOfMines.length; i++){
            int mines = numberOfMines[i];
            minesSizes.add(""+ mines + " " + "mines");
        }
        numberOfMinesSpinner = (Spinner)findViewById(R.id.selectMinesSpinner);
        numberOfMinesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, minesSizes);
        numberOfMinesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfMinesSpinner.setAdapter(numberOfMinesAdapter);


        for(int i = 0; i < numberOfMines.length; i++) {

            int minesSelected = numberOfMines[i];

            numberOfMinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            chosenMineNumber = numberOfMines[0];
                            saveNumberOfMines(chosenMineNumber);
                            break;

                        case 1:
                            chosenMineNumber = numberOfMines[1];
                            saveNumberOfMines(chosenMineNumber);

                            break;
                        case 2:
                            chosenMineNumber = numberOfMines[2];
                            saveNumberOfMines(chosenMineNumber);

                            break;


                        case 3:
                            chosenMineNumber = numberOfMines[3];
                            saveNumberOfMines(chosenMineNumber);

                            break;

                        default:
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            if(minesSelected == getNumberOfMines(this)){
                numberOfMinesSpinner.setSelection(i);
            }

        }

    }

    private void saveBoardSize(int row, int column) {
        SharedPreferences prefs = this.getSharedPreferences("BoardSizePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("row", row);
        editor.putInt("column", column);
        editor.apply();
    }

    private void saveNumberOfMines(int numberOfMines){
        SharedPreferences prefs = this.getSharedPreferences("MinePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("mines", numberOfMines);
        editor.apply();
    }

    public static void saveBestScore(int score, Context context){
        SharedPreferences prefs = context.getSharedPreferences("BestScorePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("bestScore", score);
        editor.apply();
    }


    public static void saveTimesPlayed(int times, Context context){
        SharedPreferences prefs = context.getSharedPreferences("TimesPlayedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("timesPlayed", times);
        editor.apply();
    }

    public static int getChosenRow(Context context){
        SharedPreferences prefs = context.getSharedPreferences("BoardSizePrefs", MODE_PRIVATE);
        int defaultRow = context.getResources().getInteger(R.integer.default_row);
        return prefs.getInt("row", defaultRow);
    }

    public static int getChosenColumn(Context context){
        SharedPreferences prefs = context.getSharedPreferences("BoardSizePrefs", MODE_PRIVATE);
        int defaultCol = context.getResources().getInteger(R.integer.default_col);
        return prefs.getInt("column", defaultCol);
    }

    public static int getNumberOfMines(Context context){
        SharedPreferences prefs = context.getSharedPreferences("MinePrefs", MODE_PRIVATE);
        int defaultMines = context.getResources().getInteger(R.integer.default_number_of_mines);
        return prefs.getInt("mines", defaultMines);
    }

    public static int getTimesPlayed(Context context){
        SharedPreferences prefs = context.getSharedPreferences("TimesPlayedPrefs", MODE_PRIVATE);
        int defaultTimesPlaed =  context.getResources().getInteger(R.integer.default_times_played);
        return prefs.getInt("timesPlayed", defaultTimesPlaed);
    }

    public static int getBestScore(Context context){
        SharedPreferences prefs = context.getSharedPreferences("BestScorePrefs", MODE_PRIVATE);
        int defaultTimesPlayed =  context.getResources().getInteger(R.integer.default_best_score);
        return prefs.getInt("bestScore", defaultTimesPlayed);
    }


    public static Intent makeIntentForOptions(Context context){
        return new Intent(context, Options.class);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
