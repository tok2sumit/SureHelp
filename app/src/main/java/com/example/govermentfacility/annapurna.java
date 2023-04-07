package com.example.govermentfacility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

public class annapurna extends AppCompatActivity {
    TextToSpeech t;
    int speak = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annapurna);


        t= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){

                    t.setLanguage(Locale.US);

                }
            }
        });


    }

    public void TTS(View v){

        if(speak==0){
            speak=1;
            String toSpeak = getResources().getString(R.string.annapurnaText);
            t.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);

        }else{
            speak=0;
            if(t !=null){
                t.stop();

            }

        }

    }


}