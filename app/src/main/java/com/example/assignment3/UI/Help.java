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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setUpHomePageLink();

        setUpRecourceLinks();
        setUpBackButton();

    }

    private void setUpRecourceLinks() {
        TextView links;
        links = (TextView) findViewById(R.id.linksTextView);
        links.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void setUpHomePageLink() {
        TextView homePageLink;
        homePageLink = (TextView)findViewById(R.id.hyperlinkTextView);
        homePageLink.setMovementMethod(LinkMovementMethod.getInstance());
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


}
