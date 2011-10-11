package com.steelbison.nbn.dao;

import android.database.Cursor;
import android.util.Log;

/**
 * Definition of news record
 */
public class News {

	public long id;
	public int type; // EAT, SLEEP, POOP, MEDS, PUMP
	public long start;
	public long stop;
	public int side; // LEFT, RIGHT, BOTTLE
	public float amt;
	public boolean wet;
	public boolean dirty;
	public String meds;
	public String note;

	// News Types
	public static final int EAT = 1;
	public static final int SLEEP = 2;
	public static final int POOP = 3;
	public static final int MEDS = 4;
	public static final int PUMP = 5;

	// Eat Types
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int BOTTLE = 3;

	public void getNews(Cursor cursor) {
		try {
			id = cursor.getLong(cursor.getColumnIndexOrThrow(NbnDbAdapter._ID));
			type = cursor.getInt(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.TYPE));
			start = cursor.getLong(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.START));
			stop = cursor.getLong(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.STOP));
			side = cursor.getInt(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.SIDE));
			amt = cursor.getFloat(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.AMT));
			wet = cursor.getInt(cursor.getColumnIndexOrThrow(NbnDbAdapter.WET)) > 0;
			dirty = cursor.getInt(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.DIRTY)) > 0;
			meds = cursor.getString(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.MEDS));
			note = cursor.getString(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.NOTE));
		} catch (IllegalArgumentException iae) {
			Log.w(News.class.getName(), this.toString(), iae);
		}
	}
}
