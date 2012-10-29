package zpi.mobiletoring;

import android.app.Activity;
import android.content.SharedPreferences;

public final class ConfHolder {
	
	public static final String PREFERENCES_NAME="LastVideoPreferences";
	
    private final static ConfHolder ourInstance = new ConfHolder();
    private String camera1="";
    private String camera2="";
    
    public static ConfHolder getInstance() {
        return ourInstance;
    }
 
    //¿eby unikn¹æ automatycznego tworzenia domyœlnego, publicznego, bezargumentowego konstruktora
    private ConfHolder() {
    }

	public String getCamera1() {
		return camera1;
	}

	public void setCamera1(String camera1) {
		this.camera1 = camera1;
	}

	public String getCamera2() {
		return camera2;
	}

	public void setCamera2(String camera2) {
		this.camera2 = camera2;
	}
}
