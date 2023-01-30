package com.tok2sumit.surehelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class forgot_otp extends AppCompatActivity {

    Button btn_callNextScreenFromOTP;
    ImageView img_forgort_cancel;

    //    pinview variable
    PinView pinfromUser;
    TextView otpDescriptionText;
    public String fullname,phoneno,email,username,password,date,gender,whattodo;
    String codeBySystem;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_otp);

        // Hook for cancel screen
        img_forgort_cancel = findViewById(R.id.img_cancel_forgot_otp1);
        img_forgort_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgot_otp.this,forgot_password_activity.class);
                startActivity(intent);
                finish();
            }
        });

        pinfromUser = findViewById(R.id.pin_view);
        otpDescriptionText = findViewById(R.id.otp_description_text);

//        Initialize firebase Auth
        mAuth = FirebaseAuth.getInstance();

//        Get all the data from Intent
        fullname = getIntent().getStringExtra("fullname");
        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        gender = getIntent().getStringExtra("gender");
        date = getIntent().getStringExtra("dob");
        phoneno = getIntent().getStringExtra("phoneno");
        whattodo = getIntent().getStringExtra("whattodo");

        btn_callNextScreenFromOTP = findViewById(R.id.btn_callNextScreenFromOTP);
        btn_callNextScreenFromOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    updateOldUserData();
            }
        });

        otpDescriptionText.setText("Enter One Time Password Sent on"+phoneno);
        sendVerificationCodeToUser(phoneno);
    }

    private void sendVerificationCodeToUser(String phoneno){
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth) //mAuth is defined on top
                .setPhoneNumber(phoneno)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code!=null){
                        pinfromUser.setText(code);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(forgot_otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };
    //  Verify Code
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(credential);
    }
    //  Sign in the User.
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(activity_otp.this,"Verification Completed.!",Toast.LENGTH_SHORT);
                                updateOldUserData();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(forgot_otp.this,"Verification Not Completed! Try again.",Toast.LENGTH_SHORT);
                            }
                        }
                    }
                });
    }

    private void updateOldUserData() {
        Intent intent = new Intent(forgot_otp.this,set_new_password_activity.class);
        intent.putExtra("phoneno",phoneno);
        startActivity(intent);
        finish();
    }
}