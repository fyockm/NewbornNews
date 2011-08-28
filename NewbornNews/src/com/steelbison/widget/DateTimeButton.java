package com.steelbison.widget;

import java.text.DateFormat;
import java.util.Calendar;

import android.widget.Button;

public class DateTimeButton {

	private Button button;
	private Calendar cal;
	private DateFormat dateFormat;

	public DateTimeButton(Button button, DateFormat dateFormat) {
		this.button = button;
		this.dateFormat = dateFormat;
		this.cal = Calendar.getInstance();
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	public void setCal(int year, int month, int day) {
		this.cal.set(year, month, day);
	}

	// this could be dangerous - don't get confused
	public void setCal(int hour, int minute) {
		this.cal.set(getYear(), getMonth(), getDay(), hour, minute);
	}

	public int getYear() {
		return cal.get(Calendar.YEAR);
	}

	public int getMonth() {
		return cal.get(Calendar.MONTH);
	}

	public int getDay() {
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public int getHour() {
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute() {
		return cal.get(Calendar.MINUTE);
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setButtonText() {
		button.setText(dateFormat.format(cal.getTime()));
	}
}
