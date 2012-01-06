package com.smartandroidians.sdcard.guide;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SDCardGuideActivity extends Activity {
    
	private final String TAG = "SDCardGuideActivity";
	private boolean DEBUG = true;
	private Button openSdCard;
	private ImageView imgFromSdCard;
	private final String TEST_STRING = new String("Hello Android");
	private final String FILE_NAME = "/sdcard/SAMPLEFILE.txt";
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) Log.i(TAG, "******************** inside onCreate()");
        setContentView(R.layout.main);
        openSdCard = (Button) findViewById(R.id.openSdcard);
        imgFromSdCard = (ImageView) findViewById(R.id.imgFromSdcard);
        openSdCard.setOnClickListener(sdCardListener);
        createFile();
        Log.i(TAG, "****************** readFileFromSdCard() = "+readFileFromSdCard());
    }
    
    private String readFileFromSdCard() {
    	try {
			//FileInputStream fin = openFileInput(FILE_NAME);
			FileInputStream fin = new FileInputStream(new File(FILE_NAME));
    		InputStreamReader isReader = new InputStreamReader(fin);
			char[] buffer = new char[TEST_STRING.length()];
			// Fill the buffer with data from file
			isReader.read(buffer);
			return new String(buffer);
		} catch (Exception e) {
			Log.i(TAG, "************** Exception in readFileFromSdCard() - e = " + e);
			return null;
		}
    }
    
    View.OnClickListener sdCardListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Log.i(TAG, "************ inside open sdcard onClick");
			try {
				//FileInputStream fin = openFileInput(FILE_NAME);
				/*
				 * Context.openFileOutput is meant to be used for creating files private 
				 * to your application. they go in your app's private data directory. you 
				 * supply a name, not a path: "name The name of the file to open; can not 
				 * contain path separators".
				 */
				FileInputStream in = new FileInputStream("/mnt/sdcard/DCIM/100MEDIA/IMAG0439.jpg");
				BufferedInputStream buf = new BufferedInputStream(in);
	            Bitmap bMap = BitmapFactory.decodeStream(buf);
	            imgFromSdCard.setImageBitmap(bMap);
			} catch (Exception e) {
				e.printStackTrace();
				Log.i(TAG, "****************** Exception in file opening from SD-Card");
			}
			/*File file = Environment.getExternalStorageDirectory();
			String path = file.getAbsolutePath();
			File file2 = Environment.getExternalStorageDirectory();
			File file3 = new File("/mnt/sdcard/DCIM/100MEDIA/IMAG0005.jpg");
			
			
			String DCIM_path = Environment.DIRECTORY_DCIM;
			Log.i(TAG, "****************** DCIM path = "+DCIM_path);
			String[] listFiles_sdcard_root = file.list();
			for (String fileName : listFiles_sdcard_root) {
				Log.i(TAG, "*********** listFiles Sd-card root = "+fileName);
			}
			String downloads_path = Environment.DIRECTORY_DOWNLOADS;
				
			Log.i(TAG, "********** getAbsolutePath = "+path);
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				
			}*/
		}
	};
	
	
	private void createFile() {
		try {
			//OutputStream os = openFileOutput(FILE_NAME, MODE_WORLD_READABLE);
			//OutputStream os = new outpu
			FileOutputStream fos = new FileOutputStream(new File(FILE_NAME));
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.write(TEST_STRING);
			osw.close();
		} catch (Exception e) {
			Log.i("ReadNWrite, fileCreate()", "Exception e = " + e);
		}
	}
}