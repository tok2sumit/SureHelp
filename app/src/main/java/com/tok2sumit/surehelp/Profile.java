package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    TextView name,name1,name2,name3;
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.fullname);
        name1 = findViewById(R.id.txt_name);
        name2 = findViewById(R.id.txt_name1);
        name3 = findViewById(R.id.txt_name2);

        btn_back = findViewById(R.id.btn_back);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            name.setText(personName);
            name1.setText(personName);
            String email = acct.getEmail();
            name2.setText(email);
            String id = acct.getId();
            name3.setText(id);
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });


    }
}