package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

public class KuranSurahData {

    private int number;
    @SerializedName("name")
    private String surahNameInArabic;
    private int numberOfAyahs;
    private String englishName;
    private String englishNameTranslation;
    private String  revelationType;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSurahNameInArabic() {
        return surahNameInArabic;
    }

    public void setSurahNameInArabic(String surahNameInArabic) {
        this.surahNameInArabic = surahNameInArabic;
    }

    public int getNumberOfAyahs() {
        return numberOfAyahs;
    }

    public void setNumberOfAyahs(int numberOfAyahs) {
        this.numberOfAyahs = numberOfAyahs;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishNameTranslation() {
        return englishNameTranslation;
    }

    public void setEnglishNameTranslation(String englishNameTranslation) {
        this.englishNameTranslation = englishNameTranslation;
    }

    public String getRevelationType() {
        return revelationType;
    }

    public void setRevelationType(String revelationType) {
        this.revelationType = revelationType;
    }
}
