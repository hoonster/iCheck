package com.toh.iCheck;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RegistrationOpenHelper extends SQLiteOpenHelper {
	// Database name
	public static final String DATABASE_NAME = "REGISTRATION_DB";

	// Table names
	public static final String TABLE_NAME = "REGISTRATION_TABLE";
	public static final String USER_INFO = "USERINFO_TABLE";

	public static final int VERSION = 1;
	public static final String KEY_ID = "_id";
	public static final String FNAME = "F_NAME";
	public static final String LNAME = "L_NAME";
	public static final String GENDER = "USER_GENDER";
	public static final String AGE = "USER_AGE";
	public static final String WEIGHT = "USER_WEIGHT";

	public static final String SCRIPT = "create table " + TABLE_NAME + " ("
			+ KEY_ID + " integer primary key autoincrement, " + FNAME
			+ " text not null, " + LNAME + " text not null );";

	public static final String USERINFO_TABLE = "create table " + USER_INFO
			+ " (" + KEY_ID + " integer primary key autoincrement, " + GENDER
			+ " text not null, " + AGE + " text not null, " + WEIGHT + " text not null );";

	public RegistrationOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SCRIPT);
		db.execSQL(USERINFO_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table " + TABLE_NAME);
		db.execSQL("drop table " + USER_INFO);
		onCreate(db);
	}

}