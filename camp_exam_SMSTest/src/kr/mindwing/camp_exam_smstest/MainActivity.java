package kr.mindwing.camp_exam_smstest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private EditText etSendText;
	private EditText etSendPhoneNumber;
	private Button btSend;
	private TextView tvReceiveText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etSendText = (EditText) findViewById(R.id.send_text);
		etSendPhoneNumber = (EditText) findViewById(R.id.send_phonenumber);
		btSend = (Button) findViewById(R.id.send_button);
		tvReceiveText = (TextView) findViewById(R.id.receive_text);

		btSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sendSms();
			}
		});
	}

	protected void sendSms() {
		String smsText = etSendText.getText().toString();
		String address = etSendPhoneNumber.getText().toString();

		SmsUtil.sendSms(address, smsText);

		Toast.makeText(this, "SMS 가 발송되었습니다.", Toast.LENGTH_SHORT).show();
	}
}