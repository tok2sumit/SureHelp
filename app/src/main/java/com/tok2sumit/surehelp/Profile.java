package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    TextView fullname,username,email,id;
    Button btn_back;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.txt_username);
        email = findViewById(R.id.txt_email);
        id = findViewById(R.id.txt_id);
        btn_back = findViewById(R.id.btn_back);

        profile = findViewById(R.id.img_profile_image);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (getIntent().getStringExtra("fullname")!=null){
            String full_name = getIntent().getStringExtra("fullname");
            String user_name = getIntent().getStringExtra("username");
            String email_id = getIntent().getStringExtra("email");
            String id_ = "NULL";

            Toast.makeText(Profile.this, user_name+" "+email_id, Toast.LENGTH_SHORT).show();

            fullname.setText(full_name);
            username.setText(user_name);
            email.setText(email_id);
            id.setText(id_);

        }else if(acct!=null){
            String personName = acct.getDisplayName();
            fullname.setText(personName);
            fullname.setText(personName);
            String username = acct.getDisplayName();
            String email_id = acct.getEmail();
            email.setText(email_id);
            String id_= acct.getId();
            id.setText(id_);

            String imgurl = acct.getPhotoUrl().toString();
            Glide.with(this).load(imgurl).into(profile);
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