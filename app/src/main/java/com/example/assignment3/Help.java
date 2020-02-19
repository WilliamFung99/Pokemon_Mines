package com.example.assignment3;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    TextView homePageLink;
    Dialog dialog;
    Button congratsPopup;
    Button ok;
    ImageView close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        homePageLink = (TextView)findViewById(R.id.hyperlinkTextView);
        homePageLink.setMovementMethod(LinkMovementMethod.getInstance());

        dialog = new Dialog(this);

        congratsPopup = (Button) findViewById(R.id.btncongrats);

        congratsPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCongratsPopup();
            }
        });


    }

    private void showCongratsPopup() {
        dialog.setContentView(R.layout.congrats_popup);
        close = (ImageView) dialog.findViewById(R.id.close);
        ok = (Button) findViewById(R.id.btnOk);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        
        dialog.show();

    }

    public static Intent makeIntentForHelp(Context context){
        return new Intent(context, Help.class);
    }

}
