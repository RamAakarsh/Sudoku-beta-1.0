package com.aakarsh.sudoku;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class mainActivity extends Activity {

    private VideoView mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //loading the video into VideoView from resources
        mVideoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.game);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);

            }
        });


        //play button to navigate to levelActivity where we select different levels to play
        Button login = (Button) findViewById(R.id.button_play);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), levelActivity.class);
                startActivity(intent1);
            }
        });
    }


        //terms and conditions to use the application.(used a random pdf file)
        public void go_terms(View view) {
            TextView t2 = (TextView) findViewById(R.id.term);
            t2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pdf995.com/samples/pdf.pdf"));
                    startActivity(intent1);
                }


            });
        }


        //privacy conditions for using the application
         public void go_privacy(View view)
        {
            TextView t3 = (TextView) findViewById(R.id.privacy);
            t3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pdf995.com/samples/pdf.pdf"));
                    startActivity(intent3);
                }


            });
        }
}
