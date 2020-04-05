package com.idcmedia.islamicpro;

import android.os.Bundle;
import android.webkit.WebView;

import com.github.barteksc.pdfviewer.PDFView;

import androidx.appcompat.app.AppCompatActivity;

public class KuranViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zonzapata);
        PDFView pdfView = findViewById(R.id.pdfView);

    }
}
