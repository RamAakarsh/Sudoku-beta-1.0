package com.aakarsh.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;


public class levelActivity extends Activity implements TextToSpeech.OnInitListener {

    TextToSpeech talker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);


        //selecting easy level and creating sudko grids with 35 numbers filled
        Button easy = (Button) findViewById(R.id.easy);
        easy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent easy_intent = new Intent(getApplicationContext(), sudokuActivity.class);
                easy_intent.putExtra("level", 35);
                startActivity(easy_intent);
            }
        });

        //selecting medium level and creating sudko grids with 30 numbers filled
        Button medium = (Button) findViewById(R.id.medium);
        medium.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent medium_intent = new Intent(getApplicationContext(), sudokuActivity.class);
                medium_intent.putExtra("level", 30);
                startActivity(medium_intent);
            }
        });

        //selecting hard level and creating sudko grids with 25 numbers filled
        Button hard = (Button) findViewById(R.id.hard);
        hard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent hard_intent = new Intent(getApplicationContext(), sudokuActivity.class);
                hard_intent.putExtra("level", 25);
                startActivity(hard_intent);
            }
        });

        //selecting hard level and creating sudko grids with 25 numbers filled
        Button rules = (Button) findViewById(R.id.rule_button);
        rules.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent rules_intent = new Intent(getApplicationContext(), rulesActivity.class);
                startActivity(rules_intent);
            }
        });

        //constructor for the TextToSpeech class.TextToSpeech(Context context, TextToSpeech.OnInitListener)
        talker = new TextToSpeech(this, this);

    }


    //Called to signal the completion of the TextToSpeech engine initialization.Providing the text here.
    public void onInit(int status) {

        say("Welcome to Sudoku");

    }

    //converting text to speech
    public void say(String text2say) {
        // TODO Auto-generated method stub
        talker.speak(text2say, TextToSpeech.QUEUE_FLUSH, null);

    }

    // to exit the application if back is pressed on levelActivity
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
