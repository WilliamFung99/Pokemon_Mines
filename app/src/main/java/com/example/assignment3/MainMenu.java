package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainMenu extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        return new Intent(context,MainMenu.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
}
