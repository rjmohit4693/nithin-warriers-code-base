package com.smartandroidians.sampleui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SampleUIActivity extends Activity {
	
	private ListView listView1, listView2;
	private TextView text,airportLines;
	private ArrayList<String> list1 = new ArrayList<String>();
	private ArrayList<String> list2 = new ArrayList<String>();
	private boolean textClick1 = true, textClick2 = true;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list1.add("one");
        list1.add("two");
        list1.add("three");
        list1.add("four");
        list1.add("five");
        list1.add("six");
        list1.add("seven");
        list1.add("eight");
        list1.add("nine");
        list1.add("ten");
        list2.addAll(list1);
        text = (TextView) findViewById(R.id.alllocations);
        text.setClickable(true);
        airportLines = (TextView) findViewById(R.id.airportlines);
        airportLines.setClickable(true);
        listView1 = (ListView) findViewById(R.id.list1);
        listView2 = (ListView) findViewById(R.id.list2);
        listView1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1));
        listView2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2));
        text.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (textClick1) { 
					listView1.setVisibility(View.VISIBLE);
					textClick1 = !textClick1;
				} else {
					listView1.setVisibility(View.GONE);
					textClick1 = !textClick1;
				}
			}
		});
        airportLines.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (textClick2) { 
					listView2.setVisibility(View.VISIBLE);
					textClick2 = !textClick2;
				} else {
					listView2.setVisibility(View.GONE);
					textClick2 = !textClick2;
				}
			}
		});
    }
}