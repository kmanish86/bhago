package com.bhago.geo;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.bhago.geo.EnumCalculation.ElevationUnits;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;


public class ElevationCalcutor {

	public final static String url = "http://www.gpsvisualizer.com/elevation_data/elev.js";
	public final static String coordsQueryParam = "coords";
	
	public static Elevation calculateElevation(LatLong latlong, ElevationUnits units) {

		System.out.println("Started elevation calculation");
		if(units == null)
			units = ElevationUnits.Feet;
		
		if(!latlong.isValid())
			return null;
		Float res = getElevationFromGPS(latlong);
		Elevation elevation = new Elevation(units);
		if(units.toString().equals(ElevationUnits.Meters.toString())) {
			elevation.setValue(res);
		}else {
			//converting meters to feet
			elevation.setValue(res*3.2808399f);
		}
		return elevation;
		 
	}


	
	/**
	 * Returns the elevation of the location specified using latlong object in meters 
	 * @param latlong
	 * @return
	 */
	private static Float getElevationFromGPS(LatLong latlong) {
		
		System.out.println("Will do a http call to gps");
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest get = new HttpGet(url + "?" + coordsQueryParam + "=" + latlong.getLatitude() + "," + latlong.longitude);
		try {
			
			HttpResponse response = client.execute(get);
			HttpEntity e = response.getEntity();
			InputStream is = e.getContent();
			BufferedInputStream bis = new BufferedInputStream(is);
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    int bytesRead;
		    byte[] buffer = new byte[2048];
		    String readLine;
		    while ( (bytesRead = bis.read(buffer,0,2048)) != -1) {
		          baos.write(buffer,0,bytesRead);
		    }
		    readLine = baos.toString();
			char[] chars = new char[20];
			readLine.getChars(23, 43, chars, 0);
			String resString = new String(chars);
			float res = Float.parseFloat(resString);
			float resMeters = 1f/res;
			return resMeters;

		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
