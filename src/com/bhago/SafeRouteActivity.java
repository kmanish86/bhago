package com.bhago;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bhago.gamemodel.Game;
import com.bhago.gamemodel.GameThread;
import com.bhago.gamemodel.MyLocation;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class SafeRouteActivity extends MapActivity {

	public Game game = null;
	GameThread gameThread = null;

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

		gameThread = new GameThread(this);
		gameThread.start();

		mapView.getController().setZoom(7);
		renderGame();

	}

	void renderGame() {
		MapView mapView = (MapView) findViewById(R.id.mapView);
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.home_icon);
		Drawable flagDrawable = this.getResources().getDrawable(R.drawable.flag_icon);
		MyLocation[] myLocations = game.neighbouringLocations;
		System.out.println("Render : "+game.currentLocation.name+", with neighbours: "+myLocations.length);
		for (int i = 0; i <= myLocations.length; i++) {
			MyLocation loc = null;
			WaterOverlay itemizedoverlay = null;

			if (myLocations.length == i)
			{
				loc = game.currentLocation;
				itemizedoverlay = new WaterOverlay(flagDrawable, this, loc);
			}else{
				loc = myLocations[i];
				itemizedoverlay = new WaterOverlay(drawable, this, loc);
			}
			
			
			GeoPoint point = new GeoPoint((int) (1000000 * loc.latitude),
					(int) (1000000 * loc.longitude));
			OverlayItem overlayitem = new OverlayItem(point, "Moving to",
					loc.name);

			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);

			if (myLocations.length == i)
				mapView.getController().animateTo(point);
		}
	}

}
