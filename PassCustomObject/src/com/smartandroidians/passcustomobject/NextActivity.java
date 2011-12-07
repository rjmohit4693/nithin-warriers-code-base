package com.smartandroidians.passcustomobject;

import com.smartandroidians.passcustomobject.PassCustomObjectActivity.MyClass;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NextActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyClass obj = (MyClass) getIntent().getSerializableExtra(PassCustomObjectActivity.KEY);
		String name = obj.getName();
		int empId = obj.getEmpId();
		Log.i("NextActivity", "**************************************** name = "+name+" EmpId = "+empId);
	}

}
