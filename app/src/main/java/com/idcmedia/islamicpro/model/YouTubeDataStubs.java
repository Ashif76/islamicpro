package com.idcmedia.islamicpro.model;

import java.util.List;

public class YouTubeDataStubs {

    private List<YouTubeDataItems> items;
    private String nextPageToken;
    private String etag;
    private String regionCode;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<YouTubeDataItems> getItems() {
        return items;
    }

    public void setItems(List<YouTubeDataItems> items) {
        this.items = items;
    }
}
