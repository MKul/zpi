package zpi.mobiletoring;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSNotify extends BroadcastReceiver
{
	private static final String LOG_TAG = "SMSReceiver";
	public static final int NOTIFICATION_ID_RECEIVED = 0x1221;
	static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{	
		if(intent.getAction().equals(SMSNotify.ACTION))
		{
			StringBuilder sb = new StringBuilder();
			Bundle bundle = intent.getExtras();
			if(bundle != null)
			{
				Object[] pdus = (Object[]) bundle.get("pdus");
				for(Object pdu : pdus)
				{
					SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdu);
					sb.append("Otrzymano SMS\nOd: ");
					sb.append(messages.getDisplayOriginatingAddress());
					sb.append("\n-----Wiadomoœæ-----\n");
					sb.append(messages.getDisplayMessageBody());
				}
			}
			Log.i(SMSNotify.LOG_TAG, "[SMSApp] onReceiveIntent: " + sb);
			Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
