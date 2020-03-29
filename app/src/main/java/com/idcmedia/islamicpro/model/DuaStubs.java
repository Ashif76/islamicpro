package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

public class DuaStubs {
    @SerializedName("arabic_text")
    private String textArabic;
    @SerializedName("english_transaltion")
    private String textExplanation;
    @SerializedName("Verse")
    private String verse;
    @SerializedName("Recite")
    private String recite;

    public String getRecite() {
        return recite;
    }

    public void setRecite(String recite) {
        this.recite = recite;
    }

    public String getTextArabic() {
        return textArabic;
    }

    public void setTextArabic(String textArabic) {
        this.textArabic = textArabic;
    }

    public String getTextExplanation() {
        return textExplanation;
    }

    public void setTextExplanation(String textExplanation) {
        this.textExplanation = textExplanation;
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }
}
