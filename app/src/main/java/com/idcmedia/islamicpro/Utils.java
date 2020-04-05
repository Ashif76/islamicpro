package com.idcmedia.islamicpro;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.idcmedia.islamicpro.model.DuaStubs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import androidx.core.content.ContextCompat;

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
    public static  final String KEY_NOTIFICATION_SHARED_PREF = "my_notification_shared_pref";

    public static final String RUKYAH_KEY = "RUQYAH";
    public static final String DUA_KEY = "DUA";
    public static final String ADHAN_KEY = "ADHAN";
    public static final String AYTE_SHIFA_KEY = "AYTE SHIFA";
    public static final String AZKAR_KEY = "AZKAR";
    public static final String SYMPTOMS_KEY = "SYMTOMS";
    public static final String NAMES99_KEY = "ALLAH 99 NAMES";

    public static final String SURAH_POSITION_KEY = "surah_position";
    public static final String AYAT_POSITION_KEY = "ayat_position";
    public static final String YOUTUBE_API_KEY = "AIzaSyCSP-4CfseY2oPhFoInQ-xpnUxn2wObzQ0";




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

    public static void setIntSharedPref(Context context , String key, int value){
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setStringSharedPref(Context context , String key, String value){
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY_NOTIFICATION_SHARED_PREF, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getStringValueSharedPref(Context context, String key){
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_NOTIFICATION_SHARED_PREF, Context.MODE_PRIVATE);
        String value = sharedPref.getString(key,"");
        return  value;
    }
    public static int getStringSharedPref(Context context, String key){
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_NOTIFICATION_SHARED_PREF, Context.MODE_PRIVATE);
        int value = sharedPref.getInt(key,0);
        return  value;
    }

    public static SharedPreferences getStringSharedPrefAll(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_NOTIFICATION_SHARED_PREF, Context.MODE_PRIVATE);

        return  sharedPref;
    }


    public static int getIntSharedPref(Context context, String key){
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE);
        int value = sharedPref.getInt(key,0);
        return  value;
    }

    public static void setBooleanSharedPref(Context context , String key, boolean value){
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


    public static boolean getBooleanSharedPref(Context context, String key){
        SharedPreferences sharedPref = context.getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE);
        boolean value = sharedPref.getBoolean(key,true);
        return  value;
    }


    public static List<DuaStubs> dummyData() {
        ArrayList<DuaStubs> duaStubs = new ArrayList<>();
        DuaStubs duaStubs1 = new DuaStubs();
        duaStubs1.setTextExplanation("المفاتيح العربية");
        duaStubs1.setTextArabic("Explanation");
        duaStubs1.setVerse("askdjfadf");

        DuaStubs duaStubs2 = new DuaStubs();
        duaStubs2.setTextExplanation("المفاتيح العربية");
        duaStubs2.setTextArabic("Explanation");
        duaStubs2.setVerse("askdjfadf");

        DuaStubs duaStubs3 = new DuaStubs();
        duaStubs3.setTextExplanation("المفاتيح العربية");
        duaStubs3.setTextArabic("Explanation");
        duaStubs3.setVerse("askdjfadf");

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
        duaStubs1.setVerse("askdjfadf");
        duaStubs.add(duaStubs1);

        DuaStubs duaStubs2 = new DuaStubs();
        duaStubs2.setTextArabic("لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربيةلوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية ");
        duaStubs2.setTextExplanation("Explanation");
        duaStubs2.setVerse("askdjfadf");
        duaStubs.add(duaStubs2);

        DuaStubs duaStubs3 = new DuaStubs();
        duaStubs3.setTextArabic("لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربيةلوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربية لوحة المفاتيح العربيةالمفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية المفاتيح العربية لوحة المفاتيح العربية ");
        duaStubs3.setTextExplanation("Explanation");
        duaStubs3.setVerse("askdjfadf");
        duaStubs.add(duaStubs3);
        return duaStubs;
    }

    public static String getRootDirPath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = ContextCompat.getExternalFilesDirs(context.getApplicationContext(),
                    null)[0];
            return file.getAbsolutePath();
        } else {
            return context.getApplicationContext().getFilesDir().getAbsolutePath();
        }
    }

    public static String getProgressDisplayLine(long currentBytes, long totalBytes) {
        return getBytesToMBString(currentBytes) + "/" + getBytesToMBString(totalBytes);
    }

    private static String getBytesToMBString(long bytes){
        return String.format(Locale.ENGLISH, "%.2fMb", bytes / (1024.00 * 1024.00));
    }
}
