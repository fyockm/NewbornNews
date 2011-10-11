package com.steelbison.nbn.activity;

import com.steelbison.nbn.dao.News;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BabySleepActivity extends DateTimeActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleep);

		setStartButtons();
		setStopButtons();
		mNote = (EditText) findViewById(R.id.note);

		populateNews();
		if (mNews != null) {
			mStart.cal.setTimeInMillis(mNews.start);
			mStart.setButtonText();
			mNote.setText(mNews.note);
		}
	}

	public void onConfirm(View v) {
		mNews = new News();
		mNews.type = News.SLEEP;
		mNews.start = mStart.cal.getTimeInMillis();
		mNews.stop = mStop.cal.getTimeInMillis();
		mNews.note = mNote.getText().toString();

		super.onConfirm(v);
	}
}
