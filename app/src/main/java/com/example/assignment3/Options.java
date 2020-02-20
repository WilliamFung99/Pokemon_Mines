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

        int[] fourBySix = getResources().getIntArray(R.array.four_by_six);
        int[] fiveByTen = getResources().getIntArray(R.array.five_by_ten);
        int[] sixByFifteen = getResources().getIntArray(R.array.six_by_fifteen);

        boardSizes.add( Integer.toString(fourBySix[0]) + " by " +  Integer.toString(fourBySix[1]));
        boardSizes.add( Integer.toString(fiveByTen[0]) + " by " +  Integer.toString(fiveByTen[1]));
        boardSizes.add( Integer.toString(sixByFifteen[0]) + " by " +  Integer.toString(sixByFifteen[1]));


        boardSizeSpinner = (Spinner) findViewById(R.id.boardSizeSpinner);
        boardSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, boardSizes);
        boardSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardSizeSpinner.setAdapter(boardSizeAdapter);
        boardSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Log.d("case 0", "4 by 6");
                        chosenRow = 4;
                        chosenColumn = 6;

                        saveBoardSize(chosenRow, chosenColumn);

                        break;

                    case 1:
                        Log.d("case 1", "5 by 10");
                        chosenRow = 5;
                        chosenColumn = 10;
                        saveBoardSize(chosenRow, chosenColumn);

                        break;
                    case 2:
                        Log.d("case 2", "6 by 15");
                        chosenRow = 6;
                        chosenColumn = 15;
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
                            Log.d("case 0", "6 mines");
                            chosenMineNumber = numberOfMines[0];
                            saveNumberOfMines(chosenMineNumber);
                            break;

                        case 1:
                            Log.d("case 1", "10 mines");
                            chosenMineNumber = numberOfMines[1];
                            saveNumberOfMines(chosenMineNumber);

                            break;
                        case 2:
                            Log.d("case 2", "15 mines");
                            chosenMineNumber = numberOfMines[2];
                            saveNumberOfMines(chosenMineNumber);

                            break;


                        case 3:
                            Log.d("case 3", "20 mines");
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
