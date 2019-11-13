package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

public class SurahVerse {
    @SerializedName("number")
    private int verseNumber;
    @SerializedName("text")
    private String verseArabicText;
    @SerializedName("translation_en")
    private String englishTranslation;

    public int getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(int verseNumber) {
        this.verseNumber = verseNumber;
    }

    public String getVerseArabicText() {
        return verseArabicText;
    }

    public void setVerseArabicText(String verseArabicText) {
        this.verseArabicText = verseArabicText;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public void setEnglishTranslation(String englishTranslation) {
        this.englishTranslation = englishTranslation;
    }
}
