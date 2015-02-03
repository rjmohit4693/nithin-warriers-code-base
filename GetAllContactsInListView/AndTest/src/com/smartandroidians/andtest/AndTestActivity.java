package com.smartandroidians.andtest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts.Photo;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AndTestActivity extends Activity {

	private static final String TAG = AndTestActivity.class.getSimpleName();
	private ArrayList<AppContacts> contactsList = new ArrayList<AppContacts>();
	private ListView lvContactsListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_and_test);
		lvContactsListView = (ListView) findViewById(R.id.lv_contacts);
		
		fetchContacts();
	}
	
	private void fetchContacts() {
		ContentResolver resolver = getContentResolver();
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, null, null, null, null);
		for(phoneCursor.moveToFirst();!phoneCursor.isAfterLast();phoneCursor.moveToNext()) {
			String name = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.DISPLAY_NAME));
			String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER));
			String id = phoneCursor.getString(phoneCursor.getColumnIndex(Email._ID));
			String photoUri = "";
			photoUri = phoneCursor.getString(phoneCursor.getColumnIndex(Photo.PHOTO_URI));
			Cursor emailCursor = resolver.query(Email.CONTENT_URI, null, Email.CONTACT_ID+" = ?", new String[] { id }, null);
			Log.i(TAG, "email count: "+emailCursor.getCount());
			String email = "";
			if (emailCursor.moveToFirst()) {
				Log.i(TAG, "Email: "+emailCursor.getString(emailCursor.getColumnIndex(Email.ADDRESS)));
				email = emailCursor.getString(emailCursor.getColumnIndex(Email.ADDRESS));
			}
			
			emailCursor.close();
			Log.i(TAG, "name: "+name+", phoneNumber: "+phoneNumber+", photoUri: "+photoUri);
			AppContacts contacts = new AppContacts(name, phoneNumber, email, photoUri);
			contactsList.add(contacts);
		}
		
		phoneCursor.close();
		lvContactsListView.setAdapter(new ContactsListAdapter(this, contactsList));
		lvContactsListView.setOnItemClickListener(listItemClick);
	}
	
	private AdapterView.OnItemClickListener listItemClick = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			RelativeLayout child = (RelativeLayout) parent.getChildAt(position);
			TextView tvPhoneNumber = (TextView)child.findViewById(R.id.tv_phone_number);
			Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tvPhoneNumber.getText().toString()));
			startActivity(i);
			Log.i(TAG, "child count: "+child.getChildCount());
		}
		
	};
	
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
