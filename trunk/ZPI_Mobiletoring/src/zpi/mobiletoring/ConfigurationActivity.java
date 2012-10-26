package zpi.mobiletoring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ConfigurationActivity extends Activity implements OnClickListener{
	
	private Button confirmBtn;
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.conf);
		confirmBtn= (Button) findViewById(R.id.confirm_btn);
		confirmBtn.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		
		if(v.getId() == R.id.confirm_btn){
			
			EditText hostIpET= (EditText) findViewById(R.id.host_name_edit);
			EditText port1ET= (EditText) findViewById(R.id.port1_edit);
			EditText port2ET= (EditText) findViewById(R.id.port2_edit);
			
			String fullPath1= hostIpET.getText().toString();
			String fullPath2= hostIpET.getText().toString();
			fullPath1+=":"+port1ET.getText().toString();
			fullPath2+=":"+port2ET.getText().toString();
			
			Log.i("CONF",fullPath1);
			Log.i("CONF",fullPath2);
			
			Intent sendConf=new Intent(this,VideoActivity.class);
			sendConf.putExtra("path1", fullPath1);
			sendConf.putExtra("path2", fullPath2);
			
		}else{
			
		}
		
	}


	
	
}