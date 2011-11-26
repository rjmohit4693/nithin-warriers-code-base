package com.smartandroidians.fetchdeviceid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class FetchDeviceIDActivity extends Activity {
	
	private String android_id;
	private TextView text;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        text = (TextView) findViewById(R.id.text);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        android_id = telephonyManager.getDeviceId();
        //android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
        text.setText(android_id);
    }
}