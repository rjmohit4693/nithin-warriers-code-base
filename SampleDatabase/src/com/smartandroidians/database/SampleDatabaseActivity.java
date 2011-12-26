package com.smartandroidians.database;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SampleDatabaseActivity extends Activity {
	
	String TAG = "SampleDatabaseActivity";
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //database creation
        //DBHelper helper = new DBHelper(this, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
        //helper.onCreate(helper.getWritableDatabase());
        
        //database creation
        ForeignKeyConstraint frKeyConstraint = new ForeignKeyConstraint(this, ForeignKeyConstraint.DATABASE_NAME, null, 
        		ForeignKeyConstraint.DATABASE_VERSION);
        /*DBAdapter adapter = new DBAdapter(this);
        adapter.openDatabase();
        
        adapter.insert("adding title through contentValues-1");
        adapter.insert("adding title through contentValues-2");
        adapter.insert("adding title through contentValues-3");
        
        int rowCount = adapter.getAllTitles().getCount();
        Log.i(TAG, "************* 1- rowCount = "+rowCount);
        
        adapter.delete(1);
        
        rowCount = adapter.getAllTitles().getCount();
        Log.i(TAG, "************* 2-rowCount = "+rowCount);
        
        adapter.update(2, "updated title-2");*/
        
        
        
    }
}