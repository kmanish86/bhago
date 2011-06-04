package com.bhago.geo;

import com.bhago.geo.EnumCalculation.ElevationUnits;

public class Elevation {

	long value;
	ElevationUnits units;
	
	
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public ElevationUnits getUnits() {
		return units;
	}
	public void setUnits(ElevationUnits units) {
		this.units = units;
	}
	
}
