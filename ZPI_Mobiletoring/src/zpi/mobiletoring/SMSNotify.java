package zpi.mobiletoring;

import java.util.regex.Pattern;

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
	static final String REGEX1 = 
			"^(Zdarzenie Alarmowe: Wykrycie Ruchu(Poczatek|Koniec))" +
			" Czas rozpoczecia Alarmu: [0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}" +
			" Nr kanalu wejscia Alarmowego: CAM([0-9]{2}) [[:print:]]+";

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
					String messageBody = messages.getDisplayMessageBody();
					
					messageBody.replaceAll("(\\r|\\n)", " ");
					char[] c = messageBody.toCharArray();
					int pom=0;
					for(int i=0; i<c.length; i++)
					{
						if((int)c[i]==10){
							c[i]=(char)32;
							pom++;
						}
					}
					messageBody=String.valueOf(c);
					
					if(Pattern.matches(SMSNotify.REGEX1, messageBody))
					{
						notify = true;
						int camNoPosition = messageBody.indexOf("CAM") + 3;
						camNo = Integer.parseInt(messageBody.substring(camNoPosition+1, camNoPosition+2));
					}
				}
			}
			if(notify)
			{
				CharSequence tickerText = "ALARM AKTYWOWANY";
				long when = System.currentTimeMillis();
				Notification notification = new Notification(icon, tickerText, when);
				CharSequence contentTitle = "System alarmowy";
				CharSequence contentText = "Kliknij, aby w³¹czyæ podgl¹d";
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