package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class signup_tab_activity1 extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText, slideText;

//    variables for getting the data.
    EditText fullname,email,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_tab_activity1);


        //Hooks for animation
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);

//        Hooks for getting data
        fullname = findViewById(R.id.signup_fullname);
        email = findViewById(R.id.signup_email);
        username = findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);


//        button = findViewById(R.id.signup_next_button);
//        button.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), signup_tab_acitvity2.class);
//            startActivity(intent);
//        });

    }

    private boolean validateFullName(){
        String val = fullname.getText().toString().trim();
        if (val.isEmpty()){
            fullname.setError("Field can not be empty");
            return false;
        }else {
            fullname.setError(null);
            fullname.setEnabled(false);
            return true;
        }
    }

    private boolean validateUsername(){
        String val = username.getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";  // This code says that user can include
//        text from A to z and having spaces will not be neglated from char 1 to 20.
        if (val.isEmpty()){
            username.setError("Field can not be empty");
            return false;
        }else if(val.length()>20){
            username.setError("Username is too large!");
            return false;
        }else if(!val.matches(checkspaces)){
            username.setError("No White spaces are allowed!");
            return false;
        }
        else {
            username.setError(null);
            username.setEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
        String val = email.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            email.setError("Field can not be empty");
            return false;
        }else if(!val.matches(checkEmail)){
            email.setError("Invalid Email!");
            return false;
        }
        else {
            email.setError(null);
            email.setEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){
        String val = password.getText().toString().trim();
        String checkPassword =  "\\A\\w{1,20}\\z";
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=S+$)" +           //no white spaces
////                ".{4,}" +               //at least 4 characters
//                "$";
        if (val.isEmpty()){
            password.setError("Field can not be empty");
            return false;
        }else if(!val.matches(checkPassword)){
            password.setError("Password should contain 4 characters!");
            return false;
        }
        else {
            password.setError(null);
            password.setEnabled(false);
            return true;
        }
    }


    public void callNextSigupScreen(View view) {
        if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()) {
            return;
        }
        String fullname1 = fullname.getText().toString();
        String username1 = username.getText().toString();
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();

        Intent intent = new Intent(getApplicationContext(), signup_tab_acitvity2.class);
        intent.putExtra("fullname",fullname1);
        intent.putExtra("username",username1);
        intent.putExtra("email",email1);
        intent.putExtra("password",password1);



        //Add Shared Animation
        Pair[] pairs = new Pair[6];
        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[4] = new Pair<View, String>(titleText, "transition_title_text2");
        pairs[5] = new Pair<View, String>(slideText, "transition_slide_text");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signup_tab_activity1.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }


    }


    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), Login_Activity.class));
        finish();
    }
}
