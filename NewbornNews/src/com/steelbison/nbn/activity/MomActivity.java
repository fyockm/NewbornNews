package com.steelbison.nbn.activity;

import android.os.Bundle;

public class MomActivity extends NbnTabActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTab(MomMedsActivity.class, R.string.meds, R.drawable.ic_tab_meds);
		setTab(MomPumpActivity.class, R.string.pump, R.drawable.ic_tab_meds);

		setCurrentTab();
	}
}
