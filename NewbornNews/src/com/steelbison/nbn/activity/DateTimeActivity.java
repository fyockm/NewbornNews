package com.steelbison.nbn.activity;

import java.text.DateFormat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.steelbison.widget.DateTimeButton;

public class DateTimeActivity extends Activity {
	protected DateTimeButton mStartDate;
	protected DateTimeButton mStopDate;
	protected DateTimeButton mStartTime;
	protected DateTimeButton mStopTime;

	protected DateFormat mDateFormat;
	protected DateFormat mTimeFormat;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDateFormat = android.text.format.DateFormat
				.getDateFormat(getApplicationContext());
		mTimeFormat = android.text.format.DateFormat
				.getTimeFormat(getApplicationContext());
	}

	protected void setStartButtons() {
		mStartDate = getDateTimeButton(R.id.startDate, mDateFormat);
		mStartTime = getDateTimeButton(R.id.startTime, mTimeFormat);
	}

	protected void setStopButtons() {
		mStopDate = getDateTimeButton(R.id.stopDate, mDateFormat);
		mStopTime = getDateTimeButton(R.id.stopTime, mTimeFormat);
	}

	private DateTimeButton getDateTimeButton(int id, DateFormat dateFormat) {
		DateTimeButton dtb = new DateTimeButton((Button) findViewById(id),
				dateFormat);
		dtb.getButton().setOnClickListener(getOnClickListener(id));
		dtb.setButtonText();
		return dtb;
	}

	private View.OnClickListener getOnClickListener(final int id) {
		View.OnClickListener ocl = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(id);
			}
		};
		return ocl;
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case R.id.startDate:
			return getDatePickerDialog(mStartDate);
		case R.id.stopDate:
			return getDatePickerDialog(mStopDate);
		case R.id.startTime:
			return getTimePickerDialog(mStartTime);
		case R.id.stopTime:
			return getTimePickerDialog(mStopTime);
		}
		return null;
	}

	private DatePickerDialog getDatePickerDialog(final DateTimeButton dtb) {
		DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				dtb.setCal(year, monthOfYear, dayOfMonth);
				dtb.setButtonText();
			}
		};
		return new DatePickerDialog(this, odsl, dtb.getYear(), dtb.getMonth(),
				dtb.getDay());
	}

	private TimePickerDialog getTimePickerDialog(final DateTimeButton dtb) {
		TimePickerDialog.OnTimeSetListener otsl = new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				dtb.setCal(hourOfDay, minute);
				dtb.setButtonText();
			}
		};
		return new TimePickerDialog(this, otsl, dtb.getHour(), dtb.getMinute(),
				false);
	}
}