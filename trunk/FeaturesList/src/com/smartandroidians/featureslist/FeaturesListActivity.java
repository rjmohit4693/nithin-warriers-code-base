package com.smartandroidians.featureslist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FeaturesListActivity extends Activity {

	private final String TAG = "FeaturesListActivity";
	private ListView mList;
	private ArrayList<String> systemFeaturesList = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mList = (ListView) findViewById(R.id.list);
		PackageManager mPackageManager = getPackageManager();
		FeatureInfo[] systemFeature = mPackageManager
				.getSystemAvailableFeatures();
		for (int i = 0; i < systemFeature.length; i++) {
			String feature = systemFeature[i].name;
			if (feature == null) {
				feature = "OpenGL Version : " + systemFeature[i].reqGlEsVersion;
			}
			systemFeaturesList.add(feature);
			Log.i(TAG, "**************** System Feature = " + feature);
		}
		mList.setAdapter(new SystemFeatureListAdapter(this));
	}

	private class SystemFeatureListAdapter extends BaseAdapter {

		private Context mContext;

		public SystemFeatureListAdapter(Context context) {
			mContext = context;
		}

		@Override
		public int getCount() {
			return systemFeaturesList.size();
		}

		@Override
		public Object getItem(int index) {
			return systemFeaturesList.get(index);
		}

		@Override
		public long getItemId(int id) {
			return id;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			TextView text = new TextView(mContext);
			text.setText(systemFeaturesList.get(position));
			return text;
		}
	}
}