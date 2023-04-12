package com.tok2sumit.surehelp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class food extends AppCompatActivity {

    TextToSpeech t;
    int speak = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        t= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){

                    t.setLanguage(Locale.US);

                }
            }
        });
    }
    public void TTS(View view) {
        if(speak==0){
            speak=1;
            String toSpeak = getResources().getString(R.string.foodText);
            t.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);

        }else{
            speak=0;
            if(t !=null){
                t.stop();

            }

        }
    }
}