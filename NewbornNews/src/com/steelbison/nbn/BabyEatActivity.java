package com.steelbison.nbn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BabyEatActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textview = new TextView(this);
        textview.setText("This is the Eat tab");
        setContentView(textview);
	}
}
