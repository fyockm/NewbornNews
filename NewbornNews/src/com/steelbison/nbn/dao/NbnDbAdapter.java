package com.steelbison.nbn.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NbnDbAdapter {

	private Context mCtx;
	private SQLiteDatabase mDb;
	private NbnDbHelper mDbHelper;

	public NbnDbAdapter(Context context) {
		this.mCtx = context;
	}

	public NbnDbAdapter open() throws SQLException {
		mDbHelper = new NbnDbHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	/**
	 * Create a new record
	 * 
	 * If the record is successfully created return the new id for that news,
	 * otherwise return a -1 to indicate failure.
	 */
	public long createNews(int type, long start, long stop, String note) {
		ContentValues insertValues = createContentValues(type, start, stop,
				note);
		return mDb.insert(NbnDb.DATABASE_TABLE, null, insertValues);
	}

	/**
	 * Update the News
	 */
	public boolean updateNews(long id, int type, long start, long stop,
			String note) {
		ContentValues updateValues = createContentValues(type, start, stop,
				note);
		return mDb.update(NbnDb.DATABASE_TABLE, updateValues, NbnDb._ID + "="
				+ id, null) > 0;
	}

	/**
	 * Deletes table
	 */
	public boolean deleteNews(long id) {
		return mDb.delete(NbnDb.DATABASE_TABLE, NbnDb._ID + "=" + id, null) > 0;
	}

	/**
	 * Return a Cursor over the list of all news in the mDb
	 * 
	 * @return Cursor over all news
	 */
	public Cursor fetchAllNews() {
		return mDb.query(NbnDb.DATABASE_TABLE, getNewsColumns(), null, null,
				null, null, null);
	}

	/**
	 * Return a Cursor positioned at the defined table
	 */
	public Cursor fetchNews(long id) throws SQLException {
		Cursor mCursor = mDb.query(true, NbnDb.DATABASE_TABLE,
				getNewsColumns(), NbnDb._ID + "=" + id, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private String[] getNewsColumns() {
		return new String[] { NbnDb._ID, NbnDb.TYPE, NbnDb.START, NbnDb.STOP,
				NbnDb.NOTE };
	}

	/**
	 * Create constant values
	 * 
	 * @param category
	 * @param summary
	 * @param description
	 * @return
	 */
	private ContentValues createContentValues(int type, long start, long stop,
			String note) {
		ContentValues values = new ContentValues();
		values.put(NbnDb.TYPE, type);
		values.put(NbnDb.START, start);
		values.put(NbnDb.STOP, stop);
		values.put(NbnDb.NOTE, note);
		return values;
	}
}
