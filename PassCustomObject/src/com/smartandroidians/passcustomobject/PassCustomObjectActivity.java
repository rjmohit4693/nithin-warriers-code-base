package com.smartandroidians.passcustomobject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PassCustomObjectActivity extends Activity {
	
	public static final String KEY = "key";
	
	public class MyClass implements Serializable {
    	
    	String firstName;
    	int empID;
    	
    	public MyClass(String name, int id) {
    		firstName = name;
    		empID = id;
    	}
    	
    	private void writeObject(ObjectOutputStream out) {
    		try {
				out.writeUTF(firstName);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	private void readObject(ObjectInputStream in) {
    		try {
				firstName = in.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	public String getName() {
    		return firstName;
    	}
    	
    	public int getEmpId() {
    		return empID;
    	}
    }

    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("PassCustomObjectAct", "************************* onCreate()...");
        //setContentView(R.layout.main);
        MyClass class1 = new MyClass("Nithin", 13);
        Intent intent = new Intent(this, NextActivity.class);
        intent.putExtra(KEY, class1);
        startActivity(intent);
    }
    
}