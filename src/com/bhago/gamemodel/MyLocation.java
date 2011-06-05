package com.bhago.gamemodel;


public class MyLocation{
	public int x; //x of screen
	public int y; //y of screen
	public double latitude; //ofcourse earth's
	public double longitude;
	public double elevation;
	public String name = null;
	
	public MyLocation(double latitude, double longitude)
	{
		this.latitude = latitude;
		this.longitude =longitude;
	}

	public MyLocation(String name, float lat, float lng) {
		this(lat, lng);
		this.name = name;
	}

}
