package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SurahDetails {

    private int number;

    @SerializedName("total_verses")
    private int totalVerses;
    @SerializedName("revelation_type")
    private String revelationType ;
    private ArrayList<SurahVerse> verses;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalVerses() {
        return totalVerses;
    }

    public void setTotalVerses(int totalVerses) {
        this.totalVerses = totalVerses;
    }

    public String getRevelationType() {
        return revelationType;
    }

    public void setRevelationType(String revelationType) {
        this.revelationType = revelationType;
    }

    public ArrayList<SurahVerse> getVerses() {
        return verses;
    }

    public void setVerses(ArrayList<SurahVerse> verses) {
        this.verses = verses;
    }
}
