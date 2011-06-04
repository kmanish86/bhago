package com.bhago.geo;

public class LatLong {

	public float latitude;
	public float longitude;
	
	
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
	public boolean isValid() {
		return true;
	}
}
