package com.idcmedia.islamicpro.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.SplashScreen;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.adapter.DashBoardHomeActivityAdapter;
import com.idcmedia.islamicpro.model.DashBoardData;
import com.idcmedia.islamicpro.model.DashBoardStubs;
import com.idcmedia.islamicpro.model.GridSpacingItemDecoration;
import com.idcmedia.islamicpro.model.ItemClickListener;
import com.idcmedia.islamicpro.utils.JsonConvertUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.idcmedia.islamicpro.MainActivity.MY_PERMISSIONS_REQUEST_LOCATION;

public class DashBoardHomeActivity extends AppCompatActivity implements ItemClickListener<DashBoardData>, NavigationView.OnNavigationItemSelectedListener {

    private int mColumnCount = 2;
    private AdView adView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        checkLocationPermission();
        MobileAds.initialize(this, getResources().getString(R.string.app_add_id));

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


        adView = findViewById(R.id.adView);
        RecyclerView recyclerView = findViewById(R.id.rv_home);
        smallAddListener();
        Utils.loadAdd(adView);
        Utils.setTimeForAdd(this,adView);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.page_intersial_add_id));
        fullScreenListener();
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

    private void fullScreenListener() {

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
//                Toast.makeText(PdfRenderActivity.this, "add loaded", Toast.LENGTH_SHORT).show();
//                Utils.showFullScreen(mInterstitialAd);
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
//                Toast.makeText(PdfRenderActivity.this, "add failed"+errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
//                Toast.makeText(PdfRenderActivity.this, "add opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
//                Toast.makeText(PdfRenderActivity.this, "adds loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
//                Toast.makeText(PdfRenderActivity.this, "adds left app", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
//                Toast.makeText(PdfRenderActivity.this, "adds closed", Toast.LENGTH_SHORT).show();
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }


    private void smallAddListener(){
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
//                 Toast.makeText(PdfRenderActivity.this, "add loaded", Toast.LENGTH_SHORT).show();
                // Code to be executed when an ad finishes loading.

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
//                 Toast.makeText(PdfRenderActivity.this, "add failed"+errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
//                 Toast.makeText(PdfRenderActivity.this, "add opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
//                 Toast.makeText(PdfRenderActivity.this, "adds loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
//                 Toast.makeText(PdfRenderActivity.this, "adds left app", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
//                 Toast.makeText(PdfRenderActivity.this, "adds closed", Toast.LENGTH_SHORT).show();
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    public void setupTabView(TabLayout tabLayout){
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.custom_tab_view).setTag(1));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.custom_tab_view).setTag(2));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.custom_tab_view).setTag(3));

//        TabLayout.Tab tab2 = tabLayout.getTabAt(3);
//        ImageView tab2Icon = tab2.getCustomView().findViewById(R.id.iv_icon);
//        TextView tab2TextView = tab2.getCustomView().findViewById(R.id.txt_tab_name);
//        tab2Icon.setImageDrawable(getResources().getDrawable(R.drawable.home_96));
//        tab2TextView.setText("Notification");

        TabLayout.Tab tab2 = tabLayout.getTabAt(0);
        ImageView tab2Icon = tab2.getCustomView().findViewById(R.id.iv_icon);
        TextView tab2TextView = tab2.getCustomView().findViewById(R.id.txt_tab_name);
        tab2Icon.setImageDrawable(getResources().getDrawable(R.drawable.home_96));
        tab2TextView.setText("Home");

        TabLayout.Tab tab1 = tabLayout.getTabAt(1);
        ImageView tab1Icon = tab1.getCustomView().findViewById(R.id.iv_icon);
        TextView tab1TextView = tab1.getCustomView().findViewById(R.id.txt_tab_name);
        tab1Icon.setImageDrawable(getResources().getDrawable(R.drawable.kuran_skecth96));
        tab1TextView.setText("Quran");





        TabLayout.Tab tab3 = tabLayout.getTabAt(2);
        ImageView tab3Icon = tab3.getCustomView().findViewById(R.id.iv_icon);
        TextView tab3TextView = tab3.getCustomView().findViewById(R.id.txt_tab_name);
        tab3Icon.setImageDrawable(getResources().getDrawable(R.drawable.play_48));
        tab3TextView.setText("Watch Videos");
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tagId = (int) tab.getTag();
                if(tagId == 1){
                    Intent intent = new Intent(DashBoardHomeActivity.this,DashBoardHomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
                if(tagId == 2){
                    Intent intent = new Intent(DashBoardHomeActivity.this,QuranNewMainActivity.class);
                    startActivity(intent);

                }

                if(tagId==3){
                    Intent intent = new Intent(DashBoardHomeActivity.this,ChannelVideoMainActivity.class);
                    startActivityForResult(intent,200);
//                    Intent intent = new Intent(DashBoardHomeActivity.this,NotificationActivity.class);
//                    startActivityForResult(intent,200);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int tagId = (int) tab.getTag();
//                if(tagId == 1){
//                    Intent intent = new Intent(DashBoardHomeActivity.this,QuranNewMainActivity.class);
//                    startActivity(intent);
//                }
//                if(tagId == 3){
//                    Intent intent = new Intent(DashBoardHomeActivity.this,ChannelVideoMainActivity.class);
//                    startActivityForResult(intent,200);
//                }
//
//                if(tagId==2){
//                    Intent intent = new Intent(DashBoardHomeActivity.this,DashBoardHomeActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                }

                if(tagId == 1){
                    Intent intent = new Intent(DashBoardHomeActivity.this,DashBoardHomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                if(tagId == 2){
                    Intent intent = new Intent(DashBoardHomeActivity.this,QuranNewMainActivity.class);
                    startActivity(intent);
                }

                if(tagId==3){
//                    Intent intent = new Intent(DashBoardHomeActivity.this,DashBoardHomeActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
                    Intent intent = new Intent(DashBoardHomeActivity.this,ChannelVideoMainActivity.class);
                    startActivityForResult(intent,200);
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

        if (id == R.id.nav_kuran) {
            // Handle the camera action
            Intent intent = new Intent(DashBoardHomeActivity.this,QuranNewMainActivity.class);
            startActivityForResult(intent,200);
        }else if (id == R.id.nav_notify) {
            Intent intent = new Intent(DashBoardHomeActivity.this,NotificationActivity.class);
            startActivityForResult(intent,200);
        }  else if (id == R.id.nav_gallery) {

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
                startActivityForResult(intent,200);
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
                startActivityForResult(intent,200);
                break;
            }

            case Utils.ADHAN_ID :{
//                Intent intent = new Intent(this,CommonDetailsMainActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(this,AdhanMainActivity.class);
                startActivityForResult(intent,200);
                break;
            }

            case Utils.SYMPTOMS_ID :{
                Intent intent = new Intent(this,SymptomsMainActivity.class);
                startActivityForResult(intent,200);
                break;
            }

            case Utils.NAMES99_ID :{
                Intent intent = new Intent(this,NamesMainActivity.class);
                startActivityForResult(intent,200);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Utils.showFullScreen(mInterstitialAd);
        Utils.loadInterstelFullScreenAdd(mInterstitialAd);
    }

    //    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }

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
                                ActivityCompat.requestPermissions(DashBoardHomeActivity.this,
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
