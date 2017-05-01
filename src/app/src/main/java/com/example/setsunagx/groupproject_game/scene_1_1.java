package com.example.setsunagx.groupproject_game;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class scene_1_1 extends ActionBarActivity implements View.OnClickListener{
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_base);
        getSupportActionBar().hide();
        next = (Button) findViewById(R.id.button);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //final Intent intent = new Intent(this, scene_1_1.class);
        //startActivity(intent);
    }
}
