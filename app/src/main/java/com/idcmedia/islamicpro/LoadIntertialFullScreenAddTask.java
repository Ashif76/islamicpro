package com.idcmedia.islamicpro;

import android.app.Activity;

import com.google.android.gms.ads.InterstitialAd;

import java.util.TimerTask;

class LoadIntertialFullScreenAddTask extends TimerTask {
   private InterstitialAd adView;
   Activity mContext;
   LoadIntertialFullScreenAddTask(Activity context, InterstitialAd adView){
      mContext = context;
      this.adView = adView;
   }

   public void run() {
       //calculate the new position of myBall
      mContext.runOnUiThread(new Runnable() {
         @Override
         public void run() {
            Utils.loadInterstelFullScreenAdd(adView);
         }
      });
   }


}