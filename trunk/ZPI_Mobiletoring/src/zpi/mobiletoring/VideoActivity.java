package zpi.mobiletoring;


import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VideoActivity extends Activity implements OnClickListener{

	private String path1 = null;
	private String path2 = null;
	private VideoView mVideoView;
	private Button cam1Btn;
	private Button cam2Btn;
	private ConfHolder cHolder;
	

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.video_view);
		
		cam1Btn= (Button) findViewById(R.id.cam1_btn);
		cam1Btn.setOnClickListener(this);
		cam2Btn= (Button) findViewById(R.id.cam2_btn);
		cam2Btn.setOnClickListener(this);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		
		SharedPreferences adresses= getSharedPreferences(ConfHolder.PREFERENCES_NAME,0);
		
		String host=adresses.getString("HostIp", "");
		String port1=adresses.getString("Port1", "");
		String port2=adresses.getString("Port2", "");
		
		if(!host.equals("")&&!port1.equals("")&&!port2.equals("")){
			ConfHolder cf=ConfHolder.getInstance();
			cf.setCamera1("http://"+host+":"+port1);
			cf.setCamera2("http://"+host+":"+port2);
			playPreview("http://"+host+":"+port1);
		}
	}
	
	public void onResume(Bundle bnd){
		super.onResume();
	}
	
	private void playPreview(String path){
		if(path!=null){
			
			mVideoView.setVideoPath(path);		
			/*MediaController mc=new MediaController(this);
			mVideoView.setMediaController(mc);*/
			
			mVideoView.requestFocus();
			
			mVideoView.setKeepScreenOn(true);
			mVideoView.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
				
				private boolean start=false;
				
				public void onBufferingUpdate(MediaPlayer mp, int arg1) {
					if(!start){
						mp.start();
						start=true;
					}
				}
			});
			mVideoView.setOnPreparedListener(new OnPreparedListener(){

				public void onPrepared(MediaPlayer mp) {
					Log.i("Nasz onPrepared", "Weszlo!");
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
		}
	}

}
