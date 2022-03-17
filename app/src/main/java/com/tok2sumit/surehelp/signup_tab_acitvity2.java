package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class signup_tab_acitvity2 extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText, slideText;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_tab_activity2);

        //Hooks
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);
        radioGroup = findViewById(R.id.radio_group);
        datePicker = findViewById(R.id.age_picker);

//        button.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), signup_tab_activity3.class);
//            startActivity(intent);
//        });
    }


    public void call3rdSigupScreen(View view) {
        if (!validateGender() | !validateAge()) {
            return;
        }

//        getting data of radioButton and Age.
        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String _gender = selectedGender.getText().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String _date = day+"/"+month+"/"+year;

        String fullname2 = getIntent().getStringExtra("fullname");
        String username2 = getIntent().getStringExtra("username");
        String email2 = getIntent().getStringExtra("email");
        String password2 = getIntent().getStringExtra("password");



        Intent intent = new Intent(getApplicationContext(), signup_tab_activity3.class);

        intent.putExtra("fullname",fullname2);
        intent.putExtra("username",username2);
        intent.putExtra("email",email2);
        intent.putExtra("password",password2);
        intent.putExtra("dob",_date);
        intent.putExtra("gender",_gender);

        //Add Transition and call next activity
        Pair[] pairs = new Pair[6];
        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[4] = new Pair<View, String>(titleText, "transition_title_text2");
        pairs[5] = new Pair<View, String>(slideText, "transition_slide_text");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signup_tab_acitvity2.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }


    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 18) {
            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }


    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), Login_Activity.class));
        finish();
    }

    public void goback(View view) {
        startActivity(new Intent(getApplicationContext(),signup_tab_activity1.class));
        finish();
    }
}