package com.showflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.LinearLayout.LayoutParams;

public class ShowFlipper extends Activity {

	ViewFlipper flipper;
	Button button1;
	Button button2;
	LinearLayout l1;
	LinearLayout l2;
	ScrollView sv1,sv2;
	TextView tv1, tv2, tv3, tv4, tv5, tv6;
	LayoutParams params;
	GestureDetector gestureDetector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		gestureDetector = new GestureDetector(new MyGestureDetector());
		flipper = new ViewFlipper(this);
		l1 = new LinearLayout(this);
		l2 = new LinearLayout(this);
		sv1 = new ScrollView(this);
		sv2 = new ScrollView(this);
		button1 = new Button(this);
		button2 = new Button(this);
		button1.setText("Button 1");
		button2.setText("Button 2");

		l1.setOrientation(LinearLayout.VERTICAL);
		l2.setOrientation(LinearLayout.VERTICAL);
		l1.setId(1);
		l2.setId(2);
		tv1 = new TextView(this);
		tv2 = new TextView(this);
		tv3 = new TextView(this);
		tv4 = new TextView(this);
		tv5 = new TextView(this);
		tv6 = new TextView(this);
		
		tv1.setTextSize(20);
		tv2.setTextSize(20);
		tv3.setTextSize(20);
		tv4.setTextSize(20);
		tv5.setTextSize(20);
		tv6.setTextSize(20);
		
		tv1.setText("text view 1");
		tv2.setText("text view 2");
		tv3.setText("text view 3");
		tv4.setText("text view 4");
		tv5.setText("text view 5");
		tv6.setText("text view 6");
		l1.addView(tv1, params);
		l1.addView(tv2, params);
		l1.addView(tv3, params);
		
		// l1.addView(button1, params);
		l2.addView(tv4, params);
		l2.addView(tv5, params);
		l2.addView(tv6, params);
		
		
		// l2.addView(button2, params);

		//flipper.addView(l1);
		//flipper.addView(l2);
		sv1.addView(l1);
		sv2.addView(l2);
		flipper.addView(sv1);
		flipper.addView(sv2);
		flipper.setClickable(true);
		flipper.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				} else {
					return false;
				}
			}
		});

		// l1.setOnTouchListener(new OnTouchListener() {
		//
		// public boolean onTouch(View v, MotionEvent event) {
		// if (event.getAction() == MotionEvent.ACTION_MOVE) {
		// flipper.setInAnimation(inFromRightAnimation());
		// flipper.setOutAnimation(outToLeftAnimation());
		// flipper.showNext();
		// }
		// return false;
		// }
		//
		// });
		//
		// l2.setOnTouchListener(new OnTouchListener() {
		//
		// public boolean onTouch(View v, MotionEvent event) {
		// if (event.getAction() == MotionEvent.ACTION_MOVE) {
		// flipper.setInAnimation(inFromLeftAnimation());
		// flipper.setOutAnimation(outToRightAnimation());
		// flipper.showPrevious();
		// }
		// return false;
		// }
		//
		// });

		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				flipper.setInAnimation(inFromRightAnimation());
				flipper.setOutAnimation(outToLeftAnimation());

				// LinearLayout parent2 = (LinearLayout) tv1.getParent();
				// parent2.removeAllViews();

				// FrameLayout parent = (FrameLayout) flipper.getParent();
				// parent.removeAllViews();

				// tv1.setText("after removing : text view 1");
				// tv2.setText("after removing : text view 2");
				// tv3.setText("after removing : text view 3");
				//									
				// flipper.removeAllViews();
				// l1.removeAllViews();
				//
				// l1.addView(tv1, params);
				// l1.addView(tv2, params);
				// l1.addView(tv3, params);
				// l1.addView(view, params);
				// flipper.addView(l1);
				flipper.showNext();
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				flipper.setInAnimation(inFromLeftAnimation());
				flipper.setOutAnimation(outToRightAnimation());

				// flipper.removeAllViews();
				// l1.removeAllViews();
				//				
				// tv1.setText("text view 1");
				// tv2.setText("text view 2");
				// tv3.setText("text view 3");
				//				
				// l1.addView(tv1, params);
				// l1.addView(tv2, params);
				// l1.addView(tv3, params);
				// l1.addView(button1, params);
				// flipper.addView(l1);

				flipper.showPrevious();
			}
		});
		System.out.println("flipper : getChildCount :: "
				+ flipper.getChildCount());
		setContentView(flipper);
	}

	/*
	 * If scrollview is the child for viewFlipper, then dispatchTouchEvent() has
	 * to override otherwise fling won't work (non-Javadoc)
	 * 
	 * @see android.app.Activity#dispatchTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		super.dispatchTouchEvent(ev);
		return gestureDetector.onTouchEvent(ev);
	}
	
	private Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(500);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	private Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	private Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(500);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	private Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoRight.setDuration(500);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}

	class MyGestureDetector extends SimpleOnGestureListener {

		private static final int SWIPE_MIN_DISTANCE = 120;
		private static final int SWIPE_MAX_OFF_PATH = 250;
		private static final int SWIPE_THRESHOLD_VELOCITY = 200;

		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			System.out.println(" in onFling() :: ");
			if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
				return false;
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				flipper.setInAnimation(inFromRightAnimation());
				flipper.setOutAnimation(outToLeftAnimation());
				flipper.showNext();
			} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				flipper.setInAnimation(inFromLeftAnimation());
				flipper.setOutAnimation(outToRightAnimation());
				flipper.showPrevious();
			}
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}

}