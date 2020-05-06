package com.idcmedia.islamicpro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;
import com.idcmedia.islamicpro.fragment.KuranInstallationListFragment;
import com.idcmedia.islamicpro.fragment.KuranListFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class KuranFullReadingMainActivity extends AppCompatActivity {
    KuranInstallationListFragment newFragment;
    private RewardedVideoAd mRewardedVideoAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kuran);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
//        videoAddListener();
//        Utils.loadRewardedVideoAd(mRewardedVideoAd,this);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.page_intersial_add_id));
//        fullScreenListener();
        if (savedInstanceState == null) {
             newFragment = KuranInstallationListFragment.newInstance(1);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.ll_rukyah_main, newFragment,"KuranInstallationListFragment");
//            ft.addToBackStack("null");
            ft.commit();
        }

    }

    private void videoAddListener(){
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
//                 Toast.makeText(PdfRenderActivity.this, "add loaded", Toast.LENGTH_SHORT).show();
//                Utils.showVideo(mRewardedVideoAd);
//                Utils.showVideo(mRewardedVideoAd);

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
//                loadRewardedVideoAd();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
//                 Toast.makeText(PdfRenderActivity.this, "add failed rewarded"+i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });
    }

//    @Override
//    protected void onResume() {
//        mRewardedVideoAd.resume(this);
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        mRewardedVideoAd.pause(this);
//        super.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        mRewardedVideoAd.destroy(this);
//        super.onDestroy();
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(newFragment!=null){
            newFragment.onItemClick(null);
        }

//        Utils.showVideo(mRewardedVideoAd);
//        Utils.loadRewardedVideoAd(mRewardedVideoAd,this);
        Utils.showFullScreen(mInterstitialAd);
        Utils.loadInterstelFullScreenAdd(mInterstitialAd);


    }
    private InterstitialAd mInterstitialAd;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
