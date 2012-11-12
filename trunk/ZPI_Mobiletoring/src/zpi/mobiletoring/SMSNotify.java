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
/**
 * Usluga dzialajaca w tle, monitoruje nadchodzace wiadomosci SMS- jesli ktoras z nich to wiadomosc z systemu 
 * monitoringu, to przetwarza ja na wygodna w uzyciu notyfikacje dla uzytkownika.
 * @author Mobiletoring Team
 *
 */
public class SMSNotify extends BroadcastReceiver
{
	private static final String LOG_TAG = "SMSReceiver";
	public static final int NOTIFICATION_ID_RECEIVED = 0x1221;
	static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	static final String FROM = "Bramka SMS";
	static final int icon = R.drawable.ic_launcher;
	private boolean notify = false;
	private int camNo;
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{	
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		if(intent.getAction().equals(SMSNotify.ACTION))
		{
			Bundle bundle = intent.getExtras();
			if(bundle != null)
			{
				Object[] pdus = (Object[]) bundle.get("pdus");
				for(Object pdu : pdus)
				{
					SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdu);
					if(messages.getDisplayOriginatingAddress().equals(SMSNotify.FROM))
					{
						notify = true;
						String messageBody = messages.getDisplayMessageBody();
						int camNoPosition = messageBody.indexOf("CAM") + 3;
						camNo = Integer.parseInt(messageBody.substring(camNoPosition, camNoPosition+2));
					}
				}
			}
			if(notify)
			{
				CharSequence tickerText = "ALARM AKTYWOWANY";
				long when = System.currentTimeMillis();
				Notification notification = new Notification(icon, tickerText, when);
				CharSequence contentTitle = "System alarmowy";
				CharSequence contentText = "Kliknij, aby w��czy� podgl�d";
				Intent notificationIntent = new Intent(context, TabHostActivity.class);
				notificationIntent.putExtra("camNo", camNo);
				PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
				notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
				mNotificationManager.notify(NOTIFICATION_ID_RECEIVED, notification);
				notify = false;
			}
		}
	}
}