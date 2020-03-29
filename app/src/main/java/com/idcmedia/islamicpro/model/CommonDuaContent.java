package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

public class CommonDuaContent {
    private String heading;
    @SerializedName("arabic_text")
    private String arabicText;
    private String pronounciation;
    @SerializedName("english_transaltion")
    private String englishExplanation;
    private String source;
    private int duaTypeId;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDuaTypeId() {
        return duaTypeId;
    }

    public void setDuaTypeId(int duaTypeId) {
        this.duaTypeId = duaTypeId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getArabicText() {
        return arabicText;
    }

    public void setArabicText(String arabicText) {
        this.arabicText = arabicText;
    }

    public String getPronounciation() {
        return pronounciation;
    }

    public void setPronounciation(String pronounciation) {
        this.pronounciation = pronounciation;
    }

    public String getEnglishExplanation() {
        return englishExplanation;
    }

    public void setEnglishExplanation(String englishExplanation) {
        this.englishExplanation = englishExplanation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
