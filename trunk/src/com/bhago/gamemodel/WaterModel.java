package com.bhago.gamemodel;

public class WaterModel {
	
	long startTime = 0;
	long lastTime = 0;
	long lastWaterLevel = 0;
	double lastVelocity = 3; //3m/s
	
	public void start(int elevationInM){
		startTime = System.currentTimeMillis();
		lastTime = startTime;
		
		lastVelocity = Math.log(elevationInM/60);//TODO: replace with integration of exp function
	}
	
	public long getWaterLevelMeters()
	{
		long endTime = System.currentTimeMillis();
		int seconds = (int)(endTime - lastTime)/1000;
		lastVelocity = getWaterVelocityMetersPerSec();
		lastWaterLevel+= lastVelocity*seconds;
		lastTime = endTime;
		
		return lastWaterLevel;
	}
	
	private double getWaterVelocityMetersPerSec()
	{
		return lastVelocity+Math.exp(lastVelocity);
	}
	

}
