package com.smartandoisian.checkconfig;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

public class CheckScreenConfig extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView scrConfig = (TextView) findViewById(R.id.text);
        Display display = getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        scrConfig.setText("Screen Width = "+screenWidth+" Screen Height = "+screenHeight);
    }
}