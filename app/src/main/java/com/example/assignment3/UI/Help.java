package com.example.assignment3.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.assignment3.R;

import androidx.appcompat.app.AppCompatActivity;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Help button information
 */
public class Help extends AppCompatActivity {

    TextView homePageLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        homePageLink = (TextView)findViewById(R.id.hyperlinkTextView);
        homePageLink.setMovementMethod(LinkMovementMethod.getInstance());

        setUpBackButton();

        howToPlayText();
        aboutAuthorText();

    }

    private void setUpBackButton() {
        Button backButton = (Button) findViewById(R.id.btnBack);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public static Intent makeIntentForHelp(Context context){
        return new Intent(context, Help.class);
    }

    private void howToPlayText(){
        TextView howToPlay = findViewById(R.id.instructionsTextView);
        String text =
                "Pokemon seeker is a game based on the original Mine seeker game. The player clicks" + '\n' +
                "on a pokeball to find the hidden charzard, that is randomly located in any of the" + '\n' +
                "pokeballs. If the pokeball contains a pokemon, it is revealed to the player, otherwise" + '\n' +
                "it performs a scan on its respective row and column to indicate how many pokemon are" + '\n' +
                "located in its respective column and row. the goal of the game is to find all the " + '\n' +
                        "charzards in the least amount of scans.";

        howToPlay.setText(text);
    }
    private void aboutAuthorText(){
        TextView about = findViewById(R.id.authors);
        String text =
                "Both William and Kenneth are third year CMPT" + '\n' +
                "Major students attending Simon Fraser University" + '\n';
        about.setText(text);
    }

}
