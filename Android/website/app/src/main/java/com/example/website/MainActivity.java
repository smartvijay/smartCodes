package com.example.website;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.StatusBarManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout mySwipeRefreshLayout;
    WebView simpleWebView;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);

        //Status bar

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.purple_200));
        }

        simpleWebView = (WebView) findViewById(R.id.simpleWebView);
        simpleWebView.setWebViewClient(new MyWebViewClient());
        String url = "https://www.mindgate.in/";
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load a web page in a web view
        //https://saffronel.in/invoicer
        //web page load

        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        simpleWebView.reload();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(mySwipeRefreshLayout.isRefreshing()) {
                                    mySwipeRefreshLayout.setRefreshing(false);
                                }
                            }
                        }, 1000);
//                        simpleWebView.stopLoading();
                    }
                }
        );
        simpleWebView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                if (pd != null){
//                    progressDialog.dismiss();
                    pd.dismiss();
                }
            }

        });
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //back page website
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (simpleWebView.canGoBack()) {
                        simpleWebView.goBack();
                    } else {
                          onBackPressed();
//                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}