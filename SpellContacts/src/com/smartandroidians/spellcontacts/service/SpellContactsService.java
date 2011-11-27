package com.smartandroidians.spellcontacts.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.ContactsContract.PhoneLookup;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.widget.Toast;

import com.smartandroidians.spellcontacts.receiver.GetIncomingNumberReceiver;

public class SpellContactsService extends Service {
	
	private final String TAG = "SpellContactsService";
	private String contactName = null;

	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "************************************* inside onCreate() of the service....");
		contactName = getContactsDisplayName();
		if (contactName == null) {
			contactName = "Unknown Number";
		}
		Toast.makeText(this, contactName, Toast.LENGTH_LONG).show();
		//speakContactName();
	}
	
	private String getContactsDisplayName() {
		String getContactName = null;
		ContentResolver contactsResolver = getContentResolver();
		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(GetIncomingNumberReceiver.INCOMING_NUMBER));
		Cursor contactsLookupCursor = contactsResolver.query(uri, new String[] {PhoneLookup.DISPLAY_NAME}, null, null, null);
		Log.i(TAG, "********************************** getCount() = "+contactsLookupCursor.getCount());
		while (contactsLookupCursor.moveToNext()) {
			getContactName = contactsLookupCursor.getString(contactsLookupCursor.getColumnIndexOrThrow(PhoneLookup.DISPLAY_NAME));
			Log.i(TAG, "************************************* contactsName = "+contactName);
		}
		contactsLookupCursor.close();
		contactsLookupCursor = null;
		return getContactName;
	}
	
	private void speakContactName() {
		new SpeakContactsName(this);
	}
	
	private class SpeakContactsName implements OnInitListener {
		
		private TextToSpeech speechEngine;
		
		public SpeakContactsName(Context context) {
			Log.i(TAG, "******************** SpeakContactsName Constructor..");
			speechEngine = new TextToSpeech(context, this);
		}	
		
		private void speak(String name) {
			speechEngine.speak(name, TextToSpeech.QUEUE_FLUSH, null);
			release();
		}
		
		private void release() {
			if (speechEngine != null) {
				speechEngine.stop();
				speechEngine.shutdown();
			}
		}

		@Override
		public void onInit(int status) {
			Log.i(TAG, "*************************** onInit().... ");
			if (contactName == null) {
				contactName = "Unknown Number";
			}
			speak(contactName);
		}
		
	}
	
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
