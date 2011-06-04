package com.bhago;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bhago.gamemodel.Game;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class SafeRouteActivity extends MapActivity {
	
	Game game = null;
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mapview);
	    
	    game = new Game(this);
		
	    game.start();
	    
	    MapView mapView = (MapView) findViewById(R.id.mapView);
	    mapView.setBuiltInZoomControls(true);
	    
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
	    WaterOverlay itemizedoverlay = new WaterOverlay(drawable, this);
	    
	    
	    
	    GeoPoint point = new GeoPoint((int)(1000000*game.currentLocation.latitude),(int)(1000000*game.currentLocation.longitude));
	    OverlayItem overlayitem = new OverlayItem(point, "Hola, Jayanth!", "I'm in Bengaluru!");
	    
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedoverlay);
	    mapView.getController().animateTo(point);
	    mapView.getController().setZoom(7);
	    
	    
	}
	

}
