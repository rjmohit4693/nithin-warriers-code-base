package com.smartandroidians.fetchcontacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;

public class FetchContacts extends Activity {

    private Cursor cursor;
    final String TAG = "FetchContacts";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_contacts);
        fetchContacts();
    }
    
	
    private void fetchContacts() {
    	ContentResolver contactsResolver = getContentResolver();
    	/** from ContactsContract.Contacts.CONTENT_URI, we will get contact_id & display name, to get phone number
    	 and other details it will be in a separate table, so we have to query that. */ 
    	cursor = contactsResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    	for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
    		String id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
    		String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
    		Log.i(TAG, "******************** contacts_id = "+id+" display_name = "+name);
    		if (Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER)))>0) {
    			Cursor phone_cursor = contactsResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
    								null, null, null, null);
    			for (phone_cursor.moveToFirst(); !phone_cursor.isAfterLast(); phone_cursor.moveToNext()) {
    				String phone_number = phone_cursor.getString(
    						phone_cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
    				Log.i(TAG, "************ Phone number = "+phone_number);
    				//Like this we can get all details about contact like email, notes etc. by querying different tables
    			}
    		}
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_fetch_contacts, menu);
        return true;
    }
    
}
