package com.idcmedia.islamicpro;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import com.idcmedia.islamicpro.activity.DashBoardHomeActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.idcmedia.islamicpro.MainActivity.MY_PERMISSIONS_REQUEST_LOCATION;


public class SplashScreen extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utils.setIntSharedPref(this, Utils.DUA_KEY,0);
        Utils.setIntSharedPref(this, Utils.RUKYAH_KEY,0);
        Utils.setIntSharedPref(this, Utils.AZKAR_KEY,0);
        Utils.setIntSharedPref(this, Utils.NAMES99_KEY,0);
        Utils.setIntSharedPref(this, Utils.AYTE_SHIFA_KEY,0);
        Utils.setIntSharedPref(this, Utils.SYMPTOMS_KEY,0);
        Utils.setIntSharedPref(this, Utils.ADHAN_KEY,0);
//        checkLocationPermission();
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