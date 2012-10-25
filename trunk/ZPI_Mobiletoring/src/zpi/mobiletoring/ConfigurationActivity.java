package zpi.mobiletoring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ConfigurationActivity extends Activity implements OnClickListener{
	
	private Button confirmBtn;
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.conf);
		confirmBtn= (Button) findViewById(R.id.confirm_btn);
		confirmBtn.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		
		if(v.equals(findViewById(R.id.confirm_btn))){
			
			//TODO: ³¹czenie pól adresu i portów i przekazanie do VideoActivity
			
		}
		
	}


	
	
}
