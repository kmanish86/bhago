package com.bhago.geo;

import com.bhago.geo.EnumCalculation.ElevationUnits;

public class Elevation {

	Float value;
	ElevationUnits units;
	
	public Elevation() {
		
	}
	
	public Elevation(ElevationUnits units) {
		this.units = units;
	}
	
	
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public ElevationUnits getUnits() {
		return units;
	}
	public void setUnits(ElevationUnits units) {
		this.units = units;
	}
	
}
