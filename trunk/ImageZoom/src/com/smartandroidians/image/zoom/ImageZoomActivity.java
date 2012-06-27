package com.smartandroidians.image.zoom;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * App to zoom an image
 * @author Nithin
 *
 */
public class ImageZoomActivity extends Activity {
    
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        WebView webView = (WebView) findViewById(R.id.webview);
//        webView.loadUrl("file:///android_asset/spring.png");
        ZoomImageView zoomImgView = new ZoomImageView(this);
        zoomImgView.setImageResource(R.drawable.spring);
        setContentView(zoomImgView);
    }
}