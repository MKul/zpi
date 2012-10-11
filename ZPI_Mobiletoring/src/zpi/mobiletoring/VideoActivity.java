package zpi.mobiletoring;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VideoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_video, menu);
        return true;
    }
}
