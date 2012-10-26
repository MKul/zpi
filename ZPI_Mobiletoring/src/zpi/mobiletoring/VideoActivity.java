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

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.video_view);
		
		cam1Btn= (Button) findViewById(R.id.cam1_btn);
		
		cam2Btn= (Button) findViewById(R.id.cam2_btn);
		
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		
	}
	
	public void onResume(Bundle bnd){
		super.onResume();
		Bundle extras=getIntent().getExtras();
		path1=extras.getString("path1");
		Log.i("PATH","ADRES:"+path1);
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
			
		}else if(arg0.getId() == R.id.cam2_btn){
			
		}
		
		
	}
	
	

}
