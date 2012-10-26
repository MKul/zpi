package zpi.mobiletoring;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
	{	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		if(intent.getAction().equals(SMSNotify.ACTION))
		{
			StringBuilder sb = new StringBuilder();
			String from = new String();
			String body = new String();
			Bundle bundle = intent.getExtras();
			if(bundle != null)
			{
				Object[] pdus = (Object[]) bundle.get("pdus");
				for(Object pdu : pdus)
				{
					SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdu);
					from = messages.getDisplayOriginatingAddress();
					body = messages.getDisplayMessageBody();
					sb.append("Otrzymano SMS\nOd: ");
					sb.append(messages.getDisplayOriginatingAddress());
					sb.append("\n-----Wiadomoœæ-----\n");
					sb.append(messages.getDisplayMessageBody());
				}
			}
			int icon = R.drawable.ic_launcher;
			CharSequence tickerText = "Nowa wiadomoœæ od " + from + ": " + body;
			long when = System.currentTimeMillis();
			Notification notification = new Notification(icon, tickerText, when);
			CharSequence contentTitle = "Nowa wiadomoœæ SMS";
			CharSequence contentText = sb.toString();
			Intent notificationIntent = new Intent(context, TabHostActivity.class);
			PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
			notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
			mNotificationManager.notify(NOTIFICATION_ID_RECEIVED, notification);
		}
	}
}
