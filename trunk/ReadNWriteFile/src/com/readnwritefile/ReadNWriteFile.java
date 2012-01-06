package com.readnwritefile;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ReadNWriteFile extends Activity {

	private final String TEST_STRING = new String("Hello Android");
	private final String FILE_NAME = "SAMPLEFILE.txt";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		fileCreate();
		tv.setText(readFile());
		setContentView(tv);
	}

	private void fileCreate() {
		try {
			OutputStream os = openFileOutput(FILE_NAME, MODE_WORLD_READABLE);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write(TEST_STRING);
			osw.close();
		} catch (Exception e) {
			Log.i("ReadNWrite, fileCreate()", "Exception e = " + e);
		}
	}

	private String readFile() {
		try {
			FileInputStream fin = openFileInput(FILE_NAME);
			InputStreamReader isReader = new InputStreamReader(fin);
			char[] buffer = new char[TEST_STRING.length()];
			// Fill the buffer with data from file
			isReader.read(buffer);
			return new String(buffer);
		} catch (Exception e) {
			Log.i("ReadNWrite, readFile()", "Exception e = " + e);
			return null;
		}
	}
}