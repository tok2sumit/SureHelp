package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

public class signup_tab_activity3 extends AppCompatActivity {

    Button button;

//    Variables
    ScrollView scrollView;
    EditText phoneno;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_tab_activity3);

//        Hooks
        scrollView = findViewById(R.id.signup_3rd_screen_scroll_view);
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneno = findViewById(R.id.signup_phone_number);
        button = findViewById(R.id.signup_next_button);

    }


    public void callOTPsignup(View view){
        if (!validatePhoneNumber()){
            return;
        }

        String fullname3 = getIntent().getStringExtra("fullname");
        String username3 = getIntent().getStringExtra("username");
        String email3 = getIntent().getStringExtra("email");
        String password3 = getIntent().getStringExtra("password");
        String dob3 = getIntent().getStringExtra("dob");
        String gender3 = getIntent().getStringExtra("gender");


        countryCodePicker.registerCarrierNumberEditText(phoneno);

        Intent intent = new Intent(getApplicationContext(), activity_otp.class);
//            pass all fields to the next activity which is the otp activity.

        intent.putExtra("fullname",fullname3);
        intent.putExtra("username",username3);
        intent.putExtra("email",email3);
        intent.putExtra("password",password3);
        intent.putExtra("gender",gender3);
        intent.putExtra("dob",dob3);
        intent.putExtra("phoneno",countryCodePicker.getFullNumberWithPlus().replace(" ",""));

        //Add Transition
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signup_tab_activity3.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }


    private boolean validatePhoneNumber() {
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

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), Login_Activity.class));
        finish();
    }

    public void goback(View view) {
        startActivity(new Intent(getApplicationContext(), signup_tab_acitvity2.class));
        finish();
    }
}