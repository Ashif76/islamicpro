package com.idcmedia.islamicpro.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.fragment.AdhanPagerFragmentDetails;
import com.idcmedia.islamicpro.fragment.CommonDetailsFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class NamesMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kuran);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (savedInstanceState == null) {
            Fragment newFragment =  AdhanPagerFragmentDetails.newInstance(1,"names_99.json");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.ll_rukyah_main, newFragment,"CommonDetailsFragment");
//            ft.addToBackStack("null");
            ft.commit();
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
