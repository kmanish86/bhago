package com.bhago;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class Main extends Activity {
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		Intent intent = new Intent(this, SafeRouteActivity.class);
		startActivity(intent);
		return true;
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
}