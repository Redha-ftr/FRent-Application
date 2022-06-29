package com.redha.frent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        Thread timer = new Thread(){

            @Override
            public void run() {
                try{
                    sleep(2000);
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    Intent intentMain =  new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intentMain);
                    finish();
                }
            }
        };
        timer.start();
    }
}