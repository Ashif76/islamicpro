package com.idcmedia.islamicpro;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import androidx.appcompat.app.AppCompatActivity;

public class PdfViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zonzapata);
        PDFView pdfView = findViewById(R.id.pdfView);


        pdfView.fromAsset("parah_21.pdf")
//                .pages(0, 2, 1, 3, 3, 3)
                .defaultPage(5)
//                .showMinimap(false)
                .enableSwipe(true)
//                .onDraw(new OnDrawListener() {
//                    @Override
//                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//                        Toast.makeText(PdfViewActivity.this,"Drwaing something",Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .onLoad(new OnLoadCompleteListener() {
//                    @Override
//                    public void loadComplete(int nbPages) {
//                        Toast.makeText(PdfViewActivity.this,"Load completed",Toast.LENGTH_SHORT).show();
//
//                    }
//                })
//                .onPageChange(new OnPageChangeListener() {
//                    @Override
//                    public void onPageChanged(int page, int pageCount) {
//                        Toast.makeText(PdfViewActivity.this,"page changed",Toast.LENGTH_SHORT).show();
//
//                    }
//                })
                .load();

//         webView = findViewById(R.id.web_view);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url){
//                // do your handling codes here, which url is the requested url
//                // probably you need to open that url rather than redirect:
//                view.loadUrl("https://www.youtube.com/channel/UC-ibpI7MJyqVSj-1LEJI6-Q");
//                return false; // then it is not handled by default action
//            }
//        });

    }
}
