package zpi.mobiletoring;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ConfigurationActivity extends Activity implements OnClickListener{
	
	private Button confirmBtn;
	private ConfHolder cHolder;
	private EditText hostIpET;
	private EditText port1ET;
	private EditText port2ET;
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.conf);
		
		confirmBtn= (Button) findViewById(R.id.confirm_btn);
		hostIpET= (EditText) findViewById(R.id.host_name_edit);
		port1ET= (EditText) findViewById(R.id.port1_edit);
		port2ET= (EditText) findViewById(R.id.port2_edit);
		
		confirmBtn.setOnClickListener(this);
		
		SharedPreferences adresses= getSharedPreferences(ConfHolder.PREFERENCES_NAME,0);
		String host=adresses.getString("HostIp", "");
		String port1=adresses.getString("Port1", "");
		String port2=adresses.getString("Port2", "");
		
		hostIpET.setText(host);
		port1ET.setText(port1);
		port2ET.setText(port2);
		
		
		Log.i("CONF", "saved host:"+host);
		Log.i("CONF", "saved port1:"+port1);
		Log.i("CONF", "saved port2:"+port2);
		
	}

	public void onClick(View v) {
		
		if(v.getId() == R.id.confirm_btn){
			
			String hostIp=hostIpET.getText().toString();
			
			String fullPath1= "http://"+hostIp;
			String fullPath2= "http://"+hostIp;
			
			String port1=port1ET.getText().toString();
			String port2=port2ET.getText().toString();
			
			fullPath1+=":"+port1;
			fullPath2+=":"+port2;
			
			Log.i("CONF",fullPath1);
			Log.i("CONF",fullPath2);
			
			//sending adresses to VideoActivity
			cHolder= ConfHolder.getInstance();
			cHolder.setCamera1(fullPath1);
			cHolder.setCamera2(fullPath2);
			
			//data storage
			SharedPreferences adresses= getSharedPreferences(ConfHolder.PREFERENCES_NAME,0);
			Editor editor=adresses.edit();
			editor.putString("HostIp", hostIp);
			editor.putString("Port1", port1);
			editor.putString("Port2", port2);
			editor.commit();
			
		}else{
			Log.i("UPS!","Coœ posz³o nie tak");
		}
		
	}


	
	
}