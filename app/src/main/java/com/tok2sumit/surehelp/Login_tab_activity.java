package com.tok2sumit.surehelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class Login_tab_activity extends AppCompatActivity {


    //    variables
    TextView textView;
    CountryCodePicker countryCodePicker;
    EditText phoneno, password;
    RelativeLayout progressbar;
    Button cancel_login_button;

    // For SignIn-In Remember me
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Code For Remember Me Sign-In
        checkBox = findViewById(R.id.cb_rem_me);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(Login_tab_activity.this,"Checked",Toast.LENGTH_SHORT).show();
                }else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(Login_tab_activity.this,"UnChecked",Toast.LENGTH_SHORT).show();
                }
            }
        });

        textView = findViewById(R.id.txt_forgot_password);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), forgot_password_activity.class);
                startActivity(intent);
            }
        });

        cancel_login_button = findViewById(R.id.cancel_login_button);
        cancel_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_tab_activity.this,Login_Activity.class);
                startActivity(intent);
            }
        });


//      hooks
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneno = findViewById(R.id.login_phone_number);
        password =findViewById(R.id.et_password);
        progressbar = findViewById(R.id.login_progress_bar);
        progressbar.setVisibility(View.GONE);

    }

//       login the user in app
    public void letTheUserLoggedIn(View view) {
//        validate phoneno and password

        if (!validatephoneno() || !validatePassword()) {
            return;
        }

        progressbar.setVisibility(View.VISIBLE);
//        get data
        String _phoneNumber = phoneno.getText().toString().trim();
        String _password = password.getText().toString().trim();
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

//

                    String systemPassword = snapshot.child(_completePhoneNumber).child("password").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        password.setError(null);
                        password.setEnabled(true);

                        progressbar.setVisibility(View.GONE);
//                    if the user exist with that phoneno
//                    fetch all data of the user
                        String _fullname = snapshot.child(_completePhoneNumber).child("fullname").getValue(String.class);
                        String _email = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);
                        String _phoneno = snapshot.child(_completePhoneNumber).child("phoneno").getValue(String.class);
                        String _dateofBirth = snapshot.child(_completePhoneNumber).child("date").getValue(String.class);
//
                        //Toast.makeText(Login_tab_activity.this, _fullname+_email+_phoneno+_dateofBirth,Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                       intent.putExtra("fullname",_fullname);
                       startActivity(intent);
                    } else {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(Login_tab_activity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(Login_tab_activity.this, "No such user exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(Login_tab_activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validatephoneno() {
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


    private boolean validatePassword() {
        String val = password.getText().toString().trim();
        String checkPassword = "\\A\\w{1,20}\\z";
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=S+$)" +           //no white spaces
////                ".{4,}" +               //at least 4 characters
//                "$";
        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Password should contain 4 characters!");
            return false;
        } else {
            password.setError(null);
            password.setEnabled(false);
            return true;
        }
    }
}