package com.steelbison.nbn.activity;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.steelbison.nbn.dao.NbnDbAdapter;
import com.steelbison.nbn.dao.News;

public class DateTimeActivity extends Activity {
	protected DateTimeButton mStart;
	protected DateTimeButton mStop;
	protected EditText mNote;

	public DateFormat mDateFormat;
	public DateFormat mTimeFormat;

	protected NbnDbAdapter mDbAdapter;
	protected News mNews;
	protected Long mRowId;

	/*
	 * Class for a combined Date Time Button
	 */
	protected class DateTimeButton {
		public Button dateButton;
		public Button timeButton;
		public Calendar cal;

		public DateTimeButton(final int dateId, final int timeId) {
			this.dateButton = (Button) findViewById(dateId);
			this.timeButton = (Button) findViewById(timeId);
			this.cal = Calendar.getInstance();
			setButtonText();
		}

		public void setButtonText() {
			setDateText();
			setTimeText();
		}

		public void setDateText() {
			this.dateButton.setText(mDateFormat.format(cal.getTime()));
		}

		public void setTimeText() {
			this.timeButton.setText(mTimeFormat.format(cal.getTime()));
		}
	}

	protected void setStartButtons() {
		mStart = new DateTimeButton(R.id.startDate, R.id.startTime);
	}

	protected void setStopButtons() {
		mStop = new DateTimeButton(R.id.stopDate, R.id.stopTime);
	}

	protected void populateNews() {
		if (mRowId != null) {
			Cursor cursor = mDbAdapter.fetchNews(mRowId);
			startManagingCursor(cursor);

			mNews = new News();
			mNews.getNews(cursor);
		}
	}

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		mDateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
		mTimeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext());

		mDbAdapter = new NbnDbAdapter(this);
		mDbAdapter.open();

		Bundle extras = getIntent().getExtras();
		mRowId = (bundle == null) ? null : (Long) bundle.getSerializable(NbnDbAdapter._ID);
		if (extras != null) {
			mRowId = extras.getLong(NbnDbAdapter._ID);
		}
	}

	@Override
	public Dialog onCreateDialog(int id) {
		switch (id) {
		case R.id.startDate:
			return getDatePickerDialog(mStart);
		case R.id.startTime:
			return getTimePickerDialog(mStart);
		case R.id.stopDate:
			return getDatePickerDialog(mStop);
		case R.id.stopTime:
			return getTimePickerDialog(mStop);
		}
		return null;
	}

	public void onDateTimeClick(View v) {
		showDialog(v.getId());
	}

	public void onNowClick(View v) {
		switch (v.getId()) {
		case R.id.startNow:
			setStartButtons();
			break;
		case R.id.stopNow:
			setStopButtons();
			break;
		}
	}

	public void onConfirm(View view) {
		if (mNews != null && mDbAdapter.createNews(mNews) > 0) {
			setResult(RESULT_OK);
			finish();
		} else {
			setResult(RESULT_CANCELED);
		}
	}

	private DatePickerDialog getDatePickerDialog(final DateTimeButton dtb) {
		DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				dtb.cal.set(year, monthOfYear, dayOfMonth);
				dtb.setDateText();
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
				dtb.setTimeText();
			}
		};
		return new TimePickerDialog(this, otsl, dtb.cal.get(Calendar.HOUR_OF_DAY),
				dtb.cal.get(Calendar.MINUTE), false);
	}
}