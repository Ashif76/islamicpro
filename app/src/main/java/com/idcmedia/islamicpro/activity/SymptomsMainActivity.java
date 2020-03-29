package com.idcmedia.islamicpro.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.fragment.DashBoardDuaFragment;
import com.idcmedia.islamicpro.fragment.SymtomsListFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SymptomsMainActivity extends AppCompatActivity  {

    private int mColumnCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_azkar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (savedInstanceState == null) {
            Fragment newFragment = SymtomsListFragment.newInstance(1);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.ll_rukyah_main, newFragment).commit();
        }
//        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
//        setupTabView(tabLayout);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void setupTabView(TabLayout tabLayout){
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.custom_tab_view));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.custom_tab_view));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.custom_tab_view));
        TabLayout.Tab tab1 = tabLayout.getTabAt(0);
        ImageView tab1Icon = tab1.getCustomView().findViewById(R.id.iv_icon);
        TextView tab1TextView = tab1.getCustomView().findViewById(R.id.txt_tab_name);
        tab1Icon.setImageDrawable(getResources().getDrawable(R.drawable.adhan_1));
        tab1TextView.setText("Kuran");

        TabLayout.Tab tab2 = tabLayout.getTabAt(1);
        ImageView tab2Icon = tab2.getCustomView().findViewById(R.id.iv_icon);
        TextView tab2TextView = tab2.getCustomView().findViewById(R.id.txt_tab_name);
        tab2Icon.setImageDrawable(getResources().getDrawable(R.drawable.rukya_2));
        tab2TextView.setText("Prayer");


        TabLayout.Tab tab3 = tabLayout.getTabAt(2);
        ImageView tab3Icon = tab3.getCustomView().findViewById(R.id.iv_icon);
        TextView tab3TextView = tab3.getCustomView().findViewById(R.id.txt_tab_name);
        tab3Icon.setImageDrawable(getResources().getDrawable(R.drawable.adhan_1));
        tab3TextView.setText("Muslim");

    }


}
