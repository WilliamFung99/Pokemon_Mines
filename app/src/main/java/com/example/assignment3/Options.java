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
import android.widget.Spinner;
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

        int savedRow = getChosenRow(this);
        Log.d("saveRow", "" + savedRow);
        int savedColumn = getChosenColumn(this);
        Log.d("saveColumn", "" + savedColumn);
        int savedNumberOfMines = getNumberOfMines(this);
        Log.d("saveNumberOfMines", "" + savedNumberOfMines);


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
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);

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
    static public int getChosenRow(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        //TODO: Change default value.
        return prefs.getInt("row", 0);

    }

    static public int getChosenColumn(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        //TODO: Change default value.
        return prefs.getInt("column", 0);
    }

    static public int getNumberOfMines(Context context){

        SharedPreferences prefs = context.getSharedPreferences("MinePrefs", MODE_PRIVATE);

        //TODO: Change default value.
        return prefs.getInt("mines", 0);
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
}
