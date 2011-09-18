package com.steelbison.nbn.activity;

import java.text.DateFormat;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.SimpleCursorAdapter.CursorToStringConverter;

import com.steelbison.nbn.dao.NbnDbAdapter;
import com.steelbison.nbn.dao.News;

public class NewbornNewsActivity extends ListActivity {

	private NbnDbAdapter dbHelper;
	private static final int ACTIVITY_EDIT = 1;
	private Cursor cursor;

	private DateFormat mDateFormat;
	private DateFormat mTimeFormat;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mDateFormat = android.text.format.DateFormat
				.getDateFormat(getApplicationContext());
		mTimeFormat = android.text.format.DateFormat
				.getTimeFormat(getApplicationContext());

		this.getListView().setDividerHeight(2);
		dbHelper = new NbnDbAdapter(this);
		dbHelper.open();
		fillData();
	}

	public void onBabyClick(View v) {
		Intent intent = new Intent(getApplicationContext(), BabyActivity.class);
		switch (v.getId()) {
		case R.id.eat:
			intent.putExtra("tab", 0);
			break;
		case R.id.sleep:
			intent.putExtra("tab", 1);
			break;
		case R.id.poop:
			intent.putExtra("tab", 2);
			break;
		}
		startActivity(intent);
	}

	public void onMomClick(View v) {
		Intent intent = new Intent(getApplicationContext(), MomActivity.class);
		switch (v.getId()) {
		case R.id.meds:
			intent.putExtra("tab", 0);
			break;
		case R.id.pump:
			intent.putExtra("tab", 1);
			break;
		}
		startActivity(intent);
	}

	// ListView and view (row) on which was clicked, position and
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i;
		// switch on news type
		int type = Integer.parseInt(((TextView) v.findViewById(R.id.type))
				.getText().toString());
		switch (type) {
		case News.EAT:
			i = new Intent(this, BabyEatActivity.class);
			break;
		case News.SLEEP:
			i = new Intent(this, BabySleepActivity.class);
			break;
		case News.POOP:
			i = new Intent(this, BabyPoopActivity.class);
			break;
		case News.MEDS:
			i = new Intent(this, MomMedsActivity.class);
			break;
		case News.PUMP:
			i = new Intent(this, MomPumpActivity.class);
			break;
		default:
			i = new Intent();
		}

		i.putExtra(NbnDbAdapter._ID, id);

		// Activity returns an result if called with startActivityForResult
		startActivityForResult(i, ACTIVITY_EDIT);
	}

	// Called with the result of the other activity requestCode was the origin
	// request code send to the activity resultCode is the return code, 0 is
	// everything is ok intend can be use to get some data from the caller
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		fillData();
	}

	private void fillData() {
		cursor = dbHelper.fetchAllNews();
		startManagingCursor(cursor);

		String[] from = NbnDbAdapter.getNewsListColumns();
		int[] to = new int[] { R.id.type, R.id.start, R.id.stop, R.id.note };

		// Now create an array adapter and set it to display using our rows
		SimpleCursorAdapter sca = new SimpleCursorAdapter(this,
				R.layout.log_row, cursor, from, to);
		setListAdapter(sca);

		// Set the CursorToStringConverter, to provide the labels for the
		// choices to be displayed in the TextView.
		sca.setCursorToStringConverter(new CursorToStringConverter() {
			public String convertToString(Cursor cursor) {
				// Get the label for this row out of the "state" column
				final long start = cursor.getLong(cursor
						.getColumnIndex(NbnDbAdapter.START));
				final String date = mDateFormat.format(start);
				final String time = mTimeFormat.format(start);
				return date + " " + time;
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
