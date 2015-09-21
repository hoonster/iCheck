package com.toh.iCheck;

import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Build;

//import com.toh.iCheck.HeadsetIntentReceiver;
import com.toh.iCheck.R;
import com.toh.iCheck.RegistrationAdapter;
import com.toh.iCheck.RegistrationOpenHelper;
import com.toh.iCheck.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.usb.UsbManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.graphics.Color;
import android.graphics.drawable.*;

import com.toh.fsk315.FSKModem315;
import com.toh.fsk315.FSKModemListener315;
import com.toh.fsk1225.FSKModem1225;
import com.toh.fsk1225.FSKModemListener1225;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.jjoe64.graphview.GraphView.GraphViewData;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class Alcohol315 extends Activity {
	/** Called when the activity is first created. */
	public final static String VIDEO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
	public final static int URL = 1;
	public final static int SDCARD = 2;
	private boolean headSetUnplugged = false;
	private HeadSetPlugReceiver myReceiver = new HeadSetPlugReceiver();
	private FSKModem315 fskm315;
	private FSKModem1225 fskm1225;
	VideoView videoView;
	public static final int REQUEST_CODE_BREATHTEST = 1001;
	public static final int REQUEST_CODE_BLOW_AL = 1002;
	public static final int REQUEST_CODE_BLOW_BS = 1003;
	public static final int REQUEST_CODE_RESULT_AL1 = 1004;
	public static final int REQUEST_CODE_RESULT_AL2 = 1005;
	public static final int REQUEST_CODE_RESULT_AL3 = 1006;
	public static final int REQUEST_CODE_RESULT_AL4 = 1007;
	public static final int REQUEST_CODE_RESULT_AL5 = 1008;
	private static final String TAG = "TTTActivity";
	private static final String LOG_TAG = "end call";

	TextView recvView2, recvView3, textview4, textview5, textView;
	final Context context = this;

	public int recv_ok = 0;

	public int tog = 0;
	CountDownTimer mtimer, mtimer2;
	ProgressDialog progressDialog2;
	ProgressWheel pw_two;

	// byte[] send_data1 ={'t','e','s','t'}; //add pye
	byte[] send_data1 = { 0x05, 0x64, 0x01, 0x02, 0x00, 0x00, 0x64, 0x05 }; // start
																			// mode
	byte[] send_data2 = { 0x05, 0x64, 0x07, 0x08, 0x30, 0x00, 0x64, 0x05 }; // adult
																			// mode
	byte[] send_data3 = { 0x05, 0x64, 0x07, 0x08, 0x30, 0x02, 0x64, 0x05 }; // adult
																			// mode
	byte[] send_data4 = { 0x05, 0x64, 0x07, 0x08, 0x30, 0x08, 0x64, 0x05 }; // adult
																			// mode
	byte[] send_data5 = { 0x05, 0x64, 0x07, 0x08, 0x30, 0x0F, 0x64, 0x05 }; // adult
																			// mode
	byte[] send_data6 = { 0x05, 0x64, 0x07, 0x08, 0x30, 0x0F, 0x64, 0x05 }; // stop
																			// mode

	public int r_c, recv_length = 0;
	public int Bar = 0;
	public int Wave_gain = 0;
	public int Status = 0;
	public int temp_data;
	public int err_code = 0;
	public double bac = 0;
	public double bac1 = 0;
	public int Hr = 0;
	public int gas;
	public int bac_000;
	public int bac_002;
	public int bac_008;
	public int r_0;
	public int r_a;
	public int r_2;
	public int r_8;
	public int r_temp;
	public int Flag;
	public int Flag_OK;
	public int S_Model;

	public int Data_1;
	public int Data_2;
	public int Data_3;
	public double aa, bb, cc;
	public String BAC_NUM;
	public String RECV_NUM;
	public int recv_data[] = new int[256];
	public int recv_cnt; // add pye
	byte[] recv_data1 = new byte[256]; // add pye
	public int temp_cnt, chksum, dd;

	byte[] Start_data = { 'A', 'A' }; // adult mode
	byte[] ACK_data = { 'A', 'A', 'A', 'A' }; // adult mode
	byte[] Mes_data = { 'A', 'A', 'A', 'A', 'A', 'A' }; // adult mode
	byte[] Stop_data = { 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A' }; // adult mode
	byte[] Test_data = { 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A' }; // adult mode
	public int bac_a;
	public int bac_b;
	public int bac_c;
	public int volume;
	public int t_cnt;
	public int stop_flag = 0;
	AudioManager am;
	private int value = 0;
	public int retest = 0;
	public int aaaa = 0;
	public int bbbb = 0;
	public int MESS = 0;
	public int phone_model = 0;
	public int myNum = 0, myNum1 = 0, myNum2 = 0;

	RegistrationAdapter adapter1;
	RegistrationOpenHelper helper1;

	private final ExecutorService mExecutor = Executors
			.newSingleThreadExecutor();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// CØ´cCI´A xmlÆAAI ¸iA¸·I ¹U²a¾ßCO
		setContentView(R.layout.alcolhol315);
		Log.e("###", "ContentView");

		final UncaughtExceptionHandler subclass = Thread.currentThread()
				.getUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread paramThread,
					Throwable paramThrowable) {
				Log.getStackTraceString(paramThrowable);

				subclass.uncaughtException(paramThread, paramThrowable);
			}
		});

		EndCallListener callListener = new EndCallListener();
		TelephonyManager mTM = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);

		am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		volume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

		am.setStreamVolume(AudioManager.STREAM_MUSIC, 20,
				AudioManager.FLAG_PLAY_SOUND);
		// am.setStreamVolume(AudioManager.STREAM_MUSIC,
		// am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
		this.registerReceiver(myReceiver, new IntentFilter(
				"android.intent.action.HEADSET_PLUG"));

		if (am.isWiredHeadsetOn()) {
			headSetUnplugged = true;
			System.out.println("modelName is :" + Build.MODEL);
		} else {
			Toast.makeText(getApplicationContext(), "Please insert iCheck.",
					Toast.LENGTH_SHORT).show();
			System.out.println("device is :" + Build.DEVICE);
		}

		System.out.println("modelName is :" + Build.MODEL);
		System.out.println("device is :" + Build.DEVICE);
		System.out.println("ProductName is :" + Build.PRODUCT);

		String s21 = Build.MODEL;

		String s22 = new String("SHV-E160"); // note 1

		Handler mHandler = new Handler();
		// if(s21.equals(s22)){
		// phone_model = 1;
		// }

		if (s21.contains(s22))
			phone_model = 1;

		phone_model = 0;

		// mHandler.postDelayed(new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// if (am.isWiredHeadsetOn())
		// Toast.makeText(getApplicationContext(), "Ready.",
		// Toast.LENGTH_SHORT).show();
		// }
		// }, 2000);

		if (phone_model == 1) {
			// final FSKModem1225 fskm;
			fskm1225 = new FSKModem1225();
			// FSKModem1225.debugPrint(fskm1225);
			// fskm1225.writeBytes(Stop_data);

			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					fskm1225.writeBytes(Stop_data);
				}
			}, 2000);
			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					fskm1225.writeBytes(Stop_data);
				}
			}, 1000);
		} else {
			// final FSKModem315 fskm;
			// if (fskm315 == null)
			fskm315 = new FSKModem315();
			FSKModem315.debugPrint(fskm315);
			// fskm315.writeBytes(Stop_data);
		}

		// TextView tv1 = (TextView) findViewById(R.id.textViewal);
		// tv1.setText("μI¹øA°ÆaAIAoAO´I´U.");
		Uri video = Uri.parse("android.resource://" + getPackageName()
				+ "/raw/drink");
		videoView = (VideoView) findViewById(R.id.videoViewal);
		videoView.setVideoURI(video);
		videoView.requestFocus();
		videoView.seekTo(0);
		// ºnμð¿A Ac≫y ½AAU
		videoView.start();

		adapter1 = new RegistrationAdapter(this);

		// Toast.makeText(getApplicationContext(), name+name1+name2,
		// Toast.LENGTH_SHORT).show();

		TextView tv1 = ((TextView) findViewById(R.id.somedata));
		tv1.setText(getIntent().getStringExtra("someData"));
		String shared = tv1.getText().toString();
		TextView tv2 = ((TextView) findViewById(R.id.somedata1));
		tv2.setText(getIntent().getStringExtra("someData1"));
		String shared1 = tv2.getText().toString();
		TextView tv3 = ((TextView) findViewById(R.id.somedata2));
		tv3.setText(getIntent().getStringExtra("someData2"));
		String shared2 = tv3.getText().toString();

		try {
			myNum = Integer.parseInt(shared);
			myNum1 = Integer.parseInt(shared1);
			myNum2 = Integer.parseInt(shared2);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}

		Flag = 0;
		Flag_OK = 0;
		MESS = 0;
		recv_cnt = 0;

		// progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		pw_two = (ProgressWheel) findViewById(R.id.progressBarTwo);
		// textView = (TextView) findViewById(R.id.textView6);

		if (myNum > 0 && myNum1 > 0 && myNum2 > 0) {
			retest = 1;
			r_0 = myNum;
			r_2 = myNum1;
			r_8 = myNum2;
			Flag = 6;
		}

		// if(phone_model == 1) fskm1225.writeBytes(Stop_data);
		// else fskm315.writeBytes(Stop_data);
		// fskm.writeBytes(Stop_data);
		System.out.println("retest@@@ :" + retest);
		if (retest == 1) {
			aaaa = 7500;
			// bbbb = aaaa/10;
			Log.e("retest == 1", "aaaa = 10100");
			bbbb = 1000;
		} else {
			aaaa = 30500;
			// bbbb = aaaa/100;
			Log.e("retest == 0", "aaaa = 30400");
			bbbb = 1000;
		}

		value = 0;

		mtimer = new CountDownTimer(aaaa, bbbb) {
			@Override
			public void onFinish() {
				// finish();
				// r_a = 1;
				// r_2 =2;
				if (Flag >= 6) {
					Log.e("&&&", "Flag = 6");
					pw_two.setVisibility(ProgressBar.VISIBLE);

					// Intent intent = new Intent(getApplicationContext(),
					// blow_al.class);
					// startActivityForResult(intent, REQUEST_CODE_BLOW_AL);
					// progressDialog2.show();
					// mtimer2.start();
					MESS = 1;
					result();

				} else {
					//
					MESS = 0;
					value = 0;
					mtimer.start();
				}
			}

			@Override
			public void onTick(long millisUntilFinished) {
				// value = value + 2;
				t_cnt++;
				MESS = 0;
				if (aaaa == 30500) {
					if (tog == 0)
						tog = 1;
					else
						tog = 0;
					// if (value == 0)
					// value = 12;
					value = value + 12;

				} else {
					// if (value == 0)
					// value = 36;
					value = value + 52;
				}
				// if (aaaa == 37500) {
				pw_two.setProgress(value, retest);
				// percent = (100*value/pw_two.getMax());
				// textView.setText(String.valueOf(percent)+"%");
				if (retest == 0) {
					Log.e("retest == 0", "retest");

					if (value > 80 || Flag >= 6) {
						if (phone_model == 1)
							fskm1225.writeBytes(ACK_data);
						else
							Log.e("retest == 0", "value>80Flag>=6");
						fskm315.writeBytes(ACK_data);
						Flag = 6;
					} else {
						if (phone_model == 1)
							fskm1225.writeBytes(Start_data);
						else
							Log.e("retest == 0", "valueFlag");
						fskm315.writeBytes(Start_data);
					}
				} else { // r_0,r_2,r_8
					Log.e("retest == 1", "retest");
					System.out.println("R_0 is :" + r_0);
					System.out.println("R_2 is :" + r_2);
					System.out.println("R_8 is :" + r_8);
					BAC_NUM = "";
					if (phone_model == 1)
						try {
							fskm1225.writeBytes(ACK_data);
						} catch (NumberFormatException ex) {
							ex.printStackTrace();
						}
					else
						try {
							fskm315.writeBytes(ACK_data);
							Log.e("###", "#retest#");
						} catch (NumberFormatException ex) {
							ex.printStackTrace();
						}
				}
			}
		};

		// A¸AI¸O ½AAU
		mtimer.start();

		if (phone_model == 0) {
			fskm315.addDataReceiver(new FSKModemListener315() {
				@Override
				public void dataReceivedFromFSKModem(final byte[] data) {
					runOnUiThread(new Runnable() {
						public void run() {
							Log.e("###", "addDataReceiver");
							// StringBuilder sb = new StringBuilder();
							TextView recvView = (TextView) findViewById(R.id.textViewal);
							TextView recvView2 = (TextView) findViewById(R.id.textView4);
							recvView.setText("R");
							System.out.println("GAS#" + gas);
							// sb.append(recvView.getText().toString());
							for (int i = 0; data != null && i < data.length; i++) {
								recv_data1[recv_cnt] = data[i];
								System.out.println("Data is :" + data[i]);
								if ((data[i] & 0xFF) == 0x7F || data[i] == -1) {
									if (recv_cnt >= 6) {
										for (int k = 0; k < 6; k++) {
											recv_data[k] = recv_data1[recv_cnt
													- 6 + k] & 0xFF;
										}

										chksum = (recv_data[1] + recv_data[2]
												+ recv_data[3] + recv_data[4]) % 256;// +
										// recv_data[3]

										if (chksum == (recv_data[5] & 0xFF)
												&& recv_data[0] == 15) {
											RECV_NUM = String
													.format("%d - %d %d %d %d %d [%d] [%d] chk_ok",
															dd, recv_data[0],
															recv_data[1],
															recv_data[2],
															recv_data[3],
															recv_data[4],
															recv_data[5],
															chksum);
											recvView.setText(RECV_NUM);

											if (Flag < 6) {
												Hr = 30;
												gas = (recv_data[1] * Hr) / 256;
												bac_000 = (recv_data[2] * Hr) / 256;
												bac_002 = (recv_data[3] * Hr) / 256;
												bac_008 = (recv_data[4] * Hr) / 256;
												r_temp = (10000 * gas)
														/ (Hr - gas);
												if (r_temp > 100)
													r_a = r_temp;

												r_temp = (10000 * bac_000)
														/ (Hr - bac_000);
												if (r_temp > 100)
													r_0 = r_temp; // rrr

												r_temp = (10000 * bac_002)
														/ (Hr - bac_002);
												if (r_temp > 100)
													r_2 = r_temp; // rrr

												r_temp = (10000 * bac_008)
														/ (Hr - bac_008);
												if (r_temp > 100)
													r_8 = r_temp; // rrr

												System.out.println("r_temp :"
														+ r_temp);
												System.out.println("Flag :"
														+ Flag);
												System.out.println("Hr is :"
														+ Hr);
												System.out.println("r_a is :"
														+ r_a);
												System.out.println("r_0 is :"
														+ r_0);
												System.out.println("r_2 is :"
														+ r_2);
												System.out.println("r_8 is :"
														+ r_8);
												// if(recv_data[2] !=
												// 0xFF &&
												// recv_data[3] != 0xFF
												// &&
												// recv_data[4] !=
												// 0xFF){
												// Flag = 6;
												// }
											} else {
												if (recv_data[3] != 0) {
													if (phone_model == 1) {
														r_temp = (23 * 256)
																/ recv_data[3];
													} else {
														r_temp = (21 * 256)
																/ recv_data[3];
													}
													if (r_temp > 10)
														Hr = r_temp;
													else
														Hr = 30;
													// Hr = 30;
													gas = (recv_data[2] * Hr) / 256;
													r_a = (10000 * gas)
															/ (Hr - gas); //
													
													if (r_a > (r_2 + (r_0 - r_2) / 2)) {
														cc = 0.00;
													} else {
														if (r_a > r_8) {
															aa = ((Math
																	.log10(r_2) - Math
																	.log10(r_8)) / (Math
																	.log10(0.02) - Math
																	.log10(0.08)));
															bb = ((Math
																	.log10(0.02)
																	* Math.log10(r_8) - Math
																	.log10(0.08)
																	* Math.log10(r_2)) / (Math
																	.log10(0.02) - Math
																	.log10(0.08)));
															cc = Math
																	.pow(10,
																			((Math.log10(r_a) - bb) / aa));
														} else {
															aa = (r_2 - r_8)
																	/ (0.02 - 0.08);
															bb = (0.02 * r_8 - 0.08 * r_2)
																	/ (0.02 - 0.08);
															cc = (r_a - bb)
																	/ aa;
														}
													}
													bac = cc;
													BAC_NUM = String.format(
															"%.2f", cc);
													recvView2.setText(BAC_NUM);
													System.out
															.println("r_temp :"
																	+ r_temp);
													System.out.println("Flag :"
															+ Flag);
													System.out
															.println("Hr is :"
																	+ Hr);
													System.out
															.println("r_a is :"
																	+ r_a);
													System.out
															.println("r_0 is :"
																	+ r_0);
													System.out
															.println("r_2 is :"
																	+ r_2);
													System.out
															.println("r_8 is :"
																	+ r_8);
													System.out
															.println("BAC is :"
																	+ bac);
													Flag = 7;
												}

											}
										} else {
											RECV_NUM = String
													.format("%d - %d %d %d %d %d [%d] [%d] chk_Error",
															dd, recv_data[0],
															recv_data[1],
															recv_data[2],
															recv_data[3],
															recv_data[4],
															recv_data[5],
															chksum);
											recvView.setText(RECV_NUM);
										}
										temp_cnt = recv_cnt;
										if (recv_data[0] == 15)
											recv_cnt = 0;

										// recv_data[4] = 0;
										temp_cnt = 0;
										dd++;
										if (dd > 9)
											dd = 0;
									} else {
									}
								}
								recv_cnt++;
								if (recv_cnt > 200)
									recv_cnt = 0;

							}
						}
					});
				}
			});
		}

		if (phone_model == 1) {
			fskm1225.addDataReceiver(new FSKModemListener1225() {
				@Override
				public void dataReceivedFromFSKModem(final byte[] data) {
					runOnUiThread(new Runnable() {
						public void run() {
							StringBuilder sb = new StringBuilder();
							// TextView recvView = (TextView)
							// findViewById(R.id.tvReceived);
							TextView recvView = (TextView) findViewById(R.id.textViewal);
							TextView recvView2 = (TextView) findViewById(R.id.textView4);
							recvView.setText("R");

							// sb.append(recvView.getText().toString());
							for (int i = 0; data != null && i < data.length; i++) {
								recv_data1[recv_cnt] = data[i];
								System.out.println("Data is :" + data[i]);
								if ((data[i] & 0xFF) == 0x7F || data[i] == -1) {
									if (recv_cnt >= 6) {
										for (int k = 0; k < 6; k++) {
											recv_data[k] = recv_data1[recv_cnt
													- 6 + k] & 0xFF;
										}

										chksum = (recv_data[1] + recv_data[2]
												+ recv_data[3] + recv_data[4]) % 256;// +
										// recv_data[3]

										if (chksum == (recv_data[5] & 0xFF)
												&& recv_data[0] == 15) {
											RECV_NUM = String
													.format("%d - %d %d %d %d %d [%d] [%d] chk_ok",
															dd, recv_data[0],
															recv_data[1],
															recv_data[2],
															recv_data[3],
															recv_data[4],
															recv_data[5],
															chksum);
											recvView.setText(RECV_NUM);

											if (Flag < 6) {
												Hr = 30;
												gas = (recv_data[1] * Hr) / 256;
												bac_000 = (recv_data[2] * Hr) / 256;
												bac_002 = (recv_data[3] * Hr) / 256;
												bac_008 = (recv_data[4] * Hr) / 256;

												r_temp = (10000 * gas)
														/ (Hr - gas);
												if (r_temp > 100)
													r_a = r_temp;
												// r_a = (10000*gas)/ (Hr -
												// gas);

												r_temp = (10000 * bac_000)
														/ (Hr - bac_000);
												if (r_temp > 100)
													r_0 = r_temp;
												// r_0 = (10000*bac_000)/
												// (Hr -
												// bac_000);

												r_temp = (10000 * bac_002)
														/ (Hr - bac_002);
												if (r_temp > 100)
													r_2 = r_temp;
												// r_2 = (10000*bac_002)/
												// (Hr -
												// bac_002);

												r_temp = (10000 * bac_008)
														/ (Hr - bac_008);
												if (r_temp > 100)
													r_8 = r_temp;
												// r_8 = (10000*bac_008)/
												// (Hr -
												// bac_008);

												System.out.println("Hr is :"
														+ Hr);
												System.out.println("r_a is :"
														+ r_a);
												System.out.println("r_2 is :"
														+ r_2);
												System.out.println("r_8 is :"
														+ r_8);
												// if(recv_data[2] != 0xFF
												// &&
												// recv_data[3] != 0xFF &&
												// recv_data[4] != 0xFF){
												// Flag = 6;
												// }
											} else {
												if (recv_data[3] != 0) {
													if (phone_model == 1) {
														r_temp = (23 * 256)
																/ recv_data[3];
													} else {
														r_temp = (21 * 256)
																/ recv_data[3];
													}
													if (r_temp > 10)
														Hr = r_temp;
													else
														Hr = 30;
													// Hr = 30;
													gas = (recv_data[2] * Hr) / 256;
													r_a = (10000 * gas)
															/ (Hr - gas);

													if (r_a > (r_2 + (r_0 - r_2) / 2)) {
														cc = 0.00;
													} else {
														if (r_a > r_8) {
															aa = ((Math
																	.log10(r_2) - Math
																	.log10(r_8)) / (Math
																	.log10(0.02) - Math
																	.log10(0.08)));
															bb = ((Math
																	.log10(0.02)
																	* Math.log10(r_8) - Math
																	.log10(0.08)
																	* Math.log10(r_2)) / (Math
																	.log10(0.02) - Math
																	.log10(0.08)));
															cc = Math
																	.pow(10,
																			((Math.log10(r_a) - bb) / aa));
														} else {
															aa = (r_2 - r_8)
																	/ (0.02 - 0.08);
															bb = (0.02 * r_8 - 0.08 * r_2)
																	/ (0.02 - 0.08);
															cc = (r_a - bb)
																	/ aa;
														}
													}
													bac = cc;// 21*256/179

													BAC_NUM = String.format(
															"%.2f", cc);
													recvView2.setText(BAC_NUM);
													System.out
															.println("Hr is :"
																	+ Hr);
													System.out
															.println("r_a is :"
																	+ r_a);
													System.out
															.println("r_2 is :"
																	+ r_2);
													System.out
															.println("r_8 is :"
																	+ r_8);
													System.out
															.println("BAC is :"
																	+ bac);
													Flag = 7;
												}

											}
										} else {
											RECV_NUM = String
													.format("%d - %d %d %d %d %d [%d] [%d] chk_Error",
															dd, recv_data[0],
															recv_data[1],
															recv_data[2],
															recv_data[3],
															recv_data[4],
															recv_data[5],
															chksum);
											recvView.setText(RECV_NUM);
										}
										temp_cnt = recv_cnt;
										if (recv_data[0] == 15)
											recv_cnt = 0;

										// recv_data[4] = 0;
										temp_cnt = 0;
										dd++;
										if (dd > 9)
											dd = 0;
									} else {

									}
								}
								recv_cnt++;
								if (recv_cnt > 200)
									recv_cnt = 0;
							}
						}
					});
				}
			});
		}

		if (phone_model == 1)
			fskm1225.start();
		else
			fskm315.start();
		// fskm.start();
	}

	public void result() {
		Log.e("###", "result");
		final ImageView i = (ImageView) findViewById(R.id.img2);

		i.setBackgroundResource(R.drawable.blow);

		i.post(new Runnable() {
			@Override
			public void run() {
				AnimationDrawable frameAnimation = (AnimationDrawable) i
						.getBackground();
				frameAnimation.start();
			}
		});

		progressDialog2 = new ProgressDialog(Alcohol315.this, R.style.MyTheme);
		progressDialog2.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		// progressDialog2.getWindow().clearFlags(LayoutParams.FLAG_DIM_BEHIND);
		progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog2.setMessage("Please blow more than 3 seconds.");
		progressDialog2.show();
		progressDialog2.setCancelable(false);
		// progressDialog2.setCanceledOnTouchOutside(false);

		value = 0;
		mtimer2 = new CountDownTimer(10500, 1000) {
			@Override
			public void onFinish() {
				progressDialog2.dismiss();
				view_result();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				value++;
				progressDialog2.setProgress(value * 10);
				System.out.println("GAS" + gas);
				if (Flag_OK == 0) {
					if (phone_model == 1) {
						fskm1225.writeBytes(ACK_data);
					} else {
						fskm315.writeBytes(ACK_data);
					}

				} else {
					if (phone_model == 1) {
						fskm1225.writeBytes(ACK_data);
					} else {
						fskm315.writeBytes(ACK_data);
					}
				}
			}
		};
		mtimer2.start();
	}

	public void view_result() {
		Log.e("###", "view_result");
		Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// long[] pattern = {1000, 200, 1000, 2000, 1200};
		// vibe.vibrate(pattern, 0);
		vibe.vibrate(1000);
		recvView3 = (TextView) findViewById(R.id.textViewal2);

		BAC_NUM = String.format("%.2f", bac);
		bac1 = Double.parseDouble(BAC_NUM);
		// bac1 = 0.02;
		recvView3.setText(BAC_NUM);
		Flag = 0;
		Flag_OK = 0;

		Calendar c = Calendar.getInstance();
		System.out.println("Current time => " + c.getTime());

		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm");
		String formattedDate = df.format(c.getTime());

		String bacValue = BAC_NUM.toString();
		// bacValue = String.valueOf(0.06);
		String dateValue = formattedDate.toString();
		long val = adapter1.insertDetails(bacValue, dateValue);

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("bacGraph", bacValue);
		editor.apply();

		if (bac1 < 0.02) {
			System.out.println("BAC is :" + bac1);
			Intent intent = new Intent(Alcohol315.this, Result_al1.class);
			intent.putExtra("bacValue", BAC_NUM);
			intent.putExtra("r_0", Integer.toString(r_0));
			intent.putExtra("r_2", Integer.toString(r_2));
			intent.putExtra("r_8", Integer.toString(r_8));

			startActivityForResult(intent, REQUEST_CODE_RESULT_AL1);
			setResult(RESULT_OK, null);
			finish();
		} else if (bac1 >= 0.02 && bac1 < 0.05) {
			System.out.println("BAC is :" + bac1);
			Intent intent = new Intent(Alcohol315.this, Result_al2.class);
			intent.putExtra("bacValue", BAC_NUM);
			intent.putExtra("r_0", Integer.toString(r_0));
			intent.putExtra("r_2", Integer.toString(r_2));
			intent.putExtra("r_8", Integer.toString(r_8));

			startActivityForResult(intent, REQUEST_CODE_RESULT_AL2);
			setResult(RESULT_OK, null);
			finish();
		} else if (bac1 >= 0.05 && bac1 < 0.1) {
			System.out.println("BAC is :" + bac1);
			Intent intent = new Intent(Alcohol315.this, Result_al3.class);
			intent.putExtra("bacValue", BAC_NUM);
			intent.putExtra("r_0", Integer.toString(r_0));
			intent.putExtra("r_2", Integer.toString(r_2));
			intent.putExtra("r_8", Integer.toString(r_8));

			startActivityForResult(intent, REQUEST_CODE_RESULT_AL3);
			setResult(RESULT_OK, null);
			finish();
		} else if (bac1 >= 0.1 && bac1 < 0.2) {
			System.out.println("BAC is :" + bac1);
			Intent intent = new Intent(Alcohol315.this, Result_al4.class);
			intent.putExtra("bacValue", BAC_NUM);
			intent.putExtra("r_0", Integer.toString(r_0));
			intent.putExtra("r_2", Integer.toString(r_2));
			intent.putExtra("r_8", Integer.toString(r_8));

			startActivityForResult(intent, REQUEST_CODE_RESULT_AL4);
			setResult(RESULT_OK, null);
			finish();
		} else if (bac1 >= 0.2) {
			System.out.println("BAC is :" + bac1);
			Intent intent = new Intent(Alcohol315.this, Result_al5.class);
			intent.putExtra("bacValue", BAC_NUM);
			intent.putExtra("r_0", Integer.toString(r_0));
			intent.putExtra("r_2", Integer.toString(r_2));
			intent.putExtra("r_8", Integer.toString(r_8));

			startActivityForResult(intent, REQUEST_CODE_RESULT_AL5);
			setResult(RESULT_OK, null);
			finish();
		}
	}

	private class EndCallListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			if (TelephonyManager.CALL_STATE_RINGING == state) {
				System.exit(0);
				Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
			}
			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// wait for phone to go offhook (probably set a boolean flag) so
				// you know your app initiated the call.
				Log.i(LOG_TAG, "OFFHOOK");
			}
			if (TelephonyManager.CALL_STATE_IDLE == state) {
				// when this state occurs, and your flag is set, restart your
				// app
				// System.exit(0);
				Log.i(LOG_TAG, "IDLE");
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			System.exit(0);
		}
		return super.onKeyDown(keyCode, event);
	}

	public void onUserLeaveHint() { // this only executes when Home is selected.
		System.exit(0);
		super.onUserLeaveHint();
	}

	@Override
	protected void onDestroy() {
		am.setStreamVolume(AudioManager.STREAM_MUSIC, volume,
				AudioManager.FLAG_PLAY_SOUND);
		if (myReceiver != null) {
			unregisterReceiver(myReceiver);
			myReceiver = null;
		}
		if (fskm1225 != null) {
			fskm1225.stop();
			fskm1225 = null;
		}
		if (fskm315 != null) {
			fskm315.stop();
			fskm315 = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		am.setStreamVolume(AudioManager.STREAM_MUSIC, volume,
				AudioManager.FLAG_PLAY_SOUND);
		if (myReceiver != null) {
			unregisterReceiver(myReceiver);
			myReceiver = null;
		}
		if (fskm1225 != null) {
			fskm1225.stop();
			fskm1225 = null;
		}
		if (fskm315 != null) {
			fskm315.stop();
			fskm315 = null;
		}
		super.onStop();
	}

	@Override
	public void onResume() {
		// IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
		// IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
		// HeadsetIntentReceiver myReceiver = new HeadsetIntentReceiver();
		// myReceiver = new HeadsetIntentReceiver();
		// registerReceiver(myReceiver, filter);
		am.setStreamVolume(AudioManager.STREAM_MUSIC, 20,
				AudioManager.FLAG_PLAY_SOUND);
		super.onResume();
	}

	@Override
	public void onPause() {
		// unregisterReceiver(myReceiver);
		// fskm2.stop();
		// fskm2 = null;
		am.setStreamVolume(AudioManager.STREAM_MUSIC, volume,
				AudioManager.FLAG_PLAY_SOUND);
		if (myReceiver != null) {
			unregisterReceiver(myReceiver);
			myReceiver = null;
		}
		if (fskm1225 != null) {
			fskm1225.stop();
			fskm1225 = null;
		}
		if (fskm315 != null) {
			fskm315.stop();
			fskm315 = null;
		}
		super.onPause();

	}

	private class HeadSetPlugReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equalsIgnoreCase(Intent.ACTION_HEADSET_PLUG)) {
				int state = intent.getIntExtra("state", -1);
				switch (state) {
				case 0:
					Log.d("TTT", "iCheck is unplugged");

					if (headSetUnplugged == true) {
						Toast.makeText(getApplicationContext(),
								"ICheck is detached.  App will be finished.",
								Toast.LENGTH_SHORT).show();
						try {
							if (myReceiver != null) {
								unregisterReceiver(myReceiver);
								myReceiver = null;
							}
							if (fskm1225 != null) {
								fskm1225.stop();
								fskm1225 = null;
							}
							if (fskm315 != null) {
								fskm315.stop();
								fskm315 = null;
							}
							// finish();
							System.exit(0);
						} catch (Exception e) {

						}
					} else
						Toast.makeText(context, "iCheck is unplugged",
								Toast.LENGTH_SHORT).show();
					headSetUnplugged = false;
					break;
				case 1:
					Log.d("TTT", "iCheck is plugged");
					Toast.makeText(context, "iCheck is plugged",
							Toast.LENGTH_SHORT).show();
					headSetUnplugged = true;
					break;
				default:
					Log.d("TTT", "I have no idea what the headset state is");
					Toast.makeText(context,
							"I have no idea what the headset state is",
							Toast.LENGTH_SHORT).show();
				}
			}
		}
	}
}
