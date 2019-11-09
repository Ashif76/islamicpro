package com.idcmedia.islamicpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.idcmedia.islamicpro.activity.DashBoardHomeActivity;


public class SplashScreen extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utils.setSharedPref(this, Utils.DUA_KEY,0);
        Utils.setSharedPref(this, Utils.RUKYAH_KEY,0);
        Utils.setSharedPref(this, Utils.AZKAR_KEY,0);
        Utils.setSharedPref(this, Utils.NAMES99_KEY,0);
        Utils.setSharedPref(this, Utils.AYTE_SHIFA_KEY,0);
        Utils.setSharedPref(this, Utils.SYMPTOMS_KEY,0);
        Utils.setSharedPref(this, Utils.ADHAN_KEY,0);
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {

                    Intent i = new Intent(SplashScreen.this, DashBoardHomeActivity.class);
                    startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


}