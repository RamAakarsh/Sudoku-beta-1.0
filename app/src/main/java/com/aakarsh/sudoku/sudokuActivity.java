package com.aakarsh.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class sudokuActivity extends Activity implements TextToSpeech.OnInitListener {


    TextToSpeech talker;
    private long startTime = 0L;
    //handler to implement timer functionality
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    TextView time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        //retriving the level information from levelActivity
        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 35);

        //create the sudoku grid based on level information
        sudokuController.getInstance().createGrid(this, level);

        //the final status of the suduko game(solved/unsloved)
        boolean final_status = sudokuController.getInstance().sudoku_status;

        //System.out.println("--------------------------------------------"+final_status);

        //to intimate the user if he had solved the puzzle in previous chance
        if (final_status == true) {
            talker = new TextToSpeech(this, this);
        }

        // Redirecting to Home
        Button back = (Button) findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), levelActivity.class);
                startActivity(intent2);
            }
        });

        //timer functionality
        time = (TextView) findViewById(R.id.timer);
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);

    }

    //to stop the timer if the user chooses to navigate to home.
    protected void onPause(Bundle savedInstanceState) {

        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);


    }

    //worker thread to process the timer functionality and update the UI thread
    private Runnable updateTimerThread = new Runnable() {


        public void run() {


            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            time.setText("" + mins + ":" + String.format("%02d", secs));


            customHandler.postDelayed(this, 0);
        }

    };

    //Called to signal the completion of the TextToSpeech engine initialization.Providing the text here.
    public void onInit(int status) {

        say("You can make it again");

    }

    //converting text to speech
    private void say(String text2say) {
        // TODO Auto-generated method stub
        talker.speak(text2say, TextToSpeech.QUEUE_FLUSH, null);

    }
}
