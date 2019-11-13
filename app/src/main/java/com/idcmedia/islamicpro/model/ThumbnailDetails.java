package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

public class ThumbnailDetails {
    @SerializedName("default")
    private ThumbnailUrlInfo defaultThumbnail;
    @SerializedName("medium")
    private ThumbnailUrlInfo mediumThumbnail;
    @SerializedName("high")
    private ThumbnailUrlInfo highThumbnail;

    public ThumbnailUrlInfo getDefaultThumbnail() {
        return defaultThumbnail;
    }

    public void setDefaultThumbnail(ThumbnailUrlInfo defaultThumbnail) {
        this.defaultThumbnail = defaultThumbnail;
    }

    public ThumbnailUrlInfo getMediumThumbnail() {
        return mediumThumbnail;
    }

    public void setMediumThumbnail(ThumbnailUrlInfo mediumThumbnail) {
        this.mediumThumbnail = mediumThumbnail;
    }

    public ThumbnailUrlInfo getHighThumbnail() {
        return highThumbnail;
    }

    public void setHighThumbnail(ThumbnailUrlInfo highThumbnail) {
        this.highThumbnail = highThumbnail;
    }
}
