package com.idcmedia.islamicpro.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.idcmedia.islamicpro.R;
import com.idcmedia.islamicpro.Utils;

public class YouVideoPlayActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
YouTubePlayerView youTubePlayerView;
String API_KEY= Utils.YOUTUBE_API_KEY;
    private static final int RECOVERY_REQUEST = 1;
    String TAG="YouVideoPlayActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
       youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtubeview);
       youTubePlayerView.initialize(API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        Bundle bundle = getIntent().getExtras();
        String showVideo = bundle.getString("videoId");
        Log.e(TAG,"Video" +showVideo);
        youTubePlayer.cueVideo(showVideo);
//        youTubePlayer.setFullscreen(false);
        youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
            @Override
            public void onFullscreen(boolean b) {

            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {

            Toast.makeText(this, "Error Intializing Youtube Player", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            getYouTubePlayerProvider().initialize(API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubePlayerView;
    }

}