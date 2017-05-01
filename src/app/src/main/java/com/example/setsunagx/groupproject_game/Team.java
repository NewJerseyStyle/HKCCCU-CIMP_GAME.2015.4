package com.example.setsunagx.groupproject_game;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class Team extends ActionBarActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        getSupportActionBar().hide();
        ((TextView)findViewById(R.id.animation)).setMovementMethod(ScrollingMovementMethod.getInstance());
        new CountDownTimer(10000, 20) {
            private int i = -20;
            public void onTick(long millisUntilFinished) {
                findViewById(R.id.animation).scrollTo(0, i);
                i++;
            }

            public void onFinish() {

            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
