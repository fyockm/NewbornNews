package com.steelbison.nbn.activity;

import com.steelbison.nbn.dao.News;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class BabyPoopActivity extends DateTimeActivity {

	private CheckBox mWet;
	private CheckBox mDirty;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poop);

		setStartButtons();
		mWet = (CheckBox) findViewById(R.id.wet);
		mDirty = (CheckBox) findViewById(R.id.dirty);
		mNote = (EditText) findViewById(R.id.note);

		populateNews();
		if (mNews != null) {
			mStart.cal.setTimeInMillis(mNews.start);
			mStart.setButtonText();
			mWet.setChecked(mNews.wet);
			mDirty.setChecked(mNews.dirty);
			mNote.setText(mNews.note);
		}
	}

	public void onConfirm(View v) {
		mNews = new News();
		mNews.type = News.POOP;
		mNews.start = mStart.cal.getTimeInMillis();
		mNews.wet = mWet.isChecked();
		mNews.dirty = mDirty.isChecked();
		mNews.note = mNote.getText().toString();

		super.onConfirm(v);
	}
}