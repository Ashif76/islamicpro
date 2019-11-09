package com.idcmedia.islamicpro.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.idcmedia.islamicpro.model.DashBoardStubs;

import java.io.IOException;
import java.io.InputStream;

public class JsonConvertUtil {

    public static DashBoardStubs getDashBoardData(Context context){
        Gson gson = new Gson();
        DashBoardStubs model = gson.fromJson(loadJSONFromAssets(context,"dashboard.json"), DashBoardStubs.class);
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
