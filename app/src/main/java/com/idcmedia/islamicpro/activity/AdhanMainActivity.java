package com.idcmedia.islamicpro.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.fragment.AdhanViewPagerFragment;
import com.idcmedia.islamicpro.fragment.RukyahViewPagerFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AdhanMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rukyah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (savedInstanceState == null) {
            Fragment newFragment = AdhanViewPagerFragment.newInstance("","");
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
