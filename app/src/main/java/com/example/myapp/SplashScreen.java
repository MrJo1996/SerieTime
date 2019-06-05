package com.example.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapp.Database.DatabaseHelper;

public class SplashScreen extends AppCompatActivity {
    DatabaseHelper dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        dbManager = new DatabaseHelper(this);

        //SplashScreen
        Thread splash = new Thread() {
            public void run() {
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (isLogged()) {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        splash.start();
    }

    private boolean isLogged() {
        dbManager.getWritableDatabase();
        Cursor cursor = dbManager.getUtente(1);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
