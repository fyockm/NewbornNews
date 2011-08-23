package com.steelbison.nbn;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MomActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);

		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent().setClass(this, MomMedsActivity.class);
		spec = tabHost.newTabSpec("meds").setIndicator(
				res.getString(R.string.meds)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, MomPumpActivity.class);
		spec = tabHost.newTabSpec("pump").setIndicator(
				res.getString(R.string.pump)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}
}
