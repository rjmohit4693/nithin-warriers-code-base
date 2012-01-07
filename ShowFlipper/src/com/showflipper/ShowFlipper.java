package com.showflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.LinearLayout.LayoutParams;

public class ShowFlipper extends Activity {

	ViewFlipper flipper;
	Button button1;
	Button button2;
	LinearLayout l1;
	LinearLayout l2;
	TextView tv1, tv2, tv3, tv4, tv5, tv6;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		flipper = new ViewFlipper(this);
		l1 = new LinearLayout(this);
		l2 = new LinearLayout(this);
		button1 = new Button(this);
		button2 = new Button(this);
		button1.setText("Button 1");
		button2.setText("Button 2");

		l1.setOrientation(LinearLayout.VERTICAL);
		l2.setOrientation(LinearLayout.VERTICAL);
		tv1 = new TextView(this);
		tv2 = new TextView(this);
		tv3 = new TextView(this);
		tv4 = new TextView(this);
		tv5 = new TextView(this);
		tv6 = new TextView(this);
		tv1.setText("text view 1");
		tv2.setText("text view 2");
		tv3.setText("text view 3");
		tv4.setText("text view 4");
		tv5.setText("text view 5");
		tv6.setText("text view 6");
		l1.addView(tv1, params);
		l1.addView(tv2, params);
		l1.addView(tv3, params);
		l1.addView(button1, params);
		l2.addView(tv4, params);
		l2.addView(tv5, params);
		l2.addView(tv6, params);
		l2.addView(button2, params);

		flipper.addView(l1);
		flipper.addView(l2);

		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				flipper.setInAnimation(inFromRightAnimation());
				flipper.setOutAnimation(outToLeftAnimation());
				flipper.showNext();
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				flipper.setInAnimation(inFromLeftAnimation());
				flipper.setOutAnimation(outToRightAnimation());
				flipper.showPrevious();
			}
		});
		setContentView(flipper);
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

}