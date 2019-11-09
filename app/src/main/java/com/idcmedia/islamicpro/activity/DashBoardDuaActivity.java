package com.idcmedia.islamicpro.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.adapter.DashBoardDuaActivityAdapter;
import com.idcmedia.islamicpro.adapter.DashBoardHomeActivityAdapter;
import com.idcmedia.islamicpro.model.DashBoardData;
import com.idcmedia.islamicpro.model.DashBoardStubs;
import com.idcmedia.islamicpro.model.GridSpacingItemDecoration;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.utils.JsonConvertUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DashBoardDuaActivity extends AppCompatActivity implements ItemClickListener<DashBoardData> {

    private int mColumnCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_dua_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.rv_home);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
        int spanCount =3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        DashBoardStubs dashBoardStubs = JsonConvertUtil.getDashBoardData(this);
        recyclerView.setAdapter(new DashBoardDuaActivityAdapter(dashBoardStubs.getDashBoardData(), this));

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

    @Override
    public void onItemClick(DashBoardData o) {

    }
}
