package com.steelbison.nbn.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.steelbison.nbn.dao.NbnDb;
import com.steelbison.nbn.dao.NbnDbAdapter;

public class NewbornNewsActivity extends ListActivity {

	private NbnDbAdapter dbHelper;
	private static final int ACTIVITY_EDIT = 1;
	private Cursor cursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.getListView().setDividerHeight(2);
		dbHelper = new NbnDbAdapter(this);
		dbHelper.open();
		fillData();
	}

	public void onBabyClick(View v) {
		Intent intent = new Intent(getApplicationContext(), BabyActivity.class);
		switch (v.getId()) {
		case R.id.eat:
			intent.putExtra("tab", "0");
			break;
		case R.id.sleep:
			intent.putExtra("tab", "1");
			break;
		case R.id.poop:
			intent.putExtra("tab", "2");
			break;
		}
		startActivity(intent);
	}

	public void onMomClick(View v) {
		Intent intent = new Intent(getApplicationContext(), MomActivity.class);
		switch (v.getId()) {
		case R.id.meds:
			intent.putExtra("tab", "0");
			break;
		case R.id.pump:
			intent.putExtra("tab", "1");
			break;
		}
		startActivity(intent);
	}

	// ListView and view (row) on which was clicked, position and
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, LogActivity.class);
		i.putExtra(NbnDb._ID, id);

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

		String[] from = new String[] { NbnDb.TYPE };
		int[] to = new int[] { R.id.news };

		// Now create an array adapter and set it to display using our rows
		SimpleCursorAdapter log = new SimpleCursorAdapter(this,
				R.layout.log_row, cursor, from, to);
		setListAdapter(log);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
