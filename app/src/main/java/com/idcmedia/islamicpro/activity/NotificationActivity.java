package com.idcmedia.islamicpro.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.fragment.AzkarViewPagerFragment;
import com.idcmedia.islamicpro.fragment.DuaDetailsFragment;
import com.idcmedia.islamicpro.fragment.DuaListFragment;
import com.idcmedia.islamicpro.fragment.NotificationListFragment;
import com.idcmedia.islamicpro.model.CommonDuaContent;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_azkar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Daily Hadith");
        if (savedInstanceState == null) {


            Fragment newFragment = NotificationListFragment.newInstance(1,null);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.ll_rukyah_main, newFragment,"NotificationListFragment");
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
