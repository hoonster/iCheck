package com.toh.iCheck;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Result_al1 extends Activity {

	VideoView videoView;
	String sharedFact, sharedFact1, sharedFact2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_al1);

		videoView = (VideoView) findViewById(R.id.videoView1);

		// video play by url
		// new YourAsyncTask().execute();

		// video play by file
		Uri video1 = Uri.parse("android.resource://" + getPackageName() + "/"
				+ R.raw.result1);
		videoView.setVideoURI(video1);
		videoView.requestFocus();
		videoView.start();

		TextView tv = ((TextView) findViewById(R.id.bacValue1));
		tv.setText(getIntent().getStringExtra("bacValue"));
		
		TextView tv1 = ((TextView) findViewById(R.id.resultAl1_r0));
		tv1.setText(getIntent().getStringExtra("r_0"));
		sharedFact = tv1.getText().toString();
		
		TextView tv2 = ((TextView) findViewById(R.id.resultAl1_r2));
		tv2.setText(getIntent().getStringExtra("r_2"));
		sharedFact1 = tv2.getText().toString();
		
		TextView tv3 = ((TextView) findViewById(R.id.resultAl1_r8));
		tv3.setText(getIntent().getStringExtra("r_8"));
		sharedFact2 = tv3.getText().toString();
		
		if (sharedFact.equals("0") && sharedFact1.equals("0") && sharedFact2.equals("0")) {
			Toast.makeText(getApplicationContext(), "Please unplug iCheck and start the app again.",
					Toast.LENGTH_SHORT).show();
		}

		Button butGene = (Button) findViewById(R.id.testAgain1);
		butGene.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Alcohol315.class);
				intent.putExtra("someData", sharedFact);
				intent.putExtra("someData1", sharedFact1);
				intent.putExtra("someData2", sharedFact2);
				startActivityForResult(intent, 1);
				finish();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Intent refresh = new Intent(this, Result_al1.class);
			startActivity(refresh);
			this.finish();
		}
	}
}
