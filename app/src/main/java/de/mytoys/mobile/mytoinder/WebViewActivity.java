package de.mytoys.mobile.mytoinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by dgomez on 2/11/16.
 */

public class WebViewActivity extends AppCompatActivity {

    public static final String URL = "de.mytoys.mobile.mytoider.webviewactivity.URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String url = "";

        if (intent != null) {
            url = intent.getStringExtra(URL);
        }
        if (!TextUtils.isEmpty(url)) {
            myWebView.loadUrl(url);
        } else {
            myWebView.loadUrl("http://www.mytoys.de");
        }
    }

}
