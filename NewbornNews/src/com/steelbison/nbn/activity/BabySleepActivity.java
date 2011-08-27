package com.steelbison.nbn.activity;

import android.os.Bundle;
import android.view.View;

import com.steelbison.widget.DateTimeButton;

public class BabySleepActivity extends DateTimeActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleep);

		mStartDate = (DateTimeButton) findViewById(R.id.startDate);
		mStartDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_START);
			}
		});

		mStopDate = (DateTimeButton) findViewById(R.id.stopDate);
		mStopDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_STOP);
			}
		});

		mStartTime = (DateTimeButton) findViewById(R.id.startTime);
		mStartTime.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(TIME_DIALOG_START);
			}
		});

		mStopTime = (DateTimeButton) findViewById(R.id.stopTime);
		mStopTime.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(TIME_DIALOG_STOP);
			}
		});

		getCurrentTime();
		updateButton(mStartDate, mDateFormat);
		updateButton(mStartTime, mTimeFormat);
		updateButton(mStopDate, mDateFormat);
		updateButton(mStopTime, mTimeFormat);
	}
}