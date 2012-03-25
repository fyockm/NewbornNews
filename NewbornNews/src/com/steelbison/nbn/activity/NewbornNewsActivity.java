package com.steelbison.nbn.activity;

import java.text.DateFormat;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

import com.steelbison.nbn.dao.NbnDbAdapter;
import com.steelbison.nbn.dao.News;

/*
 * Main activity for the Newborn News application
 */
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

		mDateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
		mTimeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext());

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
		Intent intent;
		// switch on news type
		String type = ((TextView) v.findViewById(R.id.type)).getText().toString();
		if (type.equals(News.getTypeAsString(News.EAT))) {
			intent = new Intent(this, BabyEatActivity.class);
		} else if (type.equals(News.getTypeAsString(News.SLEEP))) {
			intent = new Intent(this, BabySleepActivity.class);
		} else if (type.equals(News.getTypeAsString(News.POOP))) {
			intent = new Intent(this, BabyPoopActivity.class);
		} else if (type.equals(News.getTypeAsString(News.MEDS))) {
			intent = new Intent(this, MomMedsActivity.class);
		} else if (type.equals(News.getTypeAsString(News.PUMP))) {
			intent = new Intent(this, MomPumpActivity.class);
		} else {
			intent = new Intent();
		}

		intent.putExtra(NbnDbAdapter._ID, id);

		// Activity returns an result if called with startActivityForResult
		startActivityForResult(intent, ACTIVITY_EDIT);
	}

	// Called with the result of the other activity requestCode was the origin
	// request code send to the activity resultCode is the return code, 0 is
	// everything is ok intend can be use to get some data from the caller
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		fillData();
	}

	private void fillData() {
		cursor = dbHelper.fetchAllNews();
		startManagingCursor(cursor);

		String[] from = NbnDbAdapter.getNewsListColumns();
		int[] to = new int[] { R.id.type, R.id.start, R.id.stop, R.id.note };

		// Now create an array adapter and set it to display using our rows
		SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.log_row, cursor, from, to);
		setListAdapter(sca);

		sca.setViewBinder(new ViewBinder() {
			public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
				switch (columnIndex) {
				case 1:
					int type = cursor.getInt(columnIndex);
					String typeStr = News.getTypeAsString(type);
					((TextView) view).setText(typeStr);
					return true;
				case 2:
				case 3:
					long dateTime = cursor.getLong(columnIndex);
					if (dateTime > 0) {
						String dateTimeStr;
						if (columnIndex == 2) {
							dateTimeStr = mDateFormat.format(dateTime) + " "
									+ mTimeFormat.format(dateTime);
						} else {
							dateTimeStr = " - " + mTimeFormat.format(dateTime);
						}
						((TextView) view).setText(dateTimeStr);
					} else {
						((TextView) view).setText("");
					}
					return true;
				default:
					return false;
				}
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
