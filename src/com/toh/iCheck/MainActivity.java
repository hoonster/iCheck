package com.toh.iCheck;

//import com.toh.iCheck.HeadsetIntentReceiver;
import java.io.FileOutputStream;
import java.io.IOException;

import com.toh.iCheck.R;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Build;
import android.preference.PreferenceManager;

public class MainActivity extends Activity {
	public static final int REQUEST_CODE_BREATHTEST = 1001;
	public static final int REQUEST_CODE_ALCOHOLTEST = 1002;
	// private HeadsetIntentReceiver myReceiver;
	public int r_c, recv_length = 0;
	public int Bar = 0;
	public int Wave_gain = 0;
	public int Status = 0;
	public int temp_data;
	public int err_code = 0;
	public double bac = 0;
	public int Hr = 0;
	public int gas;
	public int bac_000;
	public int bac_002;
	public int bac_008;
	public int r_0;
	public int r_a;
	public int r_2;
	public int r_8;
	public double aa, bb;
	public String BAC_NUM;
	public int recv_data[] = new int[256];
	public int recv_cnt; // add pye
	byte[] recv_data1 = new byte[256]; // add pye
	public int temp_cnt, chksum, cc;
	AudioManager am;
	public int volume;
	private boolean headSetUnplugged = false;
	final Context context = this;
	String ageStr, weightStr, genderSpi;
	
	RegistrationAdapter adapter2;
	RegistrationOpenHelper helper2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		volume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		
//		am.setStreamVolume(AudioManager.STREAM_MUSIC, 20,
//		AudioManager.FLAG_PLAY_SOUND);
		
//		am.setStreamVolume(AudioManager.STREAM_MUSIC,
//				am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
		
		adapter2 = new RegistrationAdapter(this);
		
//		// Warning alertDialog start
//		boolean firstboot = getSharedPreferences("BOOT_PREF", MODE_PRIVATE)
//				.getBoolean("firstboot", true);
//		if (firstboot) {
//
//			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//					context);
//			LayoutInflater inflater = MainActivity.this.getLayoutInflater();
//			// this is what I did to added the layout to the alert dialog
//			View layout = inflater.inflate(R.layout.dialog, null);
//			alertDialogBuilder.setView(layout);
//			final EditText age = (EditText) layout.findViewById(R.id.age);
//			final EditText weight = (EditText) layout.findViewById(R.id.weight);
//			Spinner spinner = (Spinner) layout.findViewById(R.id.gender);
//			// Create an ArrayAdapter using the string array and a default
//			// spinner layout
//			ArrayAdapter<CharSequence> adapter = ArrayAdapter
//					.createFromResource(this, R.array.gender_array,
//							android.R.layout.simple_spinner_item);
//			// Specify the layout to use when the list of choices appears
//			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//			// Apply the adapter to the spinner
//			spinner.setAdapter(adapter);
//
//			spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//				public void onItemSelected(AdapterView<?> parent, View view,
//						int pos, long id) {
//					genderSpi = parent.getItemAtPosition(pos).toString();
//				}
//
//				public void onNothingSelected(AdapterView<?> parent) {
//				}
//			});
//
//			// set title
//			alertDialogBuilder.setTitle("Warning");
//
//			// set dialog message
//			alertDialogBuilder
//					.setMessage(
//							"This app is not responsible for user's drunk drive. In order to get an accurate result, user's information are required.")
//					.setCancelable(false)
//					.setPositiveButton("Yes",
//							new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog,
//										int id) {
//									ageStr = age.getText().toString();
//									if (ageStr.matches("")) {
//										Toast.makeText(getApplicationContext(),
//												"Please input your age.",
//												Toast.LENGTH_SHORT).show();
//										return;
//									}
//									weightStr = weight.getText().toString();
//									if (weightStr.matches("")) {
//										Toast.makeText(getApplicationContext(),
//												"Please input your weight.",
//												Toast.LENGTH_SHORT).show();
//										return;
//									}
//
//									SharedPreferences preferences = PreferenceManager
//											.getDefaultSharedPreferences(context);
//									SharedPreferences.Editor editor = preferences
//											.edit();
//									editor.putString("Gender", genderSpi);
//									editor.putString("Age", ageStr);
//									editor.putString("Weight", weightStr);
//									editor.apply();
//									
//									String genderValue = genderSpi.toString();
//									String ageValue = ageStr.toString();
//									String weightValue = weightStr.toString();
//									long val = adapter2.insertDetails1(genderValue, ageValue, weightValue);
//
//									try {
//										String FILENAME = "Agreement_file";
//										String string = "This user agreed to the warning statement!";
//
//										FileOutputStream fos = openFileOutput(
//												FILENAME, Context.MODE_PRIVATE);
//										fos.write(string.getBytes());
//										fos.close();
//									} catch (IOException ex) {
//										return;
//									}
//									dialog.cancel();
//								}
//							});
//
//			// create alert dialog
//			AlertDialog alertDialog = alertDialogBuilder.create();
//
//			// show it
//			alertDialog.show();
//
//			getSharedPreferences("BOOT_PREF", MODE_PRIVATE).edit()
//					.putBoolean("firstboot", false).commit();
//		}

		// adapter = new RegistrationAdapter(this);

		// usb
		final Button sendBtn = (Button) findViewById(R.id.imageView1);
		sendBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (am.isWiredHeadsetOn()) {
					Intent intent = new Intent(getApplicationContext(),
							Breath315.class);
					// start it
					startActivityForResult(intent, REQUEST_CODE_BREATHTEST);
					// fskm.writeBytes(msg.getBytes());
					// inputBox.setText(null);
				} else {
					Toast.makeText(getApplicationContext(), "Please insert iCheck.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		final Button clearBtn = (Button) findViewById(R.id.imageView2);
		clearBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (am.isWiredHeadsetOn()) {
					Intent intent = new Intent(getApplicationContext(),
							Alcohol315.class);
					// start it
					startActivityForResult(intent, REQUEST_CODE_ALCOHOLTEST);
				} else {
					Toast.makeText(getApplicationContext(), "Please insert iCheck.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_list) {
			// TODO Auto-generated method stub
			Intent i = new Intent(getApplicationContext(), bacList.class);
			startActivity(i);
			return true;
		}
		if (id == R.id.dynamic_graph) {
			// TODO Auto-generated method stub
			Intent i = new Intent(getApplicationContext(), GraphActivity.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */

	@Override
	protected void onDestroy() {
		am.setStreamVolume(AudioManager.STREAM_MUSIC, volume,
				AudioManager.FLAG_PLAY_SOUND);
		//System.exit(0);
		super.onDestroy();
	}

	@Override
	public void onResume() {
		// IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
		// HeadsetIntentReceiver myReceiver = new HeadsetIntentReceiver();
		// myReceiver = new HeadsetIntentReceiver();
		// registerReceiver(myReceiver, filter);
		am.setStreamVolume(AudioManager.STREAM_MUSIC, volume,
				AudioManager.FLAG_PLAY_SOUND);
		super.onResume();
	}

}
