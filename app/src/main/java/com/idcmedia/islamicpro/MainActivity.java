package com.idcmedia.islamicpro;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout rlDua;
    private LinearLayout llAzkar;
    private LinearLayout llRukyah;
    private LinearLayout  llAdhan;
    private LinearLayout llAyteShifa;
    private LinearLayout llSymptoms;
    private AdView adView;
    private InterstitialAd mInterstitialAd;
    private LinearLayout llIdcTv;
    private LinearLayout llHushnalMushlim;
    private LinearLayout ll99Names;
    private static final int REQUEST_CODE = 100;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_temp);
        rlDua = findViewById(R.id.rlDua);
        llAzkar = findViewById(R.id.ll_azkar);
        llAdhan = findViewById(R.id.ll_adhan);
        llRukyah = findViewById(R.id.rlRukyah);
        llAyteShifa = findViewById(R.id.ll_ayte_shifa);
        llSymptoms = findViewById(R.id.ll_symptoms);
        llIdcTv = findViewById(R.id.ll_idc_tv);
        llHushnalMushlim = findViewById(R.id.ll_husnal_mushlim);
        ll99Names = findViewById(R.id.ll_99_names);
        Toolbar toolbar = findViewById(R.id.toolbar);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(toolbar.getTitle());
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.page_intersial_add_id));
        checkLocationPermission();
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent(MainActivity.this, PDFViewActivity.class);
//                startActivity(intent);

            }
        });
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        llRukyah.setOnClickListener(this);
        rlDua.setOnClickListener(this);
        llAdhan.setOnClickListener(this);
        llAyteShifa.setOnClickListener(this);
        llAzkar.setOnClickListener(this);
        llSymptoms.setOnClickListener(this);
        llIdcTv.setOnClickListener(this);
        llHushnalMushlim.setOnClickListener(this);
        ll99Names.setOnClickListener(this);
        adView = findViewById(R.id.adView);
        MobileAds.initialize(this, getResources().getString(R.string.app_add_id));
        Utils.loadAdd(adView);
        Utils.setTimeForAdd(this,adView);
        fullScreenListener();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){

            case R.id.rlRukyah:{
                int value = Utils.getSharedPref(this, Utils.RUKYAH_KEY);
                Utils.setSharedPref(this, Utils.RUKYAH_KEY,value+1);
                Intent intent = new Intent(MainActivity.this, RuqyahActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            }
            case R.id.rlDua:{
                int value = Utils.getSharedPref(this, Utils.DUA_KEY);
                Utils.setSharedPref(this, Utils.DUA_KEY,value+1);
                startActivityIntent("dua.pdf", Utils.DUA_KEY);
                break;
            }
            case R.id.ll_adhan:{
                int value = Utils.getSharedPref(this, Utils.ADHAN_KEY);
                Utils.setSharedPref(this, Utils.ADHAN_KEY,value+1);
                startActivityIntent("adhan.pdf", Utils.ADHAN_KEY);
                break;
            }
            case R.id.ll_ayte_shifa:{
                int value = Utils.getSharedPref(this, Utils.AYTE_SHIFA_KEY);
                Utils.setSharedPref(this, Utils.AYTE_SHIFA_KEY,value+1);
                startActivityIntent("aytesh.pdf", Utils.AYTE_SHIFA_KEY);
                break;
            }
            case R.id.ll_azkar:{
                int value = Utils.getSharedPref(this, Utils.AZKAR_KEY);
                Utils.setSharedPref(this, Utils.AZKAR_KEY,value+1);
                Intent intent = new Intent(MainActivity.this, AzkarActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            }
            case R.id.ll_symptoms:{
                int value = Utils.getSharedPref(this, Utils.SYMPTOMS_KEY);
                Utils.setSharedPref(this, Utils.SYMPTOMS_KEY,value+1);
                startActivityIntent("symptoms.pdf", Utils.SYMPTOMS_KEY);
                break;
            }

            case R.id.ll_husnal_mushlim:{
                startActivityIntent("symptoms.pdf", Utils.DUA_KEY);
                break;
            }

            case R.id.ll_99_names:{
                int value = Utils.getSharedPref(this, Utils.NAMES99_KEY);
                Utils.setSharedPref(this, Utils.NAMES99_KEY,value+1);
                startActivityIntent("99names.pdf", Utils.NAMES99_KEY);
                break;
            }

            case R.id.ll_idc_tv:{
                Intent intent = new Intent(MainActivity.this, WatchVideoActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.setSharedPref(this, Utils.DUA_KEY,0);
        Utils.setSharedPref(this, Utils.RUKYAH_KEY,0);
        Utils.setSharedPref(this, Utils.AZKAR_KEY,0);
        Utils.setSharedPref(this, Utils.NAMES99_KEY,0);
        Utils.setSharedPref(this, Utils.AYTE_SHIFA_KEY,0);
        Utils.setSharedPref(this, Utils.SYMPTOMS_KEY,0);
        Utils.setSharedPref(this, Utils.ADHAN_KEY,0);
    }

    private void fullScreenListener() {

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
//                Toast.makeText(MainActivity.this, "add loaded", Toast.LENGTH_SHORT).show();
                Utils.showFullScreen(mInterstitialAd);
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
//                Toast.makeText(MainActivity.this, "add failed"+errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
//                Toast.makeText(MainActivity.this, "add opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
//                Toast.makeText(MainActivity.this, "adds loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
//                Toast.makeText(MainActivity.this, "adds left app", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
//                Toast.makeText(MainActivity.this, "adds closed", Toast.LENGTH_SHORT).show();
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    private void startActivityIntent(String fileName, String classType){
        Intent intent = new Intent(MainActivity.this, PdfRenderActivity.class);
        intent.putExtra(PdfRenderActivity.FILE_NAME,fileName);
        intent.putExtra(PdfRenderActivity.CLASS_TYPE,classType);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Utils.loadInterstelFullScreenAdd(mInterstitialAd);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
//                        locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }
}
