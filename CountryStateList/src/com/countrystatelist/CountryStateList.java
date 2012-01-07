package com.countrystatelist;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;

public class CountryStateList extends ExpandableListActivity {

	private static final String NAME = "NAME";
	private static final String IS_EVEN = "IS_EVEN";
	private ExpandableListAdapter mAdapter;
	List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
	List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
	InputStream is;
	boolean addState = true;
	boolean addCountry;
	HashMap<String, String> countryMap = new HashMap<String, String>();
	HashMap<String, String> stateMap = new HashMap<String, String>();
	int count = 0;
	int count1 = -1;
	int count2 = 0;
	List<String> stateCount = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Reading data from text file to inout stream
		is = this.getResources().openRawResource(R.raw.countrystate);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] strings = line.split("-");
				String ctry = strings[0].trim();
				String st = strings[1].trim();
				/*
				 * loading country and state to two separate hash tables
				 */
				if (countryMap.containsValue(ctry)) {
					addCountry = false;
					if (!addState) {
						stateMap.put("state" + count1 + count2, st);
						count2++;
					}
				} else {
					addCountry = !addCountry;
					count1++;
					//adding no. of states for a country to a list
					stateCount.add("" + count2);
					if (addCountry) {
						count2 = 0;
						countryMap.put("country" + count, ctry);
						stateMap.put("state" + count1 + count2, st);
						addState = false;
					}
					count2++;
					count++;
				}
			}
		} catch (Exception e) {
		}
		//re-arranging the state count list
		stateCount.add("" + count2);
		stateCount.remove(0);
		for (int i = 0; i < countryMap.size(); i++) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			String ctry = countryMap.get("country" + i);
			curGroupMap.put(NAME, ctry);
			curGroupMap.put(IS_EVEN, "Country " + i);
			List<Map<String, String>> children = new ArrayList<Map<String, String>>();
			int k = Integer.parseInt(stateCount.get(i));
			for (int j = 0; j < k; j++) {
				Map<String, String> curChildMap = new HashMap<String, String>();
				children.add(curChildMap);
				curChildMap.put(NAME, stateMap.get("state" + i + j));
				curChildMap.put(IS_EVEN, "State " + j);
			}
			childData.add(children);
		}

		// Set up our adapter
		mAdapter = new SimpleExpandableListAdapter(this, groupData,
				android.R.layout.simple_expandable_list_item_1, new String[] {
						NAME, IS_EVEN }, new int[] { android.R.id.text1,
						android.R.id.text2 }, childData,
				android.R.layout.simple_expandable_list_item_2, new String[] {
						NAME, IS_EVEN }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		setListAdapter(mAdapter);
	}
}