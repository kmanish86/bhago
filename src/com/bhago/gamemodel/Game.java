package com.bhago.gamemodel;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		 List<GeoCodeLocation> nearbyPlaces = fetchLatLong(""+currentLocation.latitude, ""+currentLocation.longitude);
		 Iterator<GeoCodeLocation> iter = nearbyPlaces.iterator();
		 ArrayList <MyLocation> myLocations = new ArrayList<MyLocation>();
		 while(iter.hasNext())
		 {
			 GeoCodeLocation loc = iter.next();
			 myLocations.add(new MyLocation(loc.name, loc.geometry.location.lat, loc.geometry.location.lng));
		 }
		 neighbouringLocations = myLocations.toArray(new MyLocation[myLocations.size()]);
	}

	public List<GeoCodeLocation> fetchLatLong(String latitude, String longitude) {
			String url = "https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longitude +"&radius=200000&sensor=false&key=AIzaSyDUYvpdxItjQOkvk41_ZMgOhrq5V8z9k-w";
			URLConnection connection;
			List<GeoCodeLocation> locations = new ArrayList<GeoCodeLocation>();
			try
			{
				connection = new URL(url).openConnection();
				InputStream is = connection.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    int bytesRead;
			    byte[] buffer = new byte[2048];
			    String readLine;
			    while ( (bytesRead = bis.read(buffer,0,2048)) != -1) {
			          baos.write(buffer,0,bytesRead);
			    }
				System.out.println("data=" + baos.toString());

				Gson gson = new GsonBuilder().create();
				Places places=gson.fromJson(baos.toString(), Places.class);
				locations = places.getResults();
				if(locations.size()>1)
				{
					locations=locations.subList(1, locations.size()-1);
				}
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return locations;
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
