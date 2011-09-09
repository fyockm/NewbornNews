package com.steelbison.nbn.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.steelbison.nbn.dao.NbnDbAdapter;
import com.steelbison.nbn.dao.News;

public class LogActivity extends Activity {

	private EditText mTitleText;
	private EditText mBodyText;
	private Long mRowId;
	private NbnDbAdapter mDbAdapter;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		mDbAdapter = new NbnDbAdapter(this);
		mDbAdapter.open();
		setContentView(R.layout.log);

		mTitleText = (EditText) findViewById(R.id.title);
		mBodyText = (EditText) findViewById(R.id.body);

		Button confirmButton = (Button) findViewById(R.id.confirm);

		Bundle extras = getIntent().getExtras();
		mRowId = (bundle == null) ? null : (Long) bundle
				.getSerializable(NbnDbAdapter._ID);
		if (extras != null) {
			mRowId = extras.getLong(NbnDbAdapter._ID);
		}
		populateFields();
		confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_OK);
				finish();
			}

		});
	}

	private void populateFields() {
		if (mRowId != null) {
			Cursor cursor = mDbAdapter.fetchNews(mRowId);
			startManagingCursor(cursor);

			// String category = cursor.getString(cursor
			// .getColumnIndexOrThrow(News.START));
			// for (int i = 0; i < mCategory.getCount(); i++) {
			//
			// String s = (String) mCategory.getItemAtPosition(i);
			// Log.e(null, s + " " + category);
			// if (s.equalsIgnoreCase(category)) {
			// mCategory.setSelection(i);
			// }
			// }

			mTitleText.setText(cursor.getInt(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.TYPE)));
			mBodyText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.NOTE)));
		}
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(NbnDbAdapter._ID, mRowId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	private void saveState() {
		// String category = (String) mCategory.getSelectedItem();
		int summary = Integer.parseInt(mTitleText.getText().toString());
		String description = mBodyText.getText().toString();
		News news = new News();
		news.type = summary;
		news.note = description;

		if (mRowId == null) {
			long id = mDbAdapter.createNews(news);
			if (id > 0) {
				mRowId = id;
			}
		} else {
			mDbAdapter.updateNews(news);
		}
	}
}
