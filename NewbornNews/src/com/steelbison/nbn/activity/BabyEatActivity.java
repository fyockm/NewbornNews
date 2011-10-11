package com.steelbison.nbn.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.steelbison.nbn.dao.News;

public class BabyEatActivity extends DateTimeActivity {

	private RadioGroup mSide;
	private EditText mAmt;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eat);

		setStartButtons();
		setStopButtons();
		mSide = (RadioGroup) findViewById(R.id.side);
		mAmt = (EditText) findViewById(R.id.ounces);
		mNote = (EditText) findViewById(R.id.note);

		populateNews();
		if (mNews != null) {
			mStart.cal.setTimeInMillis(mNews.start);
			mStart.setButtonText();
			mStop.cal.setTimeInMillis(mNews.stop);
			mStop.setButtonText();

			switch (mNews.side) {
			case News.LEFT:
				mSide.check(R.id.breastLeft);
				break;
			case News.RIGHT:
				mSide.check(R.id.breastRight);
				break;
			case News.BOTTLE:
				mSide.check(R.id.bottle);
				break;
			}
			if (mNews.amt > 0) {
				mAmt.setText(String.valueOf(mNews.amt));
			}
			mNote.setText(mNews.note);
		}
	}

	public void onConfirm(View v) {
		mNews = new News();
		mNews.type = News.EAT;
		mNews.start = mStart.cal.getTimeInMillis();
		mNews.stop = mStop.cal.getTimeInMillis();
		switch (mSide.getCheckedRadioButtonId()) {
		case R.id.breastLeft:
			mNews.side = News.LEFT;
			break;
		case R.id.breastRight:
			mNews.side = News.RIGHT;
			break;
		case R.id.bottle:
			mNews.side = News.BOTTLE;
			break;
		}
		try {
			mNews.amt = Integer.parseInt(mAmt.getText().toString());
		} catch (NumberFormatException nfe) {
			Log.w(BabyEatActivity.class.getName(), nfe);
		}
		mNews.note = mNote.getText().toString();

		super.onConfirm(v);
	}
}
