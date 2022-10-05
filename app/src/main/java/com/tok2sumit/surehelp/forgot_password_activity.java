package com.tok2sumit.surehelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class forgot_password_activity extends AppCompatActivity {
//    Variables
    Button forget_password_next_btn;

    private ImageView screenIcon;
    private TextView title,description;
    private EditText phoneno;
    private CountryCodePicker countryCodePicker;
    private Button nextBtn;
    private Animation animation;
    ProgressBar progressBar;
    ImageView forget_password_back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

//        Hooks
        phoneno = findViewById(R.id.forgot_phone_number);
        screenIcon = findViewById(R.id.forget_password_icon);
        title = findViewById(R.id.forget_password_title);
        description = findViewById(R.id.forget_password_description);
        countryCodePicker = findViewById(R.id.country_code_picker);
        nextBtn = findViewById(R.id.forget_password_next_btn);
        progressBar = findViewById(R.id.progress_bar);

//        Animation hook
        animation = AnimationUtils.loadAnimation(this,R.anim.slide_animation);
//        Set animation to all the elements
        screenIcon.setAnimation(animation);
        title.setAnimation(animation);
        description.setAnimation(animation);
        countryCodePicker.setAnimation(animation);
        nextBtn.setAnimation(animation);

//        Call the otp screen and pass phoneno Number
//        for verification
        progressBar.setVisibility(View.GONE);

        forget_password_back_btn = findViewById(R.id.forget_password_back_btn);
        forget_password_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login_tab_activity.class);
                startActivity(intent);
            }
        });

    }

    public void verifyPhoneNumber(View view){
        if (!validatePhoneNumber()){
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        String _phoneNumber = phoneno.getText().toString().trim();
        if (_phoneNumber.charAt(0) == 0) {
            _phoneNumber = _phoneNumber.substring(1);
        }

        String _completePhoneNumber = "+" + countryCodePicker.getFullNumber() + _phoneNumber;

//        Database
        Query chechUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneno").equalTo(_completePhoneNumber);

        chechUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    phoneno.setError(null);
                    phoneno.setEnabled(true);

                    Intent intent = new Intent(getApplicationContext(),forgot_otp.class);
                    intent.putExtra("phoneno", _completePhoneNumber);
//                    intent.putExtra("whattodo","updateData");
                    startActivity(intent);
                    finish();
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(forgot_password_activity.this, "No such user Exist!", Toast.LENGTH_SHORT).show();
                }
        }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(forgot_password_activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validatePhoneNumber(){
        String val = phoneno.getText().toString().trim();
//        String checkspaces = "Aw{1,10}z";
        if (val.isEmpty()) {
            phoneno.setError("Enter valid phone number");
            return false;
        }
//        else if (!val.matches(checkspaces)) {
//            phoneno.setError("No White spaces are allowed!");
//            return false;
//        }
        else {
            phoneno.setError(null);
            phoneno.setEnabled(false);
            return true;
        }
    }
}