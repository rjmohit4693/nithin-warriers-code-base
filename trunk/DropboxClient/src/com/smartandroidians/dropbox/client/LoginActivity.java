package com.smartandroidians.dropbox.client;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.smartandroidians.dropbox.client.utils.Utils;

/**
 * Class to login to Dropbox. From this activity browser will be launched and
 * authentication of the app with Dropbox cloud happens. After authenticating
 * control returns to this activity
 * 
 * @author Smart Androidians
 * @Date Mar 29, 2013
 * 
 */

public class LoginActivity extends Activity {

	private static final String TAG = LoginActivity.class.getSimpleName();
	public static DropboxAPI<AndroidAuthSession> mApi;

	/**
	 * Activity Life cycle method. When activity is launched, onCreate() will be
	 * called initially
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// create auth session for dropbox
		AndroidAuthSession session = buildSession();
		mApi = new DropboxAPI<AndroidAuthSession>(session);
		mApi.getSession().startAuthentication(LoginActivity.this);
	}

	/**
	 * Activity Life cycle method. After onCreate(), onResume() will be called
	 */
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");
		AndroidAuthSession session = mApi.getSession();
		if (session.authenticationSuccessful()) {
			Log.i(TAG, "************* authenticationSuccessful().... FINISHING AUTHENTOCATION TO DROPBOX");
			
		} else {
			Log.e(TAG, "Authentication with Dropbox failed");
		}

	}

	/**
	 * Create a Dropbox session
	 * 
	 * @return Returns a Dropbox session
	 */
	private AndroidAuthSession buildSession() {
		Log.d(TAG, "buildSession()");
		AppKeyPair appKeyPair = new AppKeyPair(Utils.APP_KEY, Utils.APP_SECRET);
		AndroidAuthSession session;
		String[] stored = getKeys();
		if (stored != null) {
			Log.i(TAG, "in IF part, start a new session.... - APP_KEY = " + Utils.APP_KEY + " ACCESS_TYPE = "
					+ Utils.ACCESS_TYPE);
			AccessTokenPair accessToken = new AccessTokenPair(stored[0], stored[1]);
			session = new AndroidAuthSession(appKeyPair, Utils.ACCESS_TYPE, accessToken);
		} else {
			Log.i(TAG, "****** buildSession(), in ELSE part, start a new session.... - APP_KEY = " + Utils.APP_KEY
					+ " ACCESS_TYPE = " + Utils.ACCESS_TYPE);
			session = new AndroidAuthSession(appKeyPair, Utils.ACCESS_TYPE);
		}

		return session;
	}

	/**
	 * To get the key if it is already stored in sharedPreferences
	 * 
	 * @return Returns the AccessKey & AccessSecret respectively in the order
	 */
	private String[] getKeys() {
		Log.d(TAG, "getKeys()");
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
		String key = sp.getString(Utils.SP_KEY_ACCESS_KEY, null);
		String secret = sp.getString(Utils.SP_KEY_ACCESS_SECRET, null);
		if (key != null && secret != null) {
			String[] ret = new String[2];
			ret[0] = key;
			ret[1] = secret;
			return ret;
		} else {
			return null;
		}
	}

}
