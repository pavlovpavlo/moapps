package com.pavlov.moappstest.screen.applicationwebview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.pavlov.moappstest.R;

public class ApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        getSupportActionBar().hide();
        WebView webView = (WebView) findViewById (R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(getIntent().getStringExtra("appUrl"));
    }
}
