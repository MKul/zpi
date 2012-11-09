package zpi.mobiletoring;


import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Aktywnosc umozliwiajaca podglad z kamer.
 * @author Mobiletoring Team
 *
 */
public class VideoActivity extends Activity implements OnClickListener{

	private String path1 = null;
	private String path2 = null;
	private VideoView mVideoView;
	private Button cam1Btn;
	private Button cam2Btn;
	private Button catchBtn;
	private ConfHolder cHolder;
	private ProgressBar pb;
	private File dir;
	

	@Override
	public void onCreate(Bundle icicle) {
			
			//Toast.makeText(this, ""+onCreateCount, Toast.LENGTH_SHORT).show();
			super.onCreate(icicle);
		
			setContentView(R.layout.videoview);
			
			dir = new File(Environment.getExternalStorageDirectory()+"/AlarmScreenShots");
			if(!dir.exists()){
        		dir.mkdir();
        	}
			
			cam1Btn= (Button) findViewById(R.id.cam1_btn);
			cam1Btn.setOnClickListener(this);
			cam2Btn= (Button) findViewById(R.id.cam2_btn);
			cam2Btn.setOnClickListener(this);
			catchBtn= (Button) findViewById(R.id.catch_btn);
			catchBtn.setOnClickListener(this);
			mVideoView = (VideoView) findViewById(R.id.surface_view);
			
			SharedPreferences adresses= getSharedPreferences(ConfHolder.PREFERENCES_NAME,0);
			
			String host=adresses.getString("HostIp", "");
			String port1=adresses.getString("Port1", "");
			String port2=adresses.getString("Port2", "");
			
			if( !host.equals("")&&( !port1.equals("")||!port2.equals("") ) ){
				ConfHolder cf=ConfHolder.getInstance();
				cf.setCamera1("http://"+host+":"+port1);
				cf.setCamera2("http://"+host+":"+port2);
				playPreview(cf.getCamera1());
			}
	
			//Log.i("CONF","ON CREATE DO DIASKA!");
	}
	
	/**
	 * Metoda, wywolywana w celu odtwarzania strumienia, znajdujacego sie pod podanym adresem URL.
	 * @param path - adres strumienia
	 */
	private void playPreview(String path){
		if(path!=null){
			
			pb= (ProgressBar) findViewById(R.id.progress_circle);
			pb.setVisibility(ProgressBar.VISIBLE);
			
			mVideoView.setVideoPath(path);		
			mVideoView.requestFocus();
			
			mVideoView.setKeepScreenOn(true);
			mVideoView.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
				
				private boolean start=false;
				
				public void onBufferingUpdate(MediaPlayer mp, int arg1) {
					if(!start){
						mp.start();
						start=true;
						pb.setVisibility(ProgressBar.INVISIBLE);
					}
				}
			});
			mVideoView.setOnPreparedListener(new OnPreparedListener(){

				public void onPrepared(MediaPlayer mp) {
					//Log.i("Nasz onPrepared", "Weszlo!");
					mVideoView.start();
				}
				
			});
		}
	}

	public void onClick(View arg0) {
		
		if(arg0.getId() == R.id.cam1_btn){
			cHolder= ConfHolder.getInstance();
			path1=cHolder.getCamera1();
			playPreview(path1);
		}else if(arg0.getId() == R.id.cam2_btn){
			cHolder= ConfHolder.getInstance();
			path2=cHolder.getCamera2();
			playPreview(path2);
		}else if(arg0.getId() == R.id.catch_btn){
			gettingScreenShot();
		}
	}
	
	private void gettingScreenShot(){
		
		View content = findViewById(R.id.video);
        content.setDrawingCacheEnabled(true);
        
        // this is the important code :)  
        // Without it the view will have a dimension of 0,0 and the bitmap will be null          
        content.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
	                 MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        content.layout(0, 0, content.getMeasuredWidth(), content.getMeasuredHeight()); 
	
        content.buildDrawingCache(true);
	    Bitmap bitmap = Bitmap.createBitmap(content.getDrawingCache());
	    content.setDrawingCacheEnabled(false); // clear drawing cache

	    Long time=System.currentTimeMillis();
	    Calendar cal=Calendar.getInstance();
	    cal.setTimeInMillis(time);
	    DateFormat df=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
	    
        //File file = new File(dir.getPath()+"/"+df.format(cal.getTime())+".png");
	    File file = new File(dir.getPath()+"/"+time+".png");
        try 
        {
        	
        	Toast.makeText(this, "Prepare: "+Environment.getExternalStorageDirectory()+"/AlarmScreenShots/test.png", Toast.LENGTH_LONG).show();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.PNG, 100, ostream);
            
            ostream.flush();
            ostream.close();
            
            Toast.makeText(this, "GET", Toast.LENGTH_LONG).show();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
	}

}
