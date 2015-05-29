package kr.mindwing.camp_exam_smstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsMessage;
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

	// Telephony.Sms.SMS_RECEIVED_ACTION 과 같은 값.
	private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	private IntentFilter filter = new IntentFilter();
	{
		filter.addAction(ACTION_SMS_RECEIVED);
	}

	private BroadcastReceiver smsReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			SmsMessage[] smsData = SmsUtil.getSmsData(context, intent);

			tvReceiveText.setText(SmsUtil.makeOneString(smsData[0]));
		}
	};

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

	@Override
	protected void onResume() {
		super.onResume();

		registerReceiver(smsReceiver, filter);
	}

	@Override
	protected void onPause() {
		super.onPause();

		unregisterReceiver(smsReceiver);
	}

	protected void sendSms() {
		String smsText = etSendText.getText().toString();
		String address = etSendPhoneNumber.getText().toString();

		SmsUtil.sendSms(address, smsText);

		Toast.makeText(this, "SMS 가 발송되었습니다.", Toast.LENGTH_SHORT).show();
	}
}
