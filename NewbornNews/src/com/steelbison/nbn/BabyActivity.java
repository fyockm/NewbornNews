package com.steelbison.nbn;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class BabyActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);

		Bundle extras = getIntent().getExtras();

		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent().setClass(this, BabyEatActivity.class);
		spec = tabHost.newTabSpec("eat").setIndicator(
				res.getString(R.string.eat),
				res.getDrawable(R.drawable.ic_tab_baby_test))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, BabySleepActivity.class);
		spec = tabHost.newTabSpec("sleep").setIndicator(
				res.getString(R.string.sleep),
				res.getDrawable(R.drawable.ic_tab_baby_test))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, BabyPoopActivity.class);
		spec = tabHost.newTabSpec("poop").setIndicator(
				res.getString(R.string.poop),
				res.getDrawable(R.drawable.ic_tab_baby_test))
				.setContent(intent);
		tabHost.addTab(spec);

		int tab = 0;

		try {
			tab = Integer.parseInt(extras.getString("tab"));
		} catch (NumberFormatException nfe) {
		}

		tabHost.setCurrentTab(tab);
	}
}
