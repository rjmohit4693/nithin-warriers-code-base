package com.smartandroidians.andtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Contacts.Photo;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AndTestActivity extends Activity {

	private static final String TAG = AndTestActivity.class.getSimpleName();
	private ArrayList<AppContacts> contactsList = new ArrayList<AppContacts>();
	private ListView lvContactsListView;
	private ProgressBar pbGettingContacts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_and_test);
		lvContactsListView = (ListView) findViewById(R.id.lv_contacts);
		pbGettingContacts = (ProgressBar) findViewById(R.id.pb_getting_contacts);

		// fetchContacts();
		new FetchContacts().execute();
	}

	private class FetchContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			fetchContacts();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			pbGettingContacts.setVisibility(View.GONE);

			lvContactsListView.setAdapter(new ContactsListAdapter(AndTestActivity.this, contactsList));
			lvContactsListView.setOnItemClickListener(listItemClick);
		}

	}

	private void fetchContacts() {
		ContentResolver resolver = getContentResolver();
		// Cursor contactsCursor = resolver.query(Contacts.CONTENT_URI, null,
		// null, null, null);
		Cursor contactsCursor = resolver.query(Contacts.CONTENT_URI, null, null, null, Contacts._ID
				+ " DESC LIMIT 10,10");
		for (contactsCursor.moveToFirst(); !contactsCursor.isAfterLast(); contactsCursor.moveToNext()) {

			String id = contactsCursor.getString(contactsCursor.getColumnIndex(Contacts._ID));
			// to get phone number of respective contact
			Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + " = ?",
					new String[] { id }, null);
			String phoneNumber = "";
			if (phoneCursor.moveToFirst()) {
				phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER));
			}
			phoneCursor.close();
			String name = contactsCursor.getString(contactsCursor.getColumnIndex(Contacts.DISPLAY_NAME));
			String photoUri = "";
			photoUri = contactsCursor.getString(contactsCursor.getColumnIndex(Photo.PHOTO_URI));
			// to get email id of respective contact
			Cursor emailCursor = resolver.query(Email.CONTENT_URI, null, Email.CONTACT_ID + " = ?",
					new String[] { id }, null);
			String email = "";
			if (emailCursor.moveToFirst()) {
				email = emailCursor.getString(emailCursor.getColumnIndex(Email.ADDRESS));
			}
			emailCursor.close();
			Log.i(TAG, "name: " + name + ", phoneNumber: " + phoneNumber + ", photoUri: " + photoUri);
			AppContacts contacts = new AppContacts(name, phoneNumber, email, photoUri);
			contactsList.add(contacts);
		}

		contactsCursor.close();
		Collections.sort(contactsList, new Comparator<AppContacts>() {

			@Override
			public int compare(AppContacts lhs, AppContacts rhs) {
				return lhs.getName().compareToIgnoreCase(rhs.getName());
			}
		});

	}

	private AdapterView.OnItemClickListener listItemClick = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			RelativeLayout child = (RelativeLayout) parent.getChildAt(position);
			TextView tvPhoneNumber = (TextView) child.findViewById(R.id.tv_phone_number);
			if (tvPhoneNumber.getText().toString() == null || tvPhoneNumber.getText().toString().isEmpty()) {
				Toast.makeText(AndTestActivity.this, "NO phone number to call", Toast.LENGTH_SHORT).show();
			} else {
				Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tvPhoneNumber.getText().toString()));
				startActivity(i);
			}
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
