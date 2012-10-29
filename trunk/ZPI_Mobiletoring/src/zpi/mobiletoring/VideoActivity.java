package zpi.mobiletoring;


import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.Intent;
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
		
	}
	
	public void onResume(Bundle bnd){
		super.onResume();
		
		cHolder= ConfHolder.getInstance();
		path1=cHolder.getCamera1();
		Log.i("CONF",path1);
		playPreview(path1);
	}
	
	private void playPreview(String path){
		if(path!=null){
			
			mVideoView.setVideoPath(path);		
			mVideoView.setMediaController(new MediaController(this));
			mVideoView.requestFocus();
		}
	}

	public void onClick(View arg0) {
		
		if(arg0.getId() == R.id.cam1_btn){
			cHolder= ConfHolder.getInstance();
			path1=cHolder.getCamera1();
			Log.i("CONF",path1);
			playPreview(path1);
		}else if(arg0.getId() == R.id.cam2_btn){
			cHolder= ConfHolder.getInstance();
			path2=cHolder.getCamera2();
			Log.i("CONF",path2);
			playPreview(path2);
		}
		
		
	}
	
	

}
