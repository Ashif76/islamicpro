package com.idcmedia.islamicpro;

import android.app.Activity;

import com.google.android.gms.ads.AdView;

import java.util.TimerTask;

class LoadBannerAddTask extends TimerTask {
   private AdView adView;
   Activity mContext;
   LoadBannerAddTask(Activity context, AdView adView){
      mContext = context;
      this.adView = adView;
   }

   public void run() {
       //calculate the new position of myBall
      mContext.runOnUiThread(new Runnable() {
         @Override
         public void run() {
            Utils.loadAdd(adView);
         }
      });
   }


}