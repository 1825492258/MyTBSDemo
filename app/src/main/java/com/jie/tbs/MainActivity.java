package com.jie.tbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jie.tbs.activity.TextOneActivity;
import com.jie.tbs.activity.TextThreeActivity;
import com.jie.tbs.activity.TextTwoActivity;
import com.jie.tbs.player.TextPlayerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnOne).setOnClickListener(this);
        findViewById(R.id.btnTwo).setOnClickListener(this);
        findViewById(R.id.btnThree).setOnClickListener(this);
        findViewById(R.id.btnFour).setOnClickListener(this);
        findViewById(R.id.btnFive).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                startActivity(new Intent(this,TextOneActivity.class));
                break;
            case R.id.btnTwo:
                startActivity(new Intent(this,TextTwoActivity.class));
                break;
            case R.id.btnThree:
                startActivity(new Intent(this,TextThreeActivity.class));
                break;
            case R.id.btnFour:
                break;
            case R.id.btnFive:
                startActivity(new Intent(this,TextPlayerActivity.class));
                break;
        }
    }
}
