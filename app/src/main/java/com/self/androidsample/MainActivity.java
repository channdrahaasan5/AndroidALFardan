package com.self.androidsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPref = getSharedPreferences("Login",Context.MODE_PRIVATE);
                if(sharedPref.getBoolean("isLogin",false)) {
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, IntroductionActivity.class);
                    startActivity(intent);
                }
                finish();

            }
        },3000);
    }
}