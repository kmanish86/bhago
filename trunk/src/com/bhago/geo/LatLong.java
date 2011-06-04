package com.bhago.geo;

public class LatLong {

	public long latitude;
	public long longitude;
	
	
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	
	
	public boolean isValid() {
		return true;
	}
}
