package com.example.daocomics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.example.daocomics.ui.login_register.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {
    TextView tvChao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                } catch (Exception e) {
                }
            }
        };thread.start();

    }
}