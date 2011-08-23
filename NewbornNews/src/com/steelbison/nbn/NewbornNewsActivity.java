package com.steelbison.nbn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewbornNewsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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

	public void onLogClick(View v) {
		if (v.getId() == R.id.log) {
			startActivity(new Intent(getApplicationContext(), LogActivity.class));
		}
	}
}