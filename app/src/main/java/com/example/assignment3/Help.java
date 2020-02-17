package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView homePageLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        homePageLink = (TextView)findViewById(R.id.hyperlinkTextView);
        homePageLink.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public static Intent makeIntentForHelp(Context context){
        return new Intent(context, Help.class);
    }

}
