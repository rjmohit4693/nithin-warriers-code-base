package com.smartandroidians.thumbnailwallpaper;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

public class ThumbnailSetWallpaper extends Activity {
	
	private final String TAG = "ThumbnailSetWallpaper";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Cursor externalImages = this.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
    	Cursor internalImages = this.managedQuery(MediaStore.Images.Media.INTERNAL_CONTENT_URI, null, null, null, null);
    	int externalImagesRows = externalImages.getCount();
    	int internalImagesRows = internalImages.getCount();
    	Log.i(TAG, "************************************ externalImagesRows = "+externalImagesRows);
    	Log.i(TAG, "************************************ internalImagesRows = "+internalImagesRows);
    	

    }
}