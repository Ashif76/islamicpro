package com.idcmedia.islamicpro.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.idcmedia.islamicpro.model.DashBoardStubs;
import com.idcmedia.islamicpro.model.KuranSurahData;
import com.idcmedia.islamicpro.model.KuranSurahModel;
import com.idcmedia.islamicpro.model.SurahDetails;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonConvertUtil {

    public static DashBoardStubs getDashBoardData(Context context){
        Gson gson = new Gson();
        DashBoardStubs model = gson.fromJson(loadJSONFromAssets(context,"dashboard.json"), DashBoardStubs.class);
        return model;
    }

    public static ArrayList<KuranSurahData> getKuranSurahList(Context context){
        Gson gson = new Gson();
        KuranSurahModel model = gson.fromJson(loadJSONFromAssets(context,"surahs_list.json"), KuranSurahModel.class);
        return model.getData();
    }

    public static SurahDetails getKuranSurahDetails(Context context, int surahNumber){
        String fileName = surahNumber+"."+"pretty.json";
        Gson gson = new Gson();
        SurahDetails model = gson.fromJson(loadJSONFromAssets(context,fileName), SurahDetails.class);
        return model;
    }

    private static String loadJSONFromAssets(Context context, String fileName) {
        byte[] buffer =null;
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
           json= new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
