package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class set_new_password_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
    }
    public void setNewPasswordBtn(View view){
        startActivity(new Intent(getApplicationContext(),success_message_activity.class));
        finish();
    }
}