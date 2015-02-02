package com.smartandroidians.andtest;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AndTestActivity extends Activity {

	private static final String TAG = AndTestActivity.class.getSimpleName();
	private static final int PICK_CONTACT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_and_test);
		Log.i(TAG, "onCreate()");
		((Button) findViewById(R.id.btn_openContacts)).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
				i.setType(Phone.CONTENT_TYPE);
				startActivityForResult(i, PICK_CONTACT);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i(TAG, "onActivityResult()");
		if (requestCode == PICK_CONTACT) {
			if (resultCode == Activity.RESULT_OK) {
				Uri contactsData = data.getData();
				CursorLoader loader = new CursorLoader(this, contactsData, null, null, null, null);
				Cursor c = loader.loadInBackground();

				if (c.moveToFirst()) {
					Log.i(TAG, "Contacts ID: " + c.getString(c.getColumnIndex(Contacts._ID)));
					Log.i(TAG, "Contacts Name: " + c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME)));
					Log.i(TAG, "Contacts Phone Number: " + c.getString(c.getColumnIndex(Phone.NUMBER)));
					Log.i(TAG, "Contacts Photo Uri: " + c.getString(c.getColumnIndex(Photo.PHOTO_URI)));
				}
			}
		}
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

}
