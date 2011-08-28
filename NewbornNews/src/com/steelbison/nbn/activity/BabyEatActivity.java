package com.steelbison.nbn.activity;

import android.os.Bundle;

public class BabyEatActivity extends DateTimeActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eat);

		setStartButtons();
		setStopButtons();
	}
}
