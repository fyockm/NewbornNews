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
	public boolean breast;
	public boolean bottle;
	public int side; // LEFT, RIGHT
	public int oz;
	public boolean wet;
	public boolean dirty;
	public String note;

	public static final int EAT = 1;
	public static final int SLEEP = 2;
	public static final int POOP = 3;
	public static final int MEDS = 4;
	public static final int PUMP = 5;

	public static final int LEFT = 1;
	public static final int RIGHT = 2;

	public void getNews(Cursor cursor) {

		try {
			id = cursor.getLong(cursor.getColumnIndexOrThrow(NbnDbAdapter._ID));
			type = cursor.getInt(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.TYPE));
			start = cursor.getLong(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.START));
			stop = cursor.getLong(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.STOP));
			breast = cursor.getInt(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.BREAST)) > 0;
			bottle = cursor.getInt(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.BOTTLE)) > 0;
			side = cursor.getInt(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.SIDE));
			oz = cursor.getInt(cursor.getColumnIndexOrThrow(NbnDbAdapter.OZ));
			wet = cursor.getInt(cursor.getColumnIndexOrThrow(NbnDbAdapter.WET)) > 0;
			dirty = cursor.getInt(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.DIRTY)) > 0;
			note = cursor.getString(cursor
					.getColumnIndexOrThrow(NbnDbAdapter.NOTE));
		} catch (IllegalArgumentException iae) {
			Log.w(News.class.getName(), this.toString(), iae);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("News [bottle=");
		builder.append(bottle);
		builder.append(", breast=");
		builder.append(breast);
		builder.append(", dirty=");
		builder.append(dirty);
		builder.append(", id=");
		builder.append(id);
		builder.append(", note=");
		builder.append(note);
		builder.append(", oz=");
		builder.append(oz);
		builder.append(", side=");
		builder.append(side);
		builder.append(", start=");
		builder.append(start);
		builder.append(", stop=");
		builder.append(stop);
		builder.append(", type=");
		builder.append(type);
		builder.append(", wet=");
		builder.append(wet);
		builder.append("]");
		return builder.toString();
	}
}
