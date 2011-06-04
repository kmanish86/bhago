package com.bhago.gamemodel;

import java.util.List;

public class Places{
	List<String> html_attributions;
	List<GeoCodeLocation> results;
	String status;
	public List<GeoCodeLocation> getResults() {
		return results;
	}
	public void setResults(List<GeoCodeLocation> results) {
		this.results = results;
	}

}

class GeoCodeLocation {
	Geometry geometry;
	String icon;
	String id;
	String name;
	String reference;
	List<String> types;
	String vicinity;
	public String getVicinity() {
		return vicinity;
	}
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}


}

class Location{
	float lat;
	float lng;
}

class Geometry{
	Location location;
}