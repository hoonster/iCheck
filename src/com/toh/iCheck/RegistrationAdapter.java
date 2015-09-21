package com.toh.iCheck;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RegistrationAdapter {
	SQLiteDatabase database_ob;
	RegistrationOpenHelper openHelper_ob;
	Context context;

	public RegistrationAdapter(Context c) {
		context = c;
	}

	public RegistrationAdapter opnToRead() {
		openHelper_ob = new RegistrationOpenHelper(context,
				openHelper_ob.DATABASE_NAME, null, openHelper_ob.VERSION);
		database_ob = openHelper_ob.getReadableDatabase();
		return this;

	}

	public RegistrationAdapter opnToWrite() {
		openHelper_ob = new RegistrationOpenHelper(context,
				openHelper_ob.DATABASE_NAME, null, openHelper_ob.VERSION);
		database_ob = openHelper_ob.getWritableDatabase();
		return this;

	}

	public void Close() {
		database_ob.close();
	}

	public long insertDetails(String fname, String lname) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(openHelper_ob.FNAME, fname);
		contentValues.put(openHelper_ob.LNAME, lname);
		opnToWrite();
		long val = database_ob.insert(openHelper_ob.TABLE_NAME, null,
				contentValues);
		Close();
		return val;

	}

	public long insertDetails1(String gender, String age, String weight) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(openHelper_ob.GENDER, gender);
		contentValues.put(openHelper_ob.AGE, age);
		contentValues.put(openHelper_ob.WEIGHT, weight);
		opnToWrite();
		long val = database_ob.insert(openHelper_ob.USER_INFO, null,
				contentValues);
		Close();
		return val;
	}

	public Cursor queryName() {
		String[] cols = { openHelper_ob.KEY_ID, openHelper_ob.FNAME,
				openHelper_ob.LNAME };
		opnToWrite();
		Cursor c = database_ob.query(openHelper_ob.TABLE_NAME, cols, null,
				null, null, null, null);

		return c;
	}

	public ArrayList<String> queryNameString() {
		String[] cols = { openHelper_ob.KEY_ID, openHelper_ob.FNAME,
				openHelper_ob.LNAME };
		opnToWrite();
		Cursor c = database_ob.query(openHelper_ob.TABLE_NAME, cols, null,
				null, null, null, null);

		ArrayList<String> numsy = new ArrayList<String>();
		// numsy.add(cursor);
		// double[] result;
		int iDate = c.getColumnIndex(openHelper_ob.FNAME);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			// numsy = c.getString( iDate );
			numsy.add(c.getString(iDate));
			System.out.println("NUMSY" + numsy);
		}

		return numsy;
	}

	public Cursor queryName1() {
		String[] cols = { openHelper_ob.KEY_ID, openHelper_ob.GENDER,
				openHelper_ob.AGE, openHelper_ob.WEIGHT };
		opnToWrite();
		Cursor c = database_ob.query(openHelper_ob.USER_INFO, cols, null, null,
				null, null, null);

		return c;
	}

	public Cursor queryAll(int nameId) {
		String[] cols = { openHelper_ob.KEY_ID, openHelper_ob.FNAME,
				openHelper_ob.LNAME };
		opnToWrite();
		Cursor c = database_ob.query(openHelper_ob.TABLE_NAME, cols,
				openHelper_ob.KEY_ID + "=" + nameId, null, null, null, null);

		return c;
	}

	public Cursor queryAll1(int nameId) {
		String[] cols = { openHelper_ob.KEY_ID, openHelper_ob.GENDER,
				openHelper_ob.AGE, openHelper_ob.WEIGHT };
		opnToWrite();
		Cursor c = database_ob.query(openHelper_ob.USER_INFO, cols,
				openHelper_ob.KEY_ID + "=" + nameId, null, null, null, null);

		return c;
	}

	public long updateldetail(int rowId, String fname, String lname) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(openHelper_ob.FNAME, fname);
		contentValues.put(openHelper_ob.LNAME, lname);
		opnToWrite();
		long val = database_ob.update(openHelper_ob.TABLE_NAME, contentValues,
				openHelper_ob.KEY_ID + "=" + rowId, null);
		Close();
		return val;
	}

	public long updateldetail1(int rowId, String gender, String age,
			String weight) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(openHelper_ob.GENDER, gender);
		contentValues.put(openHelper_ob.AGE, age);
		contentValues.put(openHelper_ob.WEIGHT, weight);
		opnToWrite();
		long val = database_ob.update(openHelper_ob.USER_INFO, contentValues,
				openHelper_ob.KEY_ID + "=" + rowId, null);
		Close();
		return val;
	}

	public int deletOneRecord(int rowId) {
		// TODO Auto-generated method stub
		opnToWrite();
		int val = database_ob.delete(openHelper_ob.TABLE_NAME,
				openHelper_ob.KEY_ID + "=" + rowId, null);
		Close();
		return val;
	}

	public int deletOneRecord1(int rowId) {
		// TODO Auto-generated method stub
		opnToWrite();
		int val = database_ob.delete(openHelper_ob.USER_INFO,
				openHelper_ob.KEY_ID + "=" + rowId, null);
		Close();
		return val;
	}

}