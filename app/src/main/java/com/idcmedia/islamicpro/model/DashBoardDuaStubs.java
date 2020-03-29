package com.idcmedia.islamicpro.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashBoardDuaStubs {
    @SerializedName("content")
    private List<DashBoardDuaItem> dashBoardData;

    public List<DashBoardDuaItem> getDashBoardData() {
        return dashBoardData;
    }

    public void setDashBoardData(List<DashBoardDuaItem> dashBoardData) {
        this.dashBoardData = dashBoardData;
    }
}
