package com.idcmedia.islamicpro.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.fragment.AzkarViewPagerFragment;
import com.idcmedia.islamicpro.fragment.RukyahViewPagerFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AzkarMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_azkar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (savedInstanceState == null) {
            Fragment newFragment = AzkarViewPagerFragment.newInstance("","");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.ll_rukyah_main, newFragment).commit();
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
