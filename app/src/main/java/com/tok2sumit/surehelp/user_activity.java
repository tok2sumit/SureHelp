package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tok2sumit.surehelp.databinding.ActivityUserBinding;

public class user_activity extends AppCompatActivity {

    ActivityUserBinding binding;
    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean mTimeRunning;
    private long MTimeleftinmilis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_user);
        setContentView(binding.getRoot());
        Intent intent=this.getIntent();
        if(intent!=null){
            String name=intent.getStringExtra("name");
            String phone=intent.getStringExtra("phone");
            String country=intent.getStringExtra("country");
            int imageId=intent.getIntExtra("imageId",R.drawable.aa);

            binding.nameProfile.setText(name);
            binding.phoneProfile.setText(phone);
            binding.countryProfile.setText(country);
            binding.profileImage.setImageResource(imageId);
        }

        startBtn = findViewById(R.id.startbutton);
        mtextview = findViewById(R.id.timer);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimeRunning){
                    stoptimer();
                }
                else{
                    starttimer();
                }
            }
        });

    }
    private void stoptimer(){
        countDownTimer.cancel();
        mTimeRunning=false;
        startBtn.setText("START");
    }
    private void starttimer(){
        final CharSequence value1 = mtextview.getText();
        String num1=value1.toString();
        String num2=num1.substring(0,2);
        String num3=num1.substring(3,5);
        final int number=Integer.valueOf(num2) * 60 + Integer.valueOf(num3);
        MTimeleftinmilis=number*1000;
        countDownTimer=new CountDownTimer(MTimeleftinmilis,1000) {
            @Override
            public void onTick(long l) {
                MTimeleftinmilis= l;
                updateTimer();
            }


            @Override
            public void onFinish() {
                int newvalue=Integer.valueOf(buttonvalue)+1;
                if(newvalue<=7){
                    Intent intent = new Intent(user_activity.this,user_activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);
                }
                else {
                    newvalue=1;
                    Intent intent=new Intent(user_activity.this,user_activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);
                }

            }
        }.start();
        startBtn.setText("PAUSE");
        mTimeRunning=true;

    }
    private void updateTimer(){
        int minutes=(int) MTimeleftinmilis/60000;
        int seconds=(int) MTimeleftinmilis%60000/1000;
        String timeLeftText="";
        if(minutes<10)
            timeLeftText="0";
        timeLeftText=timeLeftText+minutes+":";
        if(seconds<10)
            timeLeftText+="0";
        timeLeftText+=seconds;
        mtextview.setText(timeLeftText);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}