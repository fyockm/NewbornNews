package com.steelbison.nbn.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;

/*
 * Parent activity for any of the Newborn News tab activites
 */
public class NbnTabActivity extends TabActivity {

	protected Bundle extras;
	protected TabHost tabHost;
	protected Resources res;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);

		extras = getIntent().getExtras();
		res = getResources();
		tabHost = getTabHost();
	}

	protected void setTab(Class<?> actClass, int titleId, int drawId) {
		Intent intent = new Intent().setClass(this, actClass);
		String title = res.getString(titleId);
		Drawable draw = res.getDrawable(drawId);
		tabHost.addTab(tabHost.newTabSpec(title).setIndicator(title, draw).setContent(intent));
	}

	protected void setCurrentTab() {
		tabHost.setCurrentTab(extras.getInt("tab", 0));
	}
}