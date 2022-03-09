package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class forgot_password_activity extends AppCompatActivity {
    Button forget_password_next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forget_password_next_btn = findViewById(R.id.forget_password_next_btn);
        forget_password_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),set_new_password_activity.class);
                startActivity(intent);
            }
        });
    }


}