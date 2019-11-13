package com.idcmedia.islamicpro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.adapter.DashBoardHomeActivityAdapter;
import com.idcmedia.islamicpro.model.DashBoardData;
import com.idcmedia.islamicpro.model.DashBoardStubs;
import com.idcmedia.islamicpro.model.GridSpacingItemDecoration;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.utils.JsonConvertUtil;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DashBoardHomeActivity extends AppCompatActivity implements ItemClickListener<DashBoardData>, NavigationView.OnNavigationItemSelectedListener {

    private int mColumnCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        RecyclerView recyclerView = findViewById(R.id.rv_home);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, mColumnCount));
        }
        int spanCount =2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        DashBoardStubs dashBoardStubs = JsonConvertUtil.getDashBoardData(this);
        recyclerView.setAdapter(new DashBoardHomeActivityAdapter(dashBoardStubs.getDashBoardData(), this));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        setupTabView(tabLayout);

    }
    public void setupTabView(TabLayout tabLayout){
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.custom_tab_view).setTag(1));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.custom_tab_view).setTag(2));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.custom_tab_view).setTag(3));
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
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tagId = (int) tab.getTag();
                if(tagId == 1){
                    Intent intent = new Intent(DashBoardHomeActivity.this,KuranMainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int tagId = (int) tab.getTag();
                if(tagId == 1){
                    Intent intent = new Intent(DashBoardHomeActivity.this,KuranMainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SettingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(this,SettingActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(DashBoardData item) {
        int id = item.getId();
        switch (id){
            case Utils.DUA_ID :{
                Intent intent = new Intent(this, DashBoardDuaMainActivity.class);
                startActivity(intent);
                break;
            }

            case Utils.RUKYAH_ID :{
                Intent intent = new Intent(this,RukyahMainActivity.class);
                startActivity(intent);
                break;
            }
            case Utils.AZKAR_ID :{
                Intent intent = new Intent(this,AzkarMainActivity.class);
                startActivity(intent);
                break;
            }

            case Utils.AYTE_SHIFA_ID :{
                Intent intent = new Intent(this,CommonDetailsMainActivity.class);
                startActivity(intent);
                break;
            }

            case Utils.ADHAN_ID :{
                Intent intent = new Intent(this,CommonDetailsMainActivity.class);
                startActivity(intent);
                break;
            }

            case Utils.SYMPTOMS_ID :{
                Intent intent = new Intent(this,CommonDetailsMainActivity.class);
                startActivity(intent);
                break;
            }

            case Utils.NAMES99_ID :{
                Intent intent = new Intent(this,CommonListMainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
}
