package zpi.mobiletoring;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabHostActivity extends TabActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        TabHost tabHost = getTabHost();
 
        // Tab for Photos
        TabSpec camerasTabSpec = tabHost.newTabSpec("Cameras");
        // setting Title and Icon for the Tab
        camerasTabSpec.setIndicator("Cameras", getResources().getDrawable(R.drawable.icon_cam_tab));
        Intent camerasIntent = new Intent(this, VideoActivity.class);
        camerasTabSpec.setContent(camerasIntent);
 
        // Tab for Songs
        TabSpec confTabSpec = tabHost.newTabSpec("Configuration");
        confTabSpec.setIndicator("Configuration", getResources().getDrawable(R.drawable.icon_conf_tab));
        Intent confIntent = new Intent(this, ConfigurationActivity.class);
        confTabSpec.setContent(confIntent);
 
        // Tab for Videos
        TabSpec helpTabSpec = tabHost.newTabSpec("Help");
        helpTabSpec.setIndicator("Help", getResources().getDrawable(R.drawable.icon_help_tab));
        Intent helpIntent = new Intent(this, HelpActivity.class);
        helpTabSpec.setContent(helpIntent);
 
        // Adding all TabSpec to TabHost
        
        tabHost.addTab(camerasTabSpec); 
        tabHost.addTab(confTabSpec);
        tabHost.addTab(helpTabSpec); 
    }
	
}
