package com.idcmedia.islamicpro;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.idcmedia.islamicpro.model.DuaStubs;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static android.content.Context.MODE_PRIVATE;

public class Utils  {

    public static  final long loadAddFreqInMillis = 60000;
    public static  final long loadAddFreqInMillisForRewarded = 600000;
    public static  final long loadFullScreenAddFreqInMillisFor = 600000;
    public static final  int RUKYAH_ID = 1;
    public static final  int AZKAR_ID = 4;
    public static final  int DUA_ID = 2;
    public static final  int AYTE_SHIFA_ID = 3;
    public static final  int ADHAN_ID = 5;
    public static final  int SYMPTOMS_ID = 6;
    public static final  int NAMES99_ID = 7;
    public static  final String KEY_SHARED_PREF = "my_shared_pref";

    public static final String RUKYAH_KEY = "RUQYAH";
    public static final String DUA_KEY = "DUA";
    public static final String ADHAN_KEY = "ADHAN";
    public static final String AYTE_SHIFA_KEY = "AYTE SHIFA";
    public static final String AZKAR_KEY = "AZKAR";
    public static final String SYMPTOMS_KEY = "SYMTOMS";
    public static final String NAMES99_KEY = "ALLAH 99 NAMES";


    public static void loadAdd(AdView adView){
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public static Timer setTimeForAdd(Activity activity, AdView adView){
        LoadBannerAddTask loadAddTask = new LoadBannerAddTask(activity,adView);
        Timer  myTimer = new Timer();
        myTimer.schedule(loadAddTask, 0, loadAddFreqInMillis);

        return myTimer;
    }

    public static Timer setTimeForRewardedAdd(Activity activity, RewardedVideoAd adView){
        LoadRewardedVideoAddTask loadAddTask = new LoadRewardedVideoAddTask(activity,adView);
        Timer  myTimer = new Timer();
        myTimer.schedule(loadAddTask, loadAddFreqInMillisForRewarded, loadAddFreqInMillisForRewarded);
        return myTimer;
    }

    public static Timer setTimeForInterstelFullScreenAdd(Activity activity, InterstitialAd adView){
        LoadIntertialFullScreenAddTask loadAddTask = new LoadIntertialFullScreenAddTask(activity,adView);
        Timer  myTimer = new Timer();
        myTimer.schedule(loadAddTask, loadFullScreenAddFreqInMillisFor, loadFullScreenAddFreqInMillisFor);
        return myTimer;
    }

    public static void loadRewardedVideoAd(RewardedVideoAd mRewardedVideoAd,Context context) {
        mRewardedVideoAd.loadAd(context.getResources().getString(R.string.video_rewarded_add_id),
                new AdRequest.Builder().build());
    }

    public static void showVideo(RewardedVideoAd mRewardedVideoAd){
        if(mRewardedVideoAd.isLoaded()){
            mRewardedVideoAd.show();
        }
    }

    public static void loadInterstelFullScreenAdd(InterstitialAd mInterstitialAd){
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }





    public static void showFullScreen(InterstitialAd mInterstitialAd){
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

    public static void setSharedPref(Context context , String key, int value){
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }


    public static int getSharedPref(Context context, String key){
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE);
        int value = sharedPref.getInt(key,0);
        return  value;
    }


    public static List<DuaStubs> dummyData() {
        ArrayList<DuaStubs> duaStubs = new ArrayList<>();
        DuaStubs duaStubs1 = new DuaStubs();
        duaStubs1.setTextExplanation("المفاتيح العربية");
        duaStubs1.setTextArabic("Explanation");
        duaStubs1.setTextTranslation("askdjfadf");

        DuaStubs duaStubs2 = new DuaStubs();
        duaStubs2.setTextExplanation("المفاتيح العربية");
        duaStubs2.setTextArabic("Explanation");
        duaStubs2.setTextTranslation("askdjfadf");

        DuaStubs duaStubs3 = new DuaStubs();
        duaStubs3.setTextExplanation("المفاتيح العربية");
        duaStubs3.setTextArabic("Explanation");
        duaStubs3.setTextTranslation("askdjfadf");

        duaStubs.add(duaStubs1);
        duaStubs.add(duaStubs2);
        duaStubs.add(duaStubs3);
        return duaStubs;
    }

    public static List<DuaStubs> dummyDataDetails() {
        ArrayList<DuaStubs> duaStubs = new ArrayList<>();
        DuaStubs duaStubs1 = new DuaStubs();
        duaStubs1.setTextArabic("لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربيةلوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية ");
        duaStubs1.setTextExplanation("Explanation");
        duaStubs1.setTextTranslation("askdjfadf");
        duaStubs.add(duaStubs1);

        DuaStubs duaStubs2 = new DuaStubs();
        duaStubs2.setTextArabic("لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربيةلوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية ");
        duaStubs2.setTextExplanation("Explanation");
        duaStubs2.setTextTranslation("askdjfadf");
        duaStubs.add(duaStubs2);

        DuaStubs duaStubs3 = new DuaStubs();
        duaStubs3.setTextArabic("لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربيةلوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية ");
        duaStubs3.setTextExplanation("Explanation");
        duaStubs3.setTextTranslation("askdjfadf");
        duaStubs.add(duaStubs3);
        return duaStubs;
    }
}
