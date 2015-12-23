package damroo.spinno.com.damroo.videoview;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.greenfrvr.rubberloader.RubberLoaderView;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.API_URL;
import damroo.spinno.com.damroo.R;
import damroo.spinno.com.damroo.home.HomeConstants;
import damroo.spinno.com.damroo.home.QuestionActivity;

public class VideoViewActivity extends Activity {

    VideoView videoview;

   @Bind(R.id.loader)RubberLoaderView loader;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        ButterKnife.bind(this);
        // Find your VideoView in your video_main.xml layout
        videoview = (VideoView) findViewById(R.id.VideoView);
        // Execute StreamVideo AsyncTask

      loader.startLoading();
      loader.setVisibility(View.VISIBLE);

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(VideoViewActivity.this);
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL
            String videolink = API_URL.VIDEO_BASE_URI.concat(HomeConstants.VideoLink) ;
            videolink=videolink.replace(" ","%20");
            Uri video = Uri.parse(videolink);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);

            videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    //Start a new activity or do whatever else you want here
                    Intent in = new Intent(VideoViewActivity.this,QuestionActivity.class);
                    startActivity(in);
                    finish();
                }
            });

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                loader.setVisibility(View.GONE);
                videoview.start();
            }
        });

    }

}
