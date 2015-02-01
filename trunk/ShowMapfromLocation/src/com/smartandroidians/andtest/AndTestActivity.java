package com.smartandroidians.andtest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class AndTestActivity extends Activity {

	private static final String TAG = AndTestActivity.class.getSimpleName();
	private static final int PICK_CONTACT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_and_test);
		Log.i(TAG, "onCreate()");
		showMapFromLocation("Cochin", "Bangalore");
	}

	private boolean isOnline() {
		boolean isOnline = false;
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo.isConnected() || networkInfo.isConnectedOrConnecting()) {
			isOnline = true;
		}
		return isOnline;
	}

	private void showMapFromLocation(String src, String dest) {
		double srcLat = 0, srcLng = 0, destLat = 0, destLng = 0;
		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		try {
			if (isOnline()) {
				List<Address> srcAddresses = geocoder.getFromLocationName(src,
						1);
				if (srcAddresses.size() > 0) {
					Address location = srcAddresses.get(0);
					srcLat = location.getLatitude();
					srcLng = location.getLongitude();
				}

				List<Address> destAddresses = geocoder.getFromLocationName(
						dest, 1);
				if (destAddresses.size() > 0) {
					Address location = destAddresses.get(0);
					destLat = location.getLatitude();
					destLng = location.getLongitude();
				}

				String desLocation = "&daddr=" + Double.toString(destLat) + ","
						+ Double.toString(destLng);
				String currLocation = "saddr=" + Double.toString(srcLat) + ","
						+ Double.toString(srcLng);

				// "d" means driving car, "w" means walking "r" means by bus
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("http://maps.google.com/maps?" + currLocation
								+ desLocation + "&dirflg=d"));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						& Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
				intent.setClassName("com.google.android.apps.maps",
						"com.google.android.maps.MapsActivity");

				startActivity(intent);
			}
		} catch (IOException e) {
			Log.e(TAG, "Error when showing google map directions, E: " + e);
		} catch (Exception e) {
			Log.e(TAG, "Error when showing google map directions, E: " + e);
		}
	}

}
