package com.steelbison.nbn.activity;

import android.os.Bundle;

public class MomMedsActivity extends DateTimeActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meds);

		setStartButtons();
	}
}
