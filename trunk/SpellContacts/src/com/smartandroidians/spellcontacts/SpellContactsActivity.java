package com.smartandroidians.spellcontacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.smartandroidians.spellcontacts.receiver.GetIncomingNumberReceiver;

public class SpellContactsActivity extends Activity {
	
	/** 
	 * Get the phone number of incoming call, search the mobile number for the appropiate name in
	 * contacts content provider, if name is present , spell it else spell unknown number
	 * 
	 */
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent broadcastIntent = new Intent(this, GetIncomingNumberReceiver.class);
        sendBroadcast(broadcastIntent);
    }
}