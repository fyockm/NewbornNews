package com.steelbison.nbn.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.steelbison.nbn.dao.News;

public class BabyEatActivity extends DateTimeActivity {

	private CheckBox mBottle;
	private CheckBox mBreast;
	private RadioGroup mBreastSide;
	private EditText mOunces;
	private EditText mNote;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eat);

		setStartButtons();
		setStopButtons();
		// setConfirmButton();

		mBottle = (CheckBox) findViewById(R.id.bottle);
		mBreast = (CheckBox) findViewById(R.id.breast);
		mBreastSide = (RadioGroup) findViewById(R.id.breastSide);
		mOunces = (EditText) findViewById(R.id.ounces);
		mNote = (EditText) findViewById(R.id.note);

		populateFields();

		if (mRowId != null) {
			mBottle.setChecked(mNews.bottle);
			mBreast.setChecked(mNews.breast);
			switch (mNews.side) {
			case News.LEFT:
				mBreastSide.check(R.id.breastLeft);
				break;
			case News.RIGHT:
				mBreastSide.check(R.id.breastRight);
				break;
			}
			if (mNews.oz > 0) {
				mOunces.setText(String.valueOf(mNews.oz));
			}
			mNote.setText(mNews.note);
		}
	}

	public void onConfirm(View view) {
		mNews = new News();
		mNews.type = News.EAT;
		mNews.start = mStart.cal.getTimeInMillis();
		mNews.stop = mStop.cal.getTimeInMillis();
		mNews.bottle = mBottle.isChecked();
		mNews.breast = mBreast.isChecked();
		switch (mBreastSide.getCheckedRadioButtonId()) {
		case R.id.breastLeft:
			mNews.side = News.LEFT;
			break;
		case R.id.breastRight:
			mNews.side = News.RIGHT;
			break;
		}
		try {
			mNews.oz = Integer.parseInt(mOunces.getText().toString());
		} catch (NumberFormatException nfe) {
			Log.w(BabyEatActivity.class.getName(), nfe);
		}
		mNews.note = mNote.getText().toString();

		super.onConfirm(view);
	}
}
