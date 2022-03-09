package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class success_message_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_message);
    }
    public void backToLogin(View view) {
        startActivity(new Intent(getApplicationContext(), Login_Activity.class));
        finish();
    }
}