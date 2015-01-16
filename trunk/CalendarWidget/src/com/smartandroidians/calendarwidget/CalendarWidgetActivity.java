package com.smartandroidians.calendarwidget;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;

public class CalendarWidgetActivity extends Activity {
	
	private int currentSelectedYear;
	private int currentSelectedMonth;
	private int currentSelectedDate;
	private LinearLayout calendarMainLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_widget);
        init();
    }
    
    private void init() {
    	Calendar cal = Calendar.getInstance();
    	currentSelectedYear = cal.get(Calendar.YEAR);
		currentSelectedMonth = cal.get(Calendar.MONTH) + 1;
		currentSelectedDate = cal.get(Calendar.DAY_OF_MONTH);
		Log.i("CalendarWodget", "************************************************************************* "
				+" year = "+currentSelectedYear+" month = "+currentSelectedMonth+" date = "+currentSelectedDate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calendar_widget, menu);
        return true;
    }
}
