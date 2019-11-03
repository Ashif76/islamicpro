package com.idcmedia.islamicpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class RuqyahActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llShortRuq;
    LinearLayout llLongRuq;
    LinearLayout llGeneralRuq;
    LinearLayout llCompleteRuq;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruqyah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        llShortRuq = findViewById(R.id.ll_shortrukya);

        llLongRuq = findViewById(R.id.ll_long_ruq);
        llGeneralRuq = findViewById(R.id.ll_general_ruq);
        llCompleteRuq = findViewById(R.id.ll_complete_ruq);
        adView = findViewById(R.id.adView);
        llShortRuq.setOnClickListener(this);
        llLongRuq.setOnClickListener(this);
        llGeneralRuq.setOnClickListener(this);
        llCompleteRuq.setOnClickListener(this);
        MobileAds.initialize(this, getResources().getString(R.string.app_add_id));
        Utils.loadAdd(adView);
        Utils.setTimeForAdd(this,adView);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        int id  = v.getId();
        switch (id){
            case R.id.ll_shortrukya:{
                startActivityIntent("short_ruq.pdf");
                break;
            }
            case R.id.ll_long_ruq:{
                startActivityIntent("longruqyah.pdf");
                break;
            }
            case R.id.ll_general_ruq:{
                startActivityIntent("general_ruq.pdf");
                break;
            }
            case R.id.ll_complete_ruq:{
                startActivityIntent("complete_ruqyah.pdf");
                break;
            }
        }
    }


    private void startActivityIntent(String fileName){
        Intent intent = new Intent(this, PdfRenderActivity.class);
        intent.putExtra(PdfRenderActivity.FILE_NAME,fileName);
        startActivity(intent);
    }


}
