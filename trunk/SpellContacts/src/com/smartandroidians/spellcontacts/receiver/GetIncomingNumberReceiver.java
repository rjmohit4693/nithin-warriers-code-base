package com.smartandroidians.spellcontacts.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.smartandroidians.spellcontacts.service.SpellContactsService;

public class GetIncomingNumberReceiver extends BroadcastReceiver {
	
	private final String TAG = "GetIncomingNumberReceiver";
	public static String INCOMING_NUMBER = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Log.i(TAG, "********************************************* inside onReceive()");
		// If bundle doesn't have any incoming data, nothing to do... 
		if (bundle == null) {
			return;
		} 
		Log.i(TAG, "****************************************** Bundle is not null");
		String incomingState = bundle.getString(TelephonyManager.EXTRA_STATE);
		if(incomingState.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
			INCOMING_NUMBER = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
			Log.i(TAG, "**************************************** INCOMING_NUMBER = "+INCOMING_NUMBER);
		}
		
		// start a service for speaking the contacts
		Intent service = new Intent(context, SpellContactsService.class);
		context.startService(service);
	}

}
