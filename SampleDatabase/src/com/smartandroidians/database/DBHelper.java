package com.smartandroidians.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	
	public static final String DATABASE_NAME = "IzmirBusRouteData";
	public static final int DATABASE_VERSION = 1;
	private String CREATE_LINE_TABLE;
	private String CREATE_ROUTE_TABLE;
	private final String TAG = "DBHelper";
	SQLiteDatabase mDatabase;
	
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		Log.i(TAG, "************* constructor");
		mDatabase = getWritableDatabase();
		onCreate(mDatabase);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "**************** inside onCreate()");
		db.execSQL("create table LineTable (_id integer primary key, title text);");
		db.execSQL("insert into LineTable values (1, 'title-1')");
		db.execSQL("insert into LineTable values (2, 'title-2')");
		db.execSQL("insert into LineTable values (3, 'title-3')");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
}
