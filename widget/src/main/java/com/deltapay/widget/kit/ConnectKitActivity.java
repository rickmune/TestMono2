package com.deltapay.widget.kit;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.deltapay.widget.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ConnectKitActivity extends AppCompatActivity {
  private ProgressBar mConnectLoader;
  private View mProgressContainer;


  private WebViewClient mWebViewClient = new WebViewClient() {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      return true;
    }

    @Override
    public void onPageFinished(WebView view, final String url) {
      mProgressContainer.setVisibility(View.GONE);
      mConnectLoader.setVisibility(View.GONE);

      // trigger LOADED event
      JSONObject data = new JSONObject();
      long unixTime = System.currentTimeMillis() / 1000L;
      try {
        data.put("timestamp", unixTime);
        ConnectEvent connectEvent = new ConnectEvent("OPENED", data);
        MonoWebInterface.getInstance().triggerEvent(connectEvent);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  };

  @Override
  protected void onCreate(@Nullable Bundle bundle) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    super.onCreate(bundle);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.main);
    this.setup();
  }

  private void setup() {
    WebView mWebView = this.findViewById(R.id.connect_web_view);
    mConnectLoader = this.findViewById(R.id.connect_loader);
    mProgressContainer = this.findViewById(R.id.progress_container);

    String url = getIntent().getStringExtra(Constants.KEY_URL);

    mWebView.getSettings().setJavaScriptEnabled(true);
    mWebView.getSettings().setLoadWithOverviewMode(true);
    mWebView.getSettings().setUseWideViewPort(true);

    mWebView.setWebViewClient(mWebViewClient);

    MonoWebInterface instance = MonoWebInterface.getInstance();
    instance.setActivity(this);

    mWebView.addJavascriptInterface(instance, "MonoClientInterface");
    mWebView.loadUrl(url);
  }
}