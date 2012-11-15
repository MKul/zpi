package zpi.mobiletoring;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
/**
 * Glowna aktywnosc, zawierajaca menu w formie zakladek.
 * 
 * @author Mobiletoring Team
 *
 */
public class TabHostActivity extends TabActivity {
	
	private Intent camerasIntent;
	private Intent confIntent;
	private Intent helpIntent;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        TabHost tabHost = getTabHost();
 
        // Tab for Cameras
        TabSpec camerasTabSpec = tabHost.newTabSpec("Cameras");
        camerasTabSpec.setIndicator("Kamery", getResources().getDrawable(R.drawable.icon_cam_tab));
        camerasIntent = new Intent(this, VideoActivity.class);
        camerasTabSpec.setContent(camerasIntent);
        
        int camNo=this.getIntent().getIntExtra("camNo", 1);
        camerasIntent.putExtra("camNo", camNo);
        
        // Tab for Configuration
        TabSpec confTabSpec = tabHost.newTabSpec("Configuration");
        confTabSpec.setIndicator("Konfiguracja", getResources().getDrawable(R.drawable.icon_conf_tab));
        confIntent = new Intent(this, ConfigurationActivity.class);
        confTabSpec.setContent(confIntent);
 
        // Tab for Help
        TabSpec helpTabSpec = tabHost.newTabSpec("Help");
        helpTabSpec.setIndicator("Pomoc", getResources().getDrawable(R.drawable.icon_help_tab));
        helpIntent = new Intent(this, HelpActivity.class);
        helpTabSpec.setContent(helpIntent);
 
        // Adding all TabSpec to TabHost
        
        tabHost.addTab(camerasTabSpec); 
        tabHost.addTab(confTabSpec);
        tabHost.addTab(helpTabSpec); 
        
        tabHost.setCurrentTabByTag("Cameras");
        
        
    }	
}
