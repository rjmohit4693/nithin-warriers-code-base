package com.smartandroidians.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ForeignKeyConstraint extends SQLiteOpenHelper {
	
	public static String DATABASE_NAME = "DatabaseForForeignKey";
	public static int DATABASE_VERSION = 1;
	private SQLiteDatabase mDatabase;

	public ForeignKeyConstraint(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mDatabase = getWritableDatabase();
		onCreate(mDatabase);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table Artist (artistid integer primary key, artistname text);");
		db.execSQL("create table Track (trackid integer, title text, trackartist integer, foreign key(trackartist) references Artist(artistid) );");
		
		db.execSQL("insert into Artist values (1, 'title-1')");
		db.execSQL("insert into Artist values (2, 'title-2')");
		db.execSQL("insert into Artist values (3, 'title-3')");
		db.execSQL("insert into Artist values (4, 'title-4')");
		
		db.execSQL("insert into Track values (0, 'title-1', 1)");
		db.execSQL("insert into Track values (1, 'title-1', 1)");
		db.execSQL("insert into Track values (2, 'title-1', 1)");
		db.execSQL("insert into Track values (0, 'title-1', 2)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
