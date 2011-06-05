package com.bhago.gamemodel;

import android.app.Activity;
import android.os.Looper;
import android.widget.Toast;

public class GameThread extends Thread {
	
	boolean bRunning = true;
	Activity activity = null;
	
	long startTime = 0;
	
	public GameThread(Activity activity)
	{
		this.activity = activity;
	}
	public void stopRunning()
	{
		bRunning = false;
	}
	public void run()
	{
		Looper.prepare();
		startTime = System.currentTimeMillis();
		long lastTime = startTime;
		while(bRunning)
		{
			long currentTime = System.currentTimeMillis();
			if(currentTime > lastTime + 5000)
			{
				Toast.makeText(activity, "Water is rising", Toast.LENGTH_LONG);//TODO: add elevation
				lastTime = currentTime;
			}
			try{
				
				Thread.sleep(18);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
