package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Options extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner boardSizeSpinner;
    Spinner numberOfMinesSpinner;

    ArrayAdapter<CharSequence> boardSizeAdapter;
    ArrayAdapter<CharSequence> numberOfMinesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boardSizeSpinner = (Spinner) findViewById(R.id.boardSizeSpinner);
        numberOfMinesSpinner = (Spinner)findViewById(R.id.selectMinesSpinner);

        boardSizeAdapter = ArrayAdapter.createFromResource(this, R.array.boardSizes,android.R.layout.simple_spinner_item);
        boardSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfMinesAdapter = ArrayAdapter.createFromResource(this, R.array.numberOfMines,android.R.layout.simple_spinner_item);
        numberOfMinesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        boardSizeSpinner.setAdapter(boardSizeAdapter);
        numberOfMinesSpinner.setAdapter(numberOfMinesAdapter);

        boardSizeSpinner.setOnItemSelectedListener(this);
        numberOfMinesSpinner.setOnItemSelectedListener(this);

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
