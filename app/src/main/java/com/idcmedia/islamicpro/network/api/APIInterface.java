package com.idcmedia.islamicpro.network.api;


import com.idcmedia.islamicpro.model.YouTubeDataStubs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("search")
    Call<YouTubeDataStubs> getChannelData(@Query("part") String part, @Query("pageToken") String pageToken, @Query("channelId") String channelId,
                                          @Query("maxResult") int category, @Query("key") String apiKey);


}

