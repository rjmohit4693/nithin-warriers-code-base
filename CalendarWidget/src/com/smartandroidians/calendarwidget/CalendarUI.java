package com.smartandroidians.calendarwidget;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CalendarUI extends LinearLayout {
	
	private final String TAG = "CalendarUI";
	private LinearLayout calendarLayout;
	private Context mContext;
	private RelativeLayout changeYearLayout;
	private TextView monthTxtView = null;
	private Button prev = null;
	private Button next = null;
	private TranslateAnimation slide_toLeft, slide_toRight = null;
	private android.widget.RelativeLayout.LayoutParams prevParams;
	private android.widget.RelativeLayout.LayoutParams nextParams;
	private android.widget.RelativeLayout.LayoutParams monTxtParams;
	private String[] days = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	private String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", 
			"September", "October",  "November", "December" };
	private GregorianCalendar gregorianCalendar;
	private int currentSelectedMonth = 0;
	private int currentSelectedYear = 0;
	private int currentSelectedDate;
	private TableLayout calendarRowLayout;
	private TextView[] rowsForCalendar;
	private Calendar calendar;
	private int endYear = 0;
	private int endMonth = 0;
	private int endDate = 0;
	private int startYear = 0;
	private int startMonth = 0;
	private int startDate = 0;
	
	/**
	 * Calendar widget
	 */
	public CalendarUI (Context context) {
		super(context);
		mContext = context;
		init();
	}
	
	/** To get the current date (day, month and year) */
	private void getCurrentDate() {
		calendar = Calendar.getInstance();
		currentSelectedYear = calendar.get(Calendar.YEAR);
		currentSelectedMonth = calendar.get(Calendar.MONTH) + 1;
		currentSelectedDate = calendar.get(Calendar.DAY_OF_MONTH);
		Log.i("CalendarWodget", "************************************************************************* "
				+" year = "+currentSelectedYear+" month = "+currentSelectedMonth+" date = "+currentSelectedDate);
	}
	
	/** To initialize variables */
	private void init() {
		calendarLayout = new LinearLayout(mContext);
		calendarLayout.setOrientation(LinearLayout.VERTICAL);
		calendarLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		getCurrentDate();
		setChangeYearLayout();
		setCalendarRowLayout();
		addComponentToCalendar();
		refreshCalendar(currentSelectedYear, currentSelectedMonth);
	}
	
	private void setCalendarRowLayout() {
		calendarRowLayout = new TableLayout(mContext);
		calendarRowLayout.setStretchAllColumns(true);
		calendarRowLayout.setGravity(Gravity.CENTER);
		rowsForCalendar = new TextView[49];
		TableRow rowForDate = new TableRow(mContext);
		int cellWidth = 10;//2% of 480
		int cellHeight = cellWidth;
		for (int m = 0; m < 7; m++) {
			rowForDate = new TableRow(mContext);
			for (int n = 0; n < 7; n++) {
				if (m == 0) {
					rowsForCalendar[n] = createTextView(days[n], cellWidth, cellHeight, 8.0f, 0xff000000);
					rowsForCalendar[n].setGravity(Gravity.CENTER);
				} else {
					rowsForCalendar[m * 7 + n] = createTextView("  ", cellWidth, cellHeight,  8.0f, 0xff000000);
					rowsForCalendar[m * 7 + n].setGravity(Gravity.CENTER);
					rowsForCalendar[m * 7 + n].setBackgroundResource(R.drawable.active);
				}
				rowForDate.addView(rowsForCalendar[m*7 + n]);  
			}
			calendarRowLayout.addView(rowForDate);
		}
	}
	
	/*
	 * function to create TextView according to the parameters given and returns
	 * a TextView with the specified settings as given in the parameters
	 */
	public TextView createTextView(String label, int width, int height, float textSize, int textColor) {
		TextView tv = new TextView(mContext);
		tv.setText(label);
		tv.setWidth(width); 
		tv.setHeight(height);
		tv.setTextSize(textSize-2);
		tv.setTextColor(textColor);
		tv.setTypeface(Typeface.DEFAULT_BOLD);
		return tv;
	}
	
	/** To set the next and previous buttons as well as to show the months layout */
	private void setChangeYearLayout() {
		changeYearLayout = new RelativeLayout(mContext);
		changeYearLayout.setBackgroundResource(R.drawable.caltopbg);
		prevParams = new android.widget.RelativeLayout.LayoutParams(
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
		nextParams = new android.widget.RelativeLayout.LayoutParams(
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
		monTxtParams = new android.widget.RelativeLayout.LayoutParams(
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
		prev = new Button(mContext);
		next = new Button(mContext);
		monthTxtView = new TextView(mContext);
		monthTxtView.setGravity(Gravity.CENTER_HORIZONTAL);
		monthTxtView.setTypeface(Typeface.DEFAULT_BOLD);
		monthTxtView.setTextSize(17.0f);
		monthTxtView.setWidth(336);//70% of 480
		prev.setBackgroundResource(R.drawable.cal_prev);
		next.setBackgroundResource(R.drawable.cal_next);
		prevParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		prevParams.addRule(RelativeLayout.CENTER_VERTICAL);
		nextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		nextParams.addRule(RelativeLayout.CENTER_VERTICAL);
		monTxtParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		slide_toLeft = new TranslateAnimation(480, 0, 0, 0);
		slide_toRight = new TranslateAnimation(-480, 0, 0, 0);
		slide_toLeft.setDuration(300);
		slide_toRight.setDuration(300);
		changeYearLayout.addView(prev, prevParams);
		changeYearLayout.addView(monthTxtView, monTxtParams);
		changeYearLayout.addView(next, nextParams);
		prev.setOnClickListener(previousClickListener);
		next.setOnClickListener(nextClickListener);
	}
	
	/**
	 * Listener for previous button click
	 */
	private View.OnClickListener previousClickListener = new OnClickListener() {

		public void onClick(View v) {
			try {
				monthTxtView.startAnimation(slide_toLeft);
				if (currentSelectedMonth == 1) {
					currentSelectedYear--;
					currentSelectedMonth = 12;
				} else {
					currentSelectedMonth--;
				}
				monthTxtView.setText(months[currentSelectedMonth - 1] + " " + currentSelectedYear);
				refreshCalendar(currentSelectedYear, currentSelectedMonth);
			} catch (Exception e) {
				Log.i(TAG, "Exception CalendarUI prev onClick() e = "+ e);
			}
		}
	};
	
	/**
	 * Listener for next button click 
	 */
	private View.OnClickListener nextClickListener = new OnClickListener() {

		public void onClick(View v) {
			try {
				monthTxtView.startAnimation(slide_toRight);
				if (currentSelectedMonth == 12) {
					currentSelectedYear++;
					currentSelectedMonth = 1;
				} else {
					currentSelectedMonth++;
				}
				monthTxtView.setText(months[currentSelectedMonth - 1] + " "
						+ currentSelectedYear);
				refreshCalendar(currentSelectedYear, currentSelectedMonth);
			} catch (Exception e) {
				Log.i(TAG, "Exception CalendarUI next onClick() e = "+e);
			}
		}
	};
	
	/** Add components to calendar layout*/
	private void addComponentToCalendar() {
		LayoutParams changeYrLtParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		Bitmap nxt = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.cal_next);
		int ht = nxt.getHeight();
		int CALENDAR_ARROW_PADDING = 10;//2% of 480
		int totalHt = CALENDAR_ARROW_PADDING + ht + CALENDAR_ARROW_PADDING;
		changeYrLtParams.height = totalHt;
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.width = 360;//75% of 480
		params.gravity = Gravity.CENTER_HORIZONTAL;
		calendarLayout.addView(changeYearLayout, changeYrLtParams);
		calendarLayout.addView(calendarRowLayout, params);
	}
	
	/**
	 * Calendar will be created in this method, by
	 * passing the year and month, corresponding calendar will
	 * be created
	 * 
	 * @param year
	 * @param month
	 */
	private void refreshCalendar(int year, int month) {
		try {
			gregorianCalendar = new GregorianCalendar(year, month - 1, 1); // Creating the calendar here
			int noOfDays = gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			int som = gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK);
			int dateLabel = 0;
			for (int j = 1; j < 7; j++) {
				for (int index = 0; index < 7; index++) {
					rowsForCalendar[j * 7 + index].setBackgroundResource(R.drawable.active);
					if (j == 1 && (som - 1) > index) {
						rowsForCalendar[j * 7 + index].setText("  ");
						rowsForCalendar[j * 7 + index].setFocusable(false);
						rowsForCalendar[j * 7 + index].setClickable(false);
						rowsForCalendar[j * 7 + index].setBackgroundResource(R.drawable.normal);
					} else if (((som - 1) <= index && j == 1) || j > 1) {
						rowsForCalendar[j * 7 + index].setText("" + (dateLabel + 1));
						rowsForCalendar[j * 7 + index].setId((dateLabel + 1));
						if (currentSelectedYear == endYear && currentSelectedMonth == endMonth
								&& (dateLabel + 1) > endDate) {
							rowsForCalendar[j * 7 + index].setFocusable(false);
							rowsForCalendar[j * 7 + index].setClickable(false);
							rowsForCalendar[j * 7 + index].setBackgroundResource(R.drawable.unavailable);
						} else if (currentSelectedYear == startYear && currentSelectedMonth == startMonth
								&& (dateLabel + 1) < startDate) {
							rowsForCalendar[j * 7 + index].setFocusable(false);
							rowsForCalendar[j * 7 + index].setClickable(false);
							rowsForCalendar[j * 7 + index].setBackgroundResource(R.drawable.unavailable);
						} else {
							rowsForCalendar[j * 7 + index].setFocusable(true);
							rowsForCalendar[j * 7 + index].setClickable(true);
							rowsForCalendar[j * 7 + index].setOnClickListener(calendarRowTextClickListener);
						}
						dateLabel++;
					}
					if (dateLabel > noOfDays) {
						rowsForCalendar[j * 7 + index].setText("  ");
						rowsForCalendar[j * 7 + index].setFocusable(false);
						rowsForCalendar[j * 7 + index].setClickable(false);
						rowsForCalendar[j * 7 + index].setBackgroundResource(R.drawable.normal);
					}
				}
			}
		} catch(Exception e) {
			Log.i(TAG, "Exception in refreshCalendar() - E = "+e);
		}
	}
	
	/**
	 * Listener when a date is clicked in the calendar
	 */
	private View.OnClickListener calendarRowTextClickListener = new OnClickListener() {

		public void onClick(View v) {
			try {
				currentSelectedDate = v.getId();

				
				String appendM = "";
				String appendD = "";
				if (currentSelectedDate < 10)
					appendD = "0";
				if (currentSelectedMonth < 10)
					appendM = "0";
				//input_M.setText(appendD	+ currentSelectedDate + "/" + appendM + currentSelectedMonth + "/" + currentSelectedYear);
			} catch (Exception e) {
				Log.i(TAG, "Exception in calendarRowTextClickListener() - E = "+ e);
			}
		}
	};

}
