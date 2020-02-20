package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Options extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int chosenRow;
    private int chosenColumn;
    private int chosenMineNumber;


    Spinner boardSizeSpinner;
    Spinner numberOfMinesSpinner;

    ArrayAdapter<CharSequence> boardSizeAdapter;
    ArrayAdapter<CharSequence> numberOfMinesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        setUpBoardSizeSpinner();

        setUpNumberOfMinesSpinner();
    }


    private void setUpBoardSizeSpinner(){

        boardSizeSpinner = (Spinner) findViewById(R.id.boardSizeSpinner);
        boardSizeAdapter = ArrayAdapter.createFromResource(this, R.array.boardSizes,android.R.layout.simple_spinner_item);
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
                        break;

                    case 1:
                        Log.d("case 1", "5 by 10");
                        chosenRow = 5;
                        chosenColumn = 10;
                        break;
                    case 2:
                        Log.d("case 2", "6 by 15");
                        chosenRow = 6;
                        chosenColumn = 15;
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

        numberOfMinesSpinner = (Spinner)findViewById(R.id.selectMinesSpinner);
        numberOfMinesAdapter = ArrayAdapter.createFromResource(this, R.array.numberOfMines,android.R.layout.simple_spinner_item);
        numberOfMinesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfMinesSpinner.setAdapter(numberOfMinesAdapter);
        numberOfMinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Log.d("case 0", "6 mines");
                        chosenMineNumber = 6;
                        break;

                    case 1:
                        Log.d("case 1", "10 mines");
                        chosenMineNumber = 10;
                        break;
                    case 2:
                        Log.d("case 2", "15 mines");
                        chosenMineNumber = 15;
                        break;


                    case 3:
                        Log.d("case 3", "20 mines");
                        chosenMineNumber = 20;
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
