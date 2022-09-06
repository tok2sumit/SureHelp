package com.tok2sumit.surehelp;

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
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;
import com.tok2sumit.surehelp.Database.CheckInternet;

public class set_new_password_activity extends AppCompatActivity {
// Variables
private ImageView screenIcon;
    private TextView title,description;
    private EditText password,ConfirmPassword;
    private Button nextBtn;
    private Animation animation;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

//        Hooks
        screenIcon = findViewById(R.id.set_new_password_icon);
        title = findViewById(R.id.set_new_password_title);
        description = findViewById(R.id.set_new_password_description);
        password = findViewById(R.id.new_password);
        ConfirmPassword = findViewById(R.id.conferm_password);
        nextBtn = findViewById(R.id.set_new_password_btn);
        progressBar = findViewById(R.id.progressbar);


//        Animation hook
        animation = AnimationUtils.loadAnimation(this,R.anim.slide_animation);
//        Set animation to all the elements
        screenIcon.setAnimation(animation);
        title.setAnimation(animation);
        description.setAnimation(animation);
        nextBtn.setAnimation(animation);

        progressBar.setVisibility(View.GONE);
    }


    public void setNewPasswordBtn(View view){
//        check Internet Connection
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)){
//            showCustomDialog();
            return;
        }

//        validate Password
        if (!validatePassword() || !validateConfirmPassword()){
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

//        Get data from fields;
        String _newPassword = password.getText().toString().trim();
        String _phoneNumber = getIntent().getStringExtra("phoneno");

//        Update data in Firebase and in Sessions
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(_phoneNumber).child("password").setValue(_newPassword);

        startActivity(new Intent(getApplicationContext(),success_message_activity.class));
        finish();
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

    private boolean validateConfirmPassword() {
        String val = ConfirmPassword.getText().toString().trim();
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
            ConfirmPassword.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            ConfirmPassword.setError("Password should contain 4 characters!");
            return false;
        } else {
            ConfirmPassword.setError(null);
            ConfirmPassword.setEnabled(false);
            return true;
        }
    }

}