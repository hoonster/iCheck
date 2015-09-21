package com.toh.iCheck;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class UserinfoList extends Activity {

	RegistrationAdapter adapter_ob;
	RegistrationOpenHelper helper_ob;
	SQLiteDatabase db_ob;
	ListView nameList;
	Button registerBtn;
	Cursor cursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinfolist);
		nameList = (ListView) findViewById(R.id.user_name);
		// registerBtn = (Button) findViewById(R.id.btn_register);
		adapter_ob = new RegistrationAdapter(this);

		String[] from = { helper_ob.GENDER, helper_ob.AGE, helper_ob.WEIGHT };
		int[] to = { R.id.gender_name, R.id.age_name, R.id.weight_name };
		cursor = adapter_ob.queryName1();
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
				R.layout.row1, cursor, from, to);
		nameList.setAdapter(cursorAdapter);
		nameList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle passdata = new Bundle();
				Cursor listCursor = (Cursor) arg0.getItemAtPosition(arg2);
				int nameId = listCursor.getInt(listCursor
						.getColumnIndex(helper_ob.KEY_ID));
				// Toast.makeText(getApplicationContext(),
				// Integer.toString(nameId), 500).show();
				passdata.putInt("keyid", nameId);
				Intent passIntent = new Intent(UserinfoList.this,
						UserinfoList.class);
				passIntent.putExtras(passdata);
				startActivity(passIntent);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		cursor.requery();

	}
}
