package com.idcmedia.islamicpro.utils;

import android.content.Context;
import android.os.Build;

import com.google.gson.Gson;
import com.idcmedia.islamicpro.model.AdhanList;
import com.idcmedia.islamicpro.model.CommonDuaContent;
import com.idcmedia.islamicpro.model.CommonDuaStubs;
import com.idcmedia.islamicpro.model.DashBoardDuaStubs;
import com.idcmedia.islamicpro.model.DashBoardStubs;
import com.idcmedia.islamicpro.model.DuaList;
import com.idcmedia.islamicpro.model.KuranSurahData;
import com.idcmedia.islamicpro.model.KuranSurahModel;
import com.idcmedia.islamicpro.model.SurahDetails;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import androidx.annotation.RequiresApi;

public class JsonConvertUtil {

    public static DashBoardStubs getDashBoardData(Context context){
        Gson gson = new Gson();
        DashBoardStubs model = gson.fromJson(loadJSONFromAssets(context,"dashboard.json"), DashBoardStubs.class);
        return model;
    }

    public static SymptomsData getSymptomsData(Context context){
        Gson gson = new Gson();
        SymptomsData model = gson.fromJson(loadJSONFromAssets(context,"symptoms.json"), SymptomsData.class);
        return model;
    }

    public static DashBoardDuaStubs getDashBoardDuaData(Context context){
        Gson gson = new Gson();
        DashBoardDuaStubs model = gson.fromJson(loadJSONFromAssets(context,"dashboard_dua_list.json"), DashBoardDuaStubs.class);
        return model;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Map<String, List<CommonDuaContent>> getDuaData(Context context,int filterId){
        Gson gson = new Gson();
        CommonDuaStubs model = gson.fromJson(loadJSONFromAssets(context,"all_dua.json"), CommonDuaStubs.class);
        return convertToMap(model,filterId);

    }


    private static Map<String, List<CommonDuaContent>> convertToMap(CommonDuaStubs model, int filterId){
        Map<String, List<CommonDuaContent>> duaMap = new HashMap<>();
        List<CommonDuaContent> content = model.getContent();
        if(content!=null){
            for (CommonDuaContent item: content) {
                if (filterId ==-1 ||item.getDuaTypeId()==filterId) {
                    List<CommonDuaContent> commonDuaContents = duaMap.get(item.getHeading());
                    if (commonDuaContents == null) {
                        commonDuaContents = new ArrayList<>();
                    }
                    commonDuaContents.add(item);
                    duaMap.put(item.getHeading(), commonDuaContents);
                }
            }
        }
        return duaMap;
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

    public static DuaList getRukyah(Context context, String fileName){
        Gson gson = new Gson();
        DuaList model = gson.fromJson(loadJSONFromAssets(context,fileName), DuaList.class);
        return model;
    }

    public static AdhanList getAdhan(Context context, String fileName){
        Gson gson = new Gson();
        AdhanList model = gson.fromJson(loadJSONFromAssets(context,fileName), AdhanList.class);
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
