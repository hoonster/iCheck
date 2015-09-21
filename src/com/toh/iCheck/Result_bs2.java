package com.toh.iCheck;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Result_bs2 extends Activity {

	// VideoView videoView;
	String sharedFact, sharedFact1, sharedFact2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_bs2);

		// videoView = (VideoView) findViewById(R.id.videoViewbs2);

		// video play by url
		// new YourAsyncTask().execute();

		// video play by file
		// Uri video1 = Uri.parse("android.resource://" + getPackageName() + "/"
		// + R.raw.result2);
		// videoView.setVideoURI(video1);
		// videoView.requestFocus();
		// videoView.start();

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String name = preferences.getString("Gender", "");
		String name1 = preferences.getString("Age", "");
		String name2 = preferences.getString("Weight", "");
		String female = "¿©ÀÚ";

		final ImageView i = (ImageView) findViewById(R.id.img2);

		if (name.equals(female)) {
			i.setBackgroundResource(R.drawable.anim1);
		} else {
			i.setBackgroundResource(R.drawable.animmale1);
		}
		
		i.post(new Runnable() {
			@Override
			public void run() {
				AnimationDrawable frameAnimation = (AnimationDrawable) i
						.getBackground();
				frameAnimation.start();
			}
		});

		TextView tv = ((TextView) findViewById(R.id.bacValuebs2));
		tv.setText(getIntent().getStringExtra("bacValue"));

		TextView tv1 = ((TextView) findViewById(R.id.resultbs2_r0));
		tv1.setText(getIntent().getStringExtra("r_0"));
		sharedFact = tv1.getText().toString();

		TextView tv2 = ((TextView) findViewById(R.id.resultbs2_r2));
		tv2.setText(getIntent().getStringExtra("r_2"));
		sharedFact1 = tv2.getText().toString();

		TextView tv3 = ((TextView) findViewById(R.id.resultbs2_r8));
		tv3.setText(getIntent().getStringExtra("r_8"));
		sharedFact2 = tv3.getText().toString();

		if (sharedFact.equals("0") && sharedFact1.equals("0") && sharedFact2.equals("0")) {
			Toast.makeText(getApplicationContext(), "Please unplug iCheck and start the app again.",
					Toast.LENGTH_SHORT).show();
		}
		
		Button butGene = (Button) findViewById(R.id.testAgainbs2);
		butGene.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						Breath315.class);
				intent.putExtra("someDatabs", sharedFact);
				intent.putExtra("someDatabs1", sharedFact1);
				intent.putExtra("someDatabs2", sharedFact2);
				startActivityForResult(intent, 1);
				finish();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Intent refresh = new Intent(this, Result_bs2.class);
			startActivity(refresh);
			this.finish();
		}
	}
}
