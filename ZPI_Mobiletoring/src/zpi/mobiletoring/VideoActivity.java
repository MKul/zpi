package zpi.mobiletoring;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.os.Bundle;

public class VideoActivity extends Activity{

	private String path = "http://192.168.80.18:8080";
	private VideoView mVideoView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.video_view);
		
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		mVideoView.setVideoPath(path);
		
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.requestFocus();
	}

}
