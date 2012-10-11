package zpi.mobiletoring;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity implements SurfaceHolder.Callback{

	MediaController mediaControl;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_video);
        
        String path="http://metaloplastyka.eu/pliki/Wildlife.mp4";
        
        VideoView vv= (VideoView) this.findViewById(R.id.videoView1);
        mediaControl=new MediaController(this);
        
        vv.setMediaController(mediaControl);
        vv.setVideoPath(path);
        vv.requestFocus();
        
        vv.start();
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
        return true;
    }

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
    
}
