package com.smartandroidians.dropbox.client;

import android.app.Activity;
import android.os.Bundle;

/**
 * Class to list all Dropbox resources in a list
 * 
 * @author Smart Androidians
 * @Date Apr 3, 2013
 * 
 * 
 */
public class ListDropboxActivity extends Activity {

	private static final String TAG = ListDropboxActivity.class.getSimpleName();

	/**
	 * Activity Life cycle method. onCreate() will be called initially when
	 * Activity is launched
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getAccountInfo();
		getAllDropboxResourcesData();
	}

	private void getAccountInfo() {

	}

	private void getAllDropboxResourcesData() {
		
	}
}
