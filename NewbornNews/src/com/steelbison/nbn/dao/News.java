package com.steelbison.nbn.dao;

/**
 * Definition of news record
 */
public class News {

	public int id;
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
}
