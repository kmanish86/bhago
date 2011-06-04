package com.bhago.geo;

import java.io.IOException;

import com.bhago.geo.EnumCalculation.ElevationUnits;

import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;


public class ElevationCalcutor {

	public final static String url = "http://www.gpsvisualizer.com/elevation_data/elev.js";
	
	public static Elevation calculateElevation(LatLong latlong, ElevationUnits units) {
		
		if(units == null)
			units = ElevationUnits.Feet;
		
		if(!latlong.isValid())
			return null;
		
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest get = new HttpGet(url);
		HttpParams params = client.getParams();
		params.setParameter("coords", latlong.getLatitude() +"," + latlong.getLongitude());
		try {
			HttpResponse response = client.execute(get);
			System.out.println(response.getEntity());
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}


	
	
}
