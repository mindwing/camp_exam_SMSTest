package kr.mindwing.camp_exam_smstest;

import android.telephony.SmsManager;

public class SmsUtil {
	public static void sendSms(String destinationAddress, String smsText) {
		SmsManager.getDefault().sendTextMessage(destinationAddress, null,
				smsText, null, null);
	}
}