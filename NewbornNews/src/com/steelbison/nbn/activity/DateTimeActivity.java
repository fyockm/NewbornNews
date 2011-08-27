package com.steelbison.nbn.activity;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.steelbison.widget.DateTimeButton;

public class DateTimeActivity extends Activity {
	protected DateTimeButton mStartDate;
	protected DateTimeButton mStopDate;
	protected DateTimeButton mStartTime;
	protected DateTimeButton mStopTime;

	// date and time
	protected int mYear;
	protected int mMonth;
	protected int mDay;
	protected int mHour;
	protected int mMinute;

	protected DateFormat mDateFormat;
	protected DateFormat mTimeFormat;

	static final int TIME_DIALOG_START = 0;
	static final int TIME_DIALOG_STOP = 1;
	static final int DATE_DIALOG_START = 2;
	static final int DATE_DIALOG_STOP = 3;

	// get the current time
	protected void getCurrentTime() {
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);

		mDateFormat = android.text.format.DateFormat
				.getDateFormat(getApplicationContext());
		mTimeFormat = android.text.format.DateFormat
				.getTimeFormat(getApplicationContext());
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_START:
			return new DatePickerDialog(this, mDateSetStartListener, mYear,
					mMonth, mDay);
		case DATE_DIALOG_STOP:
			return new DatePickerDialog(this, mDateSetStopListener, mYear,
					mMonth, mDay);
		case TIME_DIALOG_START:
			return new TimePickerDialog(this, mTimeSetStartListener, mHour,
					mMinute, false);
		case TIME_DIALOG_STOP:
			return new TimePickerDialog(this, mTimeSetStopListener, mHour,
					mMinute, false);
		}
		return null;
	}

	protected DatePickerDialog.OnDateSetListener mDateSetStartListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateButton(mStartDate, mDateFormat);
		}
	};

	protected DatePickerDialog.OnDateSetListener mDateSetStopListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateButton(mStopDate, mDateFormat);
		}
	};

	protected TimePickerDialog.OnTimeSetListener mTimeSetStartListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateButton(mStartTime, mTimeFormat);
		}
	};

	protected TimePickerDialog.OnTimeSetListener mTimeSetStopListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateButton(mStopTime, mTimeFormat);
		}
	};

	protected void updateButton(Button button, DateFormat dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.set(mYear, mMonth + 1, mDay, mHour, mMinute);
		button.setText(dateFormat.format(cal.getTime()));
	}
}