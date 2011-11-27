package com.smartandroidians.cropselectedimage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

public class CropSelectedImageActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, 1);
    }
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			final Bundle extras = data.getExtras();
			Uri photoUri = data.getData();
			if (photoUri != null) {
				Intent intent = new Intent("com.android.camera.action.CROP");
		        //intent.setClassName("com.android.camera", "com.android.camera.CropImage");

		        intent.setData(photoUri);
		        intent.putExtra("outputX", 96);
		        intent.putExtra("outputY", 96);
		        intent.putExtra("aspectX", 1);
		        intent.putExtra("aspectY", 1);
		        intent.putExtra("scale", true);
		        intent.putExtra("return-data", true);            
		        startActivityForResult(intent, 1);
			}
			/*if (extras != null) {
				Intent intent = new Intent("com.android.camera.action.CROP");
			    intent.setClassName("com.android.camera", "com.android.camera.CropImage");
		        intent.setData(mImageCaptureUri);
		        intent.putExtra("outputX", 96);
		        intent.putExtra("outputY", 96);
		        intent.putExtra("aspectX", 1);
		        intent.putExtra("aspectY", 1);
		        intent.putExtra("scale", true);
		        intent.putExtra("return-data", true);            
			    startActivityForResult(intent, CROP_FROM_CAMERA);
		}*/
		}
	}
}