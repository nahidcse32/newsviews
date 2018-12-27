package com.example.nahid.newsviews.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.nahid.newsviews.R;
import com.example.nahid.newsviews.Utils.StaticValue;

public class WebViewActivity extends AppCompatActivity {

    private String url = "";
    private String type = "";
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        url = getIntent().getExtras().getString(StaticValue.WEB_URL);

        type = getIntent().getExtras().getString("option");
        webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setAllowFileAccess(true);

        if (type.equals("1")) {
            CustomWebViewClient customWebViewClient = new CustomWebViewClient();
            webView.setWebViewClient(customWebViewClient);
        } else {
            webView.loadUrl(url);
        }


    }

    private class CustomWebViewClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }


}
