package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

public class AdhanStubs {
    @SerializedName("arabic_text")
    private String textArabic;
    @SerializedName("english_explanation")
    private String englishExplanation;
    @SerializedName("english_transaltion")
    private String englishTranslation;

    public String getTextArabic() {
        return textArabic;
    }

    public void setTextArabic(String textArabic) {
        this.textArabic = textArabic;
    }

    public String getEnglishExplanation() {
        return englishExplanation;
    }

    public void setEnglishExplanation(String englishExplanation) {
        this.englishExplanation = englishExplanation;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public void setEnglishTranslation(String englishTranslation) {
        this.englishTranslation = englishTranslation;
    }
}
