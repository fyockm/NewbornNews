package com.steelbison.nbn.dao;

import android.provider.BaseColumns;

/**
 * Convenience definitions for NbnDbAdapter
 */
public interface NbnDb extends BaseColumns {

	/**
	 * News table
	 */
	public static final String DATABASE_NAME = "newbornnews";
	public static final String DATABASE_TABLE = "news";
	public static final int DATABASE_VERSION = 1;

	/**
	 * The default sort order for this table
	 */
	// public static final String DEFAULT_SORT_ORDER = "modified DESC";

	public static final String TYPE = "type";
	public static final String START = "start";
	public static final String STOP = "stop";
	public static final String NOTE = "note";

	public static final String DATABASE_CREATE = "CREATE TABLE "
			+ DATABASE_TABLE + " (" + _ID
			+ " INTEGER primary key autoincrement, " + TYPE + " int not null,"
			+ START + " TIMESTAMP DEFAULT current_timestamp, " + STOP
			+ " TIMESTAMP DEFAULT current_timestamp, " + NOTE
			+ " VARCHAR(50));";

	public static final String DATABASE_DROP = "DROP TABLE IF EXISTS "
			+ DATABASE_NAME;
}
