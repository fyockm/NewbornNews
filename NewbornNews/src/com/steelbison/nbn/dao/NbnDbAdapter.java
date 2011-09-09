package com.steelbison.nbn.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class NbnDbAdapter implements BaseColumns {

	private static final String TAG = NbnDbAdapter.class.getName();
	private NbnDbHelper mDbHelper;
	private SQLiteDatabase mDb;
	private Context mCtx;

	private static final String DATABASE_NAME = "newbornnews";
	private static final String DATABASE_TABLE = "news";
	private static final int DATABASE_VERSION = 2;

	// columns
	public static final String TYPE = "type";
	public static final String START = "start";
	public static final String STOP = "stop";
	public static final String BREAST = "breast";
	public static final String BOTTLE = "bottle";
	public static final String SIDE = "side";
	public static final String OZ = "oz";
	public static final String WET = "wet";
	public static final String DIRTY = "dirty";
	public static final String NOTE = "note";

	private static final String DATABASE_CREATE = "CREATE TABLE "
			+ DATABASE_TABLE + " (" + _ID
			+ " INTEGER primary key autoincrement, " + TYPE + " int not null,"
			+ START + " int, " + STOP + " int, " + BREAST + " int, " + BOTTLE
			+ " int, " + SIDE + " int, " + OZ + " int, " + WET + " int, "
			+ DIRTY + " int, " + NOTE + " VARCHAR(50));";

	private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "
			+ DATABASE_NAME;

	// private static final String DEFAULT_SORT_ORDER = "modified DESC";

	private static class NbnDbHelper extends SQLiteOpenHelper {

		public NbnDbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/** Method is called during creation of the database */
		@Override
		public void onCreate(SQLiteDatabase database) {
			database.execSQL(DATABASE_CREATE);
		}

		/**
		 * Method is called during an upgrade of the database, e.g. if you
		 * increase the database version
		 */
		@Override
		public void onUpgrade(SQLiteDatabase database, int oldVersion,
				int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			database.execSQL(DATABASE_DROP);
			onCreate(database);
		}
	}

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
	public long createNews(News news) {
		ContentValues insertValues = createContentValues(news);
		return mDb.insert(DATABASE_TABLE, null, insertValues);
	}

	/**
	 * Update the News
	 */
	public boolean updateNews(News news) {
		ContentValues updateValues = createContentValues(news);
		return mDb.update(DATABASE_TABLE, updateValues, _ID + "=" + news.id,
				null) > 0;
	}

	/**
	 * Deletes table
	 */
	public boolean deleteNews(long id) {
		return mDb.delete(DATABASE_TABLE, _ID + "=" + id, null) > 0;
	}

	/**
	 * Return a Cursor over the list of all news in the mDb
	 * 
	 * @return Cursor over all news
	 */
	public Cursor fetchAllNews() {
		return mDb.query(DATABASE_TABLE, getNewsColumns(), null, null, null,
				null, null);
	}

	/**
	 * Return a Cursor positioned at the defined table
	 */
	public Cursor fetchNews(long id) throws SQLException {
		Cursor mCursor = mDb.query(true, DATABASE_TABLE, getNewsColumns(), _ID
				+ "=" + id, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private String[] getNewsColumns() {
		return new String[] { _ID, TYPE, START, STOP, BREAST, BOTTLE, SIDE, OZ,
				WET, DIRTY, NOTE };
	}

	/**
	 * Create constant values
	 * 
	 * @param category
	 * @param summary
	 * @param description
	 * @return
	 */
	private ContentValues createContentValues(News news) {
		ContentValues values = new ContentValues();
		values.put(TYPE, news.type);
		values.put(START, news.start);
		values.put(STOP, news.stop);
		values.put(BREAST, news.breast);
		values.put(BOTTLE, news.bottle);
		values.put(SIDE, news.side);
		values.put(OZ, news.oz);
		values.put(WET, news.wet);
		values.put(DIRTY, news.dirty);
		values.put(NOTE, news.note);
		return values;
	}
}
