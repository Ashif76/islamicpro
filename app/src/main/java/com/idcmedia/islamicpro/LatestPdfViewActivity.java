package com.idcmedia.islamicpro;

import android.graphics.PointF;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import androidx.appcompat.app.AppCompatActivity;

public class LatestPdfViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latest_pdf_activity);
        PDFView pdfView = findViewById(R.id.pdfView);


//        pdfView.fromAsset("parah_21.pdf")
////                .pages(0, 2, 1, 3, 3, 3)
//                .defaultPage(5)
////                .showMinimap(false)
//                .enableSwipe(true)
////                .onDraw(new OnDrawListener() {
////                    @Override
////                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
////                        Toast.makeText(PdfViewActivity.this,"Drwaing something",Toast.LENGTH_SHORT).show();
////                    }
////                })
////                .onLoad(new OnLoadCompleteListener() {
////                    @Override
////                    public void loadComplete(int nbPages) {
////                        Toast.makeText(PdfViewActivity.this,"Load completed",Toast.LENGTH_SHORT).show();
////
////                    }
////                })
////                .onPageChange(new OnPageChangeListener() {
////                    @Override
////                    public void onPageChanged(int page, int pageCount) {
////                        Toast.makeText(PdfViewActivity.this,"page changed",Toast.LENGTH_SHORT).show();
////
////                    }
////                })
//                .load();
//
////         webView = findViewById(R.id.web_view);
////        webView.getSettings().setJavaScriptEnabled(true);
////        webView.setWebViewClient(new WebViewClient() {
////            public boolean shouldOverrideUrlLoading(WebView view, String url){
////                // do your handling codes here, which url is the requested url
////                // probably you need to open that url rather than redirect:
////                view.loadUrl("https://www.youtube.com/channel/UC-ibpI7MJyqVSj-1LEJI6-Q");
////                return false; // then it is not handled by default action
////            }
////        });

        loadSetting(pdfView);

    }

    private void loadSetting(PDFView pdfView) {
//        pdfView.moveRelativeTo(20,20);
        pdfView.fromAsset("parah_21_cropped.pdf")
//                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(0)
                // allows to draw something on the current page, usually visible in the middle of the screen
//                .onDraw(onDrawListener)
//                // allows to draw something on all pages, separately for every page. Called only for visible pages
//                .onDrawAll(onDrawListener)
//                .onLoad(new OnLoadCompleteListener() {
//                    @Override
//                    public void loadComplete(int nbPages) {
//                        PointF pf = new PointF();
//                        pf.x = 0.5f;
//                        pf.y = 0.5f;
//                        pdfView.zoomCenteredRelativeTo(1.5f,pf );
//                        Toast.makeText(LatestPdfViewActivity.this,"Load completed",Toast.LENGTH_SHORT).show();
//                    }
//                }) // called after document is loaded and starts to be rendered
//                .onPageChange(onPageChangeListener)
//                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
//                .onPageError(onPageErrorListener)
//                .onRender(new OnRenderListener() {
//                    @Override
//                    public void onInitiallyRendered(int nbPages) {
//                        pdfView.fitToWidth(nbPages); // optionally pass page number
//                        Toast.makeText(LatestPdfViewActivity.this,"Load completed",Toast.LENGTH_SHORT).show();
//                    }
//                }) // called after document is rendered for the first time
//                // called on single tap, return true if handled, false to toggle scroll handle visibility
//                .onTap(onTapListener)
//                .onLongPress(onLongPressListener)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
//                .spacing(-3)
                .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
//                .linkHandler(DefaultLinkHandler)
                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                .fitEachPage(true)
                // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(true) // snap pages to screen boundaries
                .pageFling(true)
                // make a fling change only a single page like ViewPager
                .nightMode(false)
                // toggle night mode
                .load();


//        pdfView.moveRelativeTo(20,20);
    }


}