package com.praktikum.myfourthandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
// Hamas Ardyan Prasetyo
// 1900018121
public class MainActivity extends AppCompatActivity {
    static final String tag = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(tag, "Ini Di OnCreate");
        Toast.makeText(MainActivity.this, "OnCreate", Toast.LENGTH_SHORT).show();

        Button login1 = (Button) findViewById(R.id.btnLogin);
        login1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onStart() {
        super.onStart();
        Log.v(tag, "onStart");
        Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.v(tag, "Ini Di OnResume");
        Toast.makeText(MainActivity.this, "Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(tag, "Ini Di OnPause");
        Toast.makeText(MainActivity.this, "Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(tag, "Ini Di OnStop");
        Toast.makeText(MainActivity.this, "Stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(tag, "Ini Di OnDestroy");
        Toast.makeText(MainActivity.this, "Destroy", Toast.LENGTH_SHORT).show();
    }

    public void register(View view) {

    }
    public void resetPass(View view) {

    }
    public void login(View view) {

    }

}