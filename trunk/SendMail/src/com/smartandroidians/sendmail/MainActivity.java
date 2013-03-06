package com.smartandroidians.sendmail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		final EditText etEmailTo = (EditText) findViewById(R.id.et_email_to);
		final EditText etSubject = (EditText) findViewById(R.id.et_subject);
		final EditText etMessage = (EditText) findViewById(R.id.et_message);
		Button btnSend = (Button) findViewById(R.id.btn_send);

		btnSend.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[] { etEmailTo.getText().toString() });
				email.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
				email.putExtra(Intent.EXTRA_TEXT, etMessage.getText().toString());
				email.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/blaaaa.txt"));
				// need this to prompts email client only
				email.setType("message/rfc822");
				startActivity(Intent.createChooser(email, "Send Email"));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
