package com.smartandroidians.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	
	private DBHelper mDBHelper;
	public SQLiteDatabase mDatabase;
	private static final String DATABASE_NAME = "MyDatabase";
	private static final int DATABASE_VERSION = 1;
	private static final String TAG = "DBAdapter";
	
	public DBAdapter(Context context) {
		mDBHelper = new DBHelper(context);//, DATABASE_NAME, null, DATABASE_VERSION);
		Log.i(TAG, "*************** DBAdapter constructor...");
	}
	
	private static class DBHelper extends SQLiteOpenHelper {
		
		public DBHelper(Context context) {//, String name, CursorFactory factory, int version) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			Log.i(TAG, "*************** DBHelper constructor...");
			//onCreate(mDatabase);
		}

		@Override
		public void onCreate(SQLiteDatabase db) throws SQLException {
			Log.i(TAG, "*************** DBHelper onCreate...");
			db.execSQL("create table if not exists MyTable (id integer primary key, title text);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersio, int newVersion) {
			Log.i(TAG, "*************** DBHelper onUpgrade()...");
		}
	}
	
	public void openDatabase() {
		Log.i(TAG, "*************** inside openDatabase()...");
		mDatabase = mDBHelper.getWritableDatabase(); 
	}
	
	public long insert(String title) {
		Log.i(TAG, "*************** inside insert()..."+title);
		try {
		ContentValues values = new ContentValues();
		values.put("title", title);
		return mDatabase.insertOrThrow("MyTable", null, values);
		} catch(Exception e) {
			return -1;
		}
	}
	
	public Cursor getAllTitles() {
		Log.i(TAG, "*************** inside getAllTitles()...");
		return mDatabase.query("MyTable", null, null, null, null, null, null);
	}
	
	public boolean delete(int rowId) {
		Log.i(TAG, "*************** inside delete()...");
		return mDatabase.delete("MyTable", "id="+rowId, null) > 0;
	}
	
	public boolean update(int rowId, String newTitle) {
		Log.i(TAG, "*************** inside update()...");
		ContentValues values = new ContentValues();
		values.put("title", newTitle);
		return mDatabase.update("MyTable", values, "id="+rowId, null) > 0;
	}
	
	public void close() {
		Log.i(TAG, "*************** inside update()...");
		mDBHelper.close();
	}

}
