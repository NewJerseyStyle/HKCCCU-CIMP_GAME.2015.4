package com.example.setsunagx.groupproject_game;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    Button goGame, about, team;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startGame:
                startActivity(new Intent(MainActivity.this, Scenes.class));
                finish();
                break;
            case R.id.about:
                startActivityForResult(new Intent(MainActivity.this, About.class), 0);
                break;
            case R.id.team:
                startActivityForResult(new Intent(MainActivity.this, Team.class), 0);
                break;
            default:
                break;
        }
    }
}
