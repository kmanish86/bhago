package com.bhago.gamemodel;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

public class Game {
	
	public MyLocation currentLocation;
	
	public MyLocation [] neighbouringLocations;
	
	Activity activity;
	
	public Game(Activity activity)
	{
		this.activity = activity;
	}
	public boolean isGameOver()
	{
		if(currentLocation.elevation <= getWaterLevel())
			return true;
		return false;
		
	}
	
	private double getWaterLevel()
	{
		return 0;
	}
	
	public void start()
	{
		//1. Get current location from lat long
		currentLocation = getCurrentLocation();
		
		//2. Build list of places nearby locations
		buildNearbyLocations(currentLocation);
		
		//3. do we need any state? 
	}
	
	private void buildNearbyLocations(MyLocation location)
	{
		
	}
	
	public void move(MyLocation newLocation)
	{
		currentLocation = newLocation;
		buildNearbyLocations(currentLocation);
	}
	
	private MyLocation getCurrentLocation()
	{
		PackageManager pm = activity.getPackageManager();
		boolean hasGps = pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
		double latitude=12.917319;
		double longitude=77.634585;
		
		if(hasGps)
		{
			try
			{
				LocationManager lm = (LocationManager)activity.getSystemService(Context.LOCATION_SERVICE); 
				if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
				{
					Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					 longitude = location.getLongitude();
					latitude = location.getLatitude();
				}
			}
			catch(Exception e)
			{
				Toast toast =Toast.makeText(activity, "Language Capture failed", Toast.LENGTH_SHORT);
				toast.show();
			}
			
			//Find other ways for finding location: may be google ip based
			
			//If everything fails use the default location: Koramangla
		}
		MyLocation location = new MyLocation(latitude,longitude);
		location.elevation = getElevation(location);
		return location;
	}
	private double getElevation(MyLocation location) {
		//TODO: get elevation based on lat  long
		return 0;
	}

}
