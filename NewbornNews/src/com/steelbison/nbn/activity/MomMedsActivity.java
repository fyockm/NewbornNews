package com.steelbison.nbn.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.steelbison.nbn.dao.News;

public class MomMedsActivity extends DateTimeActivity {

	private Spinner mMeds;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meds);

		setStartButtons();
		mMeds = (Spinner) findViewById(R.id.meds);
		mNote = (EditText) findViewById(R.id.note);

		// TODO: get meds from settings
		final ArrayList<CharSequence> al = new ArrayList<CharSequence>();
		al.add("Med1");
		al.add("Med2");
		al.add("Med3");
		ArrayAdapter<CharSequence> aa = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item, al);
		aa
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mMeds.setAdapter(aa);

		populateNews();
		if (mNews != null) {
			mStart.cal.setTimeInMillis(mNews.start);
			mStart.setButtonText();
			mMeds.setSelection(al.indexOf(mNews.meds));
			mNote.setText(mNews.note);
		}
	}

	public void onConfirm(View v) {
		mNews = new News();
		mNews.type = News.MEDS;
		mNews.start = mStart.cal.getTimeInMillis();
		mNews.meds = (String) mMeds.getSelectedItem();
		mNews.note = mNote.getText().toString();

		super.onConfirm(v);
	}
}
