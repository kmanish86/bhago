package com.bhago;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bhago.gamemodel.MyLocation;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class WaterOverlay extends ItemizedOverlay<OverlayItem> {

	private SafeRouteActivity mActivity;
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	MyLocation location = null;

	public WaterOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	public WaterOverlay(Drawable defaultMarker, SafeRouteActivity activity, MyLocation loc) {
		super(boundCenterBottom(defaultMarker));
		mActivity = activity;
		location = loc;
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		
		mActivity.game.move(location);
		mActivity.renderGame();
		
		return true;
	}

}
