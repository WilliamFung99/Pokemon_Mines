package com.example.assignment3.UI;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignment3.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Animation screen
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        animationStart_left();

        Button skipBtn = findViewById(R.id.btnSkipAnim);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = MainMenu.makeIntent(MainActivity.this);
                startActivity(mainMenu);
            }
        });
    }

    private void animationStart_left(){
        final ImageView furretLeft = findViewById(R.id.furretLeft);
        final ImageView furretRight = findViewById(R.id.furretRight);
        final ImageView furretUpright = findViewById(R.id.furretUpRight);
        final TextView welcome = findViewById(R.id.welcomeMessage);

        Animation moveLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.furret_animation_left);
        furretLeft.setAnimation(moveLeft);
        moveLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                furretRight.setVisibility(View.GONE);
                furretUpright.setVisibility(View.GONE);
                welcome.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                furretLeft.setVisibility(View.GONE);
                furretRight.setVisibility(View.VISIBLE);
                animation_right(furretLeft,furretRight);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void animation_right(final ImageView furretLeft,final ImageView furretRight){
        Animation moveRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.furret_animation_right);
        furretRight.setAnimation(moveRight);
        moveRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                furretLeft.setVisibility(View.VISIBLE);
                furretRight.setVisibility(View.GONE);
                animation_half(furretLeft,furretRight);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void animation_half(final ImageView furretLeft, final ImageView furretRight){
        Animation moveHalfLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.furret_animation_half_left);
        furretLeft.setAnimation(moveHalfLeft);
        moveHalfLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                furretFade(furretLeft);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    private void furretFade(final ImageView furretLeft) {
        Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.furret_animation_disappear);
        furretLeft.setAnimation(fadeOut);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                furretLeft.setVisibility(View.GONE);
                welcomeImage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void welcomeImage(){
        ImageView upRightFurret = findViewById(R.id.furretUpRight);
        upRightFurret.setVisibility(View.VISIBLE);

        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_fade_in);
        upRightFurret.setAnimation(fadeIn);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                welcomeText();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent mainMenu = MainMenu.makeIntent(MainActivity.this);
                startActivity(mainMenu);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void welcomeText(){
        TextView welcome = findViewById(R.id.welcomeMessage);
        welcome.setVisibility(View.VISIBLE);
        Animation welcomeFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_fade_in);
        welcome.setAnimation(welcomeFadeIn);
    }
}


