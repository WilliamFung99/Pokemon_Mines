package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main homepage of game containing
 * "play game", "options" , and "help"
 */
public class MainMenu extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        return new Intent(context,MainMenu.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setUpOptionsBtn();
        setUpHelpBtn();
        setUpPlayGameBtn();
    }

    private void setUpOptionsBtn(){
        Button optionsButton = (Button) findViewById(R.id.btnOptions);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Options.makeIntentForOptions(MainMenu.this);
                startActivity(intent);
            }
        });

    }
    private void setUpHelpBtn(){
        Button helpButton = (Button) findViewById(R.id.btnHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Help.makeIntentForHelp(MainMenu.this);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void setUpPlayGameBtn(){

        Button playButton = (Button) findViewById(R.id.btnStart);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Game.makeIntentForGame(MainMenu.this);
                startActivity(intent);
            }
        });


    }


}
