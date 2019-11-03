package com.idcmedia.islamicpro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import androidx.annotation.RequiresApi;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PdfRenderActivity extends AppCompatActivity {

    @BindView(R.id.pdf_image)
    PhotoView imageViewPdf;
    @BindView(R.id.button_pre_doc) FloatingActionButton prePageButton;
    @BindView(R.id.button_next_doc) FloatingActionButton nextPageButton;

    private String mFileName;

    private int pageIndex;
    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPage;
    private ParcelFileDescriptor parcelFileDescriptor;
    private TextView tvTitle;
    private AdView adView ;
    private RewardedVideoAd mRewardedVideoAd;
    public static final String FILE_NAME = "file_name" ;
    public  static final String CLASS_TYPE = "class_type";
    private InterstitialAd mInterstitialAd;
    Timer timerVideoAdd;
    Timer fullScreenTimes;
    private String classType;
    int currentValueToShowAdd ;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_render);
        mFileName = getIntent().getStringExtra(FILE_NAME);
        classType = getIntent().getStringExtra(CLASS_TYPE);
        if(classType !=null){
            currentValueToShowAdd = Utils.getSharedPref(this, classType);
            getSupportActionBar().setTitle(classType);
        }
        MobileAds.initialize(this, getResources().getString(R.string.app_add_id));
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.page_intersial_add_id));


        adView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        tvTitle = findViewById(R.id.title);
        pageIndex = 0;
        try {
            openRenderer(getApplicationContext());
            showPage(pageIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        loadRewardedVideoAd();
        videoAddListener();
        smallAddListener();
        fullScreenListener();
        System.currentTimeMillis();
        if(currentValueToShowAdd ==1) {
            Utils.loadRewardedVideoAd(mRewardedVideoAd,this);
        }
//         timerVideoAdd = Utils.setTimeForRewardedAdd(this, mRewardedVideoAd);
        Utils.loadAdd(adView);
        Utils.setTimeForAdd(this,adView);
//        Utils.loadInterstelFullScreenAdd(mInterstitialAd);
         fullScreenTimes = Utils.setTimeForInterstelFullScreenAdd(this, mInterstitialAd);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (timerVideoAdd!=null){
            timerVideoAdd.cancel();
        }
        if(fullScreenTimes!=null){
            fullScreenTimes.cancel();
        }
    }

    private void fullScreenListener() {

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
//                Toast.makeText(PdfRenderActivity.this, "add loaded", Toast.LENGTH_SHORT).show();
                Utils.showFullScreen(mInterstitialAd);
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
//                Toast.makeText(PdfRenderActivity.this, "add failed"+errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
//                Toast.makeText(PdfRenderActivity.this, "add opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
//                Toast.makeText(PdfRenderActivity.this, "adds loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
//                Toast.makeText(PdfRenderActivity.this, "adds left app", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
//                Toast.makeText(PdfRenderActivity.this, "adds closed", Toast.LENGTH_SHORT).show();
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    private void smallAddListener(){
         adView.setAdListener(new AdListener() {
             @Override
             public void onAdLoaded() {
//                 Toast.makeText(PdfRenderActivity.this, "add loaded", Toast.LENGTH_SHORT).show();
                 // Code to be executed when an ad finishes loading.
             }

             @Override
             public void onAdFailedToLoad(int errorCode) {
                 // Code to be executed when an ad request fails.
//                 Toast.makeText(PdfRenderActivity.this, "add failed"+errorCode, Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onAdOpened() {
                 // Code to be executed when an ad opens an overlay that
                 // covers the screen.
//                 Toast.makeText(PdfRenderActivity.this, "add opened", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onAdClicked() {
                 // Code to be executed when the user clicks on an ad.
//                 Toast.makeText(PdfRenderActivity.this, "adds loaded", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onAdLeftApplication() {
                 // Code to be executed when the user has left the app.
//                 Toast.makeText(PdfRenderActivity.this, "adds left app", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onAdClosed() {
//                 Toast.makeText(PdfRenderActivity.this, "adds closed", Toast.LENGTH_SHORT).show();
                 // Code to be executed when the user is about to return
                 // to the app after tapping on an ad.
             }
         });
     }


     private void videoAddListener(){
         mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
             @Override
             public void onRewardedVideoAdLoaded() {
//                 Toast.makeText(PdfRenderActivity.this, "add loaded", Toast.LENGTH_SHORT).show();
                 Utils.showVideo(mRewardedVideoAd);
             }

             @Override
             public void onRewardedVideoAdOpened() {

             }

             @Override
             public void onRewardedVideoStarted() {

             }

             @Override
             public void onRewardedVideoAdClosed() {
//                loadRewardedVideoAd();
             }

             @Override
             public void onRewarded(RewardItem rewardItem) {

             }

             @Override
             public void onRewardedVideoAdLeftApplication() {

             }

             @Override
             public void onRewardedVideoAdFailedToLoad(int i) {
//                 Toast.makeText(PdfRenderActivity.this, "add failed rewarded"+i, Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onRewardedVideoCompleted() {

             }
         });
     }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onStart() {
        super.onStart();
//        try {
//            openRenderer(getApplicationContext());
//            showPage(pageIndex);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onStop() {
//        try {
//            closeRenderer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.button_pre_doc)
    public void onPreviousDocClick(){
        showPage(currentPage.getIndex() - 1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.button_next_doc)
    public void onNextDocClick(){
        showPage(currentPage.getIndex() + 1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        if((currentPage.getIndex()+1) == ( pdfRenderer.getPageCount()/2) ){
            Utils.loadInterstelFullScreenAdd(mInterstitialAd);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openRenderer(Context context) throws IOException {
        // In this sample, we read a PDF from the assets directory.
        File file = new File(context.getCacheDir(), mFileName);
        if (!file.exists()) {
            // Since PdfRenderer cannot handle the compressed asset file directly, we copy it into
            // the cache directory.
            InputStream asset = context.getAssets().open(mFileName);
            FileOutputStream output = new FileOutputStream(file);
            final byte[] buffer = new byte[1024];
            int size;
            while ((size = asset.read(buffer)) != -1) {
                output.write(buffer, 0, size);
            }
            asset.close();
            output.close();
        }
        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        // This is the PdfRenderer we use to render the PDF.
        if (parcelFileDescriptor != null) {
            pdfRenderer = new PdfRenderer(parcelFileDescriptor);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void closeRenderer() throws IOException {
        if (null != currentPage) {
            currentPage.close();
//            currentPage = null;
        }
        pdfRenderer.close();
        parcelFileDescriptor.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showPage(int index) {
        int pageCount = pdfRenderer.getPageCount();
        if ( pageCount<= index) {
            return;
        }



        // Make sure to close the current page before opening another one.
        if (null != currentPage) {
            currentPage.close();
        }
        // Use `openPage` to open a specific page in PDF.
        currentPage = pdfRenderer.openPage(index);
        // Important: the destination bitmap must be ARGB (not RGB).
        Bitmap bitmap = Bitmap.createBitmap(currentPage.getWidth(), currentPage.getHeight(),
                Bitmap.Config.ARGB_8888);
        // Here, we render the page onto the Bitmap.
        // To render a portion of the page, use the second and third parameter. Pass nulls to get
        // the default result.
        // Pass either RENDER_MODE_FOR_DISPLAY or RENDER_MODE_FOR_PRINT for the last parameter.
        currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        // We are ready to show the Bitmap to user.
        imageViewPdf.setImageBitmap(bitmap);
        updateUi();
    }

    /**
     * Updates the state of 2 control buttons in response to the current page index.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateUi() {
        int index = currentPage.getIndex();
        int pageCount = pdfRenderer.getPageCount();
        prePageButton.setEnabled(0 != index);
        nextPageButton.setEnabled(index + 1 < pageCount);
        tvTitle.setText("page "+(index+1)+"/"+pageCount);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int getPageCount() {
        return pdfRenderer.getPageCount();
    }

    @Override
    protected void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();

    }
}