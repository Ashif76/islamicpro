package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

public class YouTubeDataItems {
    private String kind;
    private String etag;
    @SerializedName("id")
    private VideoIdInfo videoInfo;
    @SerializedName("snippet")
    private YouTubeSnippet videoDetails;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public VideoIdInfo getVideoIdInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoIdInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public YouTubeSnippet getVideoDetails() {
        return videoDetails;
    }

    public void setVideoDetails(YouTubeSnippet videoDetails) {
        this.videoDetails = videoDetails;
    }
}
