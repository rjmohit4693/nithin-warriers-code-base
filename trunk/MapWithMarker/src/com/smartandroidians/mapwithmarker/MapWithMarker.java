package com.smartandroidians.mapwithmarker;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapWithMarker extends MapActivity {
	
	private MapView map;
	private MapController controller;
	TransparentPanel panel;
	TextView locationInfo;
	FrameLayout chiefLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_with_marker);
        init();
        int latitude = (int) (12.98f * 1E6);//Bangalore Lat & Long
        int longitude = (int) (77.58f * 1E6);
        setMapToSpecificPoint(latitude, longitude);
        setMarkerToSpecificPoint(latitude, longitude);
    }
    
    /** To initialize variables*/
    private void init() {
    	chiefLayout = (FrameLayout) findViewById(R.id.mainLayout);
    	map = (MapView) findViewById(R.id.map);
    	controller = map.getController();
    	panel = new TransparentPanel(this);
    	locationInfo = new TextView(this);
    	controller.setZoom(12);
        map.setClickable(true);
        LayoutParams infoParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams panelParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        panel.addView(locationInfo, infoParams);
        panel.setVisibility(View.INVISIBLE);
        chiefLayout.addView(panel, panelParams);
    }
    
    /** Set map to a specific point according to the latitude and longitude*/
    private void setMapToSpecificPoint(int latitude, int longitude) {
    	GeoPoint point = new GeoPoint(latitude, longitude);
        controller.setCenter(point);
    }
    
    /** Set a marker icon in map to a specific point*/
    private void setMarkerToSpecificPoint(int latitude, int longitude) {
    	List<Overlay> mapOverlays = map.getOverlays();
    	mapOverlays.clear();
    	Drawable marker = getResources().getDrawable(R.drawable.map_marker);
    	MyItemizedOverlay overlays = new MyItemizedOverlay(marker);
    	GeoPoint point = new GeoPoint(latitude, longitude);
    	OverlayItem item = new OverlayItem(point, "Bangalore", "snippet");
    	overlays.addOverlay(item);
    	mapOverlays.add(overlays);
    }

    @Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
    /** Inner class to show a marker on top of the map for a specific point. 
     * Overlays are displayed on top of the map. */
    class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
    	
    	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();

		public MyItemizedOverlay(Drawable defaultMarker) {
			super(boundCenterBottom(defaultMarker));
		}
		
		public void addOverlay(OverlayItem item) {
			mOverlays.add(item);
			populate();
		}

		@Override
		protected OverlayItem createItem(int position) {
			return mOverlays.get(position);
		}

		@Override
		public int size() {
			return mOverlays.size();
		}
		
		protected boolean onTap(int index) {
			super.onTap(index);
			OverlayItem item = mOverlays.get(index);
			panel.setVisibility(View.VISIBLE);
			locationInfo.setText(item.getTitle());
			return true;
		}
    }
    
}
