package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner boardSizeSpinner;
    String boardSizes[] = {"4 by 6 ", "5 by 10"};

    ArrayAdapter<CharSequence> adapter;
    String record = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boardSizeSpinner = (Spinner) findViewById(R.id.boardSizeSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.boardSizes,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardSizeSpinner.setAdapter(adapter);

        boardSizeSpinner.setOnItemSelectedListener(this);

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
