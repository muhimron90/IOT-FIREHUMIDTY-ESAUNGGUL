package com.example.chickenronz.iot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class splash extends AppCompatActivity {
    private int SLEEP_TIME = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        launcher ss = new launcher();
        ss.start();
    }


    private class launcher extends Thread{
        @Override
        public void run() {
            try {
                sleep(1000 * SLEEP_TIME);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent intent = new Intent(splash.this, MainActivity.class);
            startActivity(intent);

            splash.this.finish();
        }

    }
}
