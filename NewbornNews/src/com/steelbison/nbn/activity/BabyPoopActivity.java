package com.steelbison.nbn.activity;

import android.os.Bundle;
import android.view.View;

import com.steelbison.widget.DateTimeButton;

public class BabyPoopActivity extends DateTimeActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poop);

		mStartDate = (DateTimeButton) findViewById(R.id.startDate);
		mStartDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_START);
			}
		});

		mStartTime = (DateTimeButton) findViewById(R.id.startTime);
		mStartTime.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(TIME_DIALOG_START);
			}
		});

		getCurrentTime();
		updateButton(mStartDate, mDateFormat);
		updateButton(mStartTime, mTimeFormat);
	}
}