package com.idcmedia.islamicpro.model;

public class KuranParahItem {
    private String english_name;
    private String arabic_name;

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getArabic_name() {
        return arabic_name;
    }

    public void setArabic_name(String arabic_name) {
        this.arabic_name = arabic_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
}
