package de.mytoys.mobile.mytoinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

/**
 * Created by dgomez on 2/11/16.
 */

public class WebViewActivity extends AppCompatActivity {

    public static final String URL = "de.mytoys.mobile.mytoider.webviewactivity.URL";

    private CircularProgressView progressBar;

    private LinearLayout progressBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        progressBarLayout = (LinearLayout) findViewById(R.id.main_progressbar_layout);
        progressBar = (CircularProgressView) findViewById(R.id.main_progressbar);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.stopAnimation();
                progressBarLayout.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }
        });

        Intent intent = getIntent();
        String url = "";

        if (intent != null) {
            url = intent.getStringExtra(URL);
        }
        progressBarLayout.setVisibility(View.VISIBLE);
        //progressBar.setColor(R.color.colorPrimaryDark);
        progressBar.startAnimation();
        if (!TextUtils.isEmpty(url)) {
            Log.d("TAG", "URL:" + url);
            myWebView.loadUrl(url);
        } else {
            myWebView.loadUrl("http://www.mytoys.de");
        }
    }

}
