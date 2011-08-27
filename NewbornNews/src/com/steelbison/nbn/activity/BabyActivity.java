package com.steelbison.nbn.activity;

import android.os.Bundle;

public class BabyActivity extends NbnTabActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTab(BabyEatActivity.class, R.string.eat, R.drawable.ic_tab_bottle);
		setTab(BabySleepActivity.class, R.string.sleep,
				R.drawable.ic_tab_bottle);
		setTab(BabyPoopActivity.class, R.string.poop,
				R.drawable.ic_tab_bottle);

		setCurrentTab();
	}
}