package com.smartandroidians.ttssample;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

public class TTSSample extends Activity implements OnInitListener {
    
	private TextToSpeech speechEngine;
	private final String TAG = "TTSSample";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        speechEngine = new TextToSpeech(this, this);
    }

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			Log.i(TAG, "**************************************** TextToSpeech.SUCCESS");
			int result = speechEngine.setLanguage(Locale.US);
			if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.i(TAG, "**************************************** Lang not supported");
			} else {
				speechEngine.setPitch(1);
				speechEngine.setSpeechRate(1);
				speechEngine.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null);
			}
		} else {
			Log.i(TAG, "****************************** could not initialize textToSpeech");
		}
		
	}
	
	public void onStop() {
		super.onStop();
		if (speechEngine != null) {
			speechEngine.stop();
			speechEngine.shutdown();
		}
	}
}