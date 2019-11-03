package com.idcmedia.islamicpro;

import android.app.Activity;

import com.google.android.gms.ads.reward.RewardedVideoAd;

import java.util.TimerTask;

class LoadRewardedVideoAddTask extends TimerTask {
   private RewardedVideoAd adView;
   Activity mContext;
   LoadRewardedVideoAddTask(Activity context, RewardedVideoAd adView){
      mContext = context;
      this.adView = adView;
   }

   public void run() {
       //calculate the new position of myBall
      mContext.runOnUiThread(new Runnable() {
         @Override
         public void run() {
            Utils.loadRewardedVideoAd(adView,mContext);
         }
      });
   }


}