package com.steelbison.nbn.activity;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DateTimeActivity extends Activity {
	private DateTimeButton mStartDate;
	private DateTimeButton mStopDate;
	private DateTimeButton mStartTime;
	private DateTimeButton mStopTime;

	private class DateTimeButton {
		public Button button;
		public DateFormat dateFormat;
		public Calendar cal;

		public static final int DATE = 0;
		public static final int TIME = 1;

		public DateTimeButton(final int id, int type) {
			this.button = (Button) findViewById(id);
			this.button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showDialog(id);
				}
			});
			setButtonText();

			switch (type) {
			case DATE:
				this.dateFormat = android.text.format.DateFormat
						.getDateFormat(getApplicationContext());
			case TIME:
				this.dateFormat = android.text.format.DateFormat
						.getTimeFormat(getApplicationContext());
			}

			this.cal = Calendar.getInstance();
		}

		public void setButtonText() {
			this.button.setText(dateFormat.format(cal.getTime()));
		}
	}

	protected void setStartButtons() {
		mStartDate = new DateTimeButton(R.id.startDate, DateTimeButton.DATE);
		mStartTime = new DateTimeButton(R.id.startTime, DateTimeButton.TIME);
	}

	protected void setStopButtons() {
		mStopDate = new DateTimeButton(R.id.stopDate, DateTimeButton.DATE);
		mStopTime = new DateTimeButton(R.id.stopTime, DateTimeButton.TIME);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case R.id.startDate:
			return getDatePickerDialog(mStartDate);
		case R.id.startTime:
			return getTimePickerDialog(mStartTime);
		case R.id.stopDate:
			return getDatePickerDialog(mStopDate);
		case R.id.stopTime:
			return getTimePickerDialog(mStopTime);
		}
		return null;
	}

	private DatePickerDialog getDatePickerDialog(final DateTimeButton dtb) {
		DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				dtb.cal.set(year, monthOfYear, dayOfMonth);
				dtb.setButtonText();
			}
		};
		return new DatePickerDialog(this, odsl, dtb.cal.get(Calendar.YEAR),
				dtb.cal.get(Calendar.MONTH), dtb.cal.get(Calendar.DAY_OF_MONTH));
	}

	private TimePickerDialog getTimePickerDialog(final DateTimeButton dtb) {
		TimePickerDialog.OnTimeSetListener otsl = new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				dtb.cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
				dtb.cal.set(Calendar.MINUTE, minute);
				dtb.setButtonText();
			}
		};
		return new TimePickerDialog(this, otsl, dtb.cal
				.get(Calendar.HOUR_OF_DAY), dtb.cal.get(Calendar.MINUTE), false);
	}
}