package kr.mindwing.camp_exam_smstest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsUtil {
	private static DateFormat dateFormat;
	static {
		dateFormat = SimpleDateFormat.getInstance();
	}

	public static void sendSms(String destinationAddress, String smsText) {
		SmsManager.getDefault().sendTextMessage(destinationAddress, null,
				smsText, null, null);
	}

	public static SmsMessage[] getSmsData(Context context, Intent smsIntent) {
		SmsMessage[] smsMessages = getMessagesFromIntent(smsIntent);

		for (SmsMessage smsMessage : smsMessages) {
			String address = smsMessage.getOriginatingAddress();
			String body = smsMessage.getMessageBody();
			long timestamp = smsMessage.getTimestampMillis();

			Toast.makeText(context, address + ", " + body + ", " + timestamp,
					Toast.LENGTH_SHORT).show();
		}

		return smsMessages;
	}

	private static SmsMessage[] getMessagesFromIntent(Intent intent) {
		Object[] messages = (Object[]) intent.getSerializableExtra("pdus");

		int pduCount = messages.length;
		SmsMessage[] msgs = new SmsMessage[pduCount];

		for (int i = 0; i < pduCount; i++) {
			byte[] pdu = (byte[]) messages[i];
			msgs[i] = SmsMessage.createFromPdu(pdu);
		}

		return msgs;
	}

	private static final String smsFormat = "[발신자번호]\t%s\n[발신일자]\t\t\t%s\n[SMS본문]\t\t%s";

	public static CharSequence makeOneString(SmsMessage smsMessage) {
		String retVal = String.format(smsFormat,
				smsMessage.getOriginatingAddress(),
				dateFormat.format(new Date(smsMessage.getTimestampMillis())),
				smsMessage.getDisplayMessageBody());

		return retVal;
	}
}
