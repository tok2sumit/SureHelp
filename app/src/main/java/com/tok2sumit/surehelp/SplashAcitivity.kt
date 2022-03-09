package com.tok2sumit.surehelp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.airbnb.lottie.LottieAnimationView

class SplashActivity : AppCompatActivity() {

    lateinit var logo:ImageView
    lateinit var backimg:ImageView
    lateinit var lottieanimation:LottieAnimationView

//    creating variable for sharedprefrences
    lateinit var sharedPreferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Setting up the view for the activity*/
        setContentView(R.layout.activity_splash)


        /*This is how we change the activity with a delay of 2000 milliseconds.
        * The delay time can be changed by changing the value of the time in milliseconds
        * Here the activity is displayed for 2 seconds and then the Handler starts the new process after 2 seconds
        * the new task is the one which we write inside the handler.
        * startActivity() is used to start the new activity, whereas finish() is used to destroy the current activity.
        * We use finish() here so the when the user presses back button, she is not redirected to this activity*/
        Handler().postDelayed({
                run {
                sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
                var isFirstTime : Boolean = sharedPreferences.getBoolean("FirstTime",true)

        if (isFirstTime == true){
//                    If we don't use the splash activity and OnboardActivity together then we just have to set then sharedPreferences value to
//                    false.
            var editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("FirstTime",false)
            editor.commit()
            startActivity(Intent(this@SplashActivity, Onboard::class.java))
            finish()
        }else{
            startActivity(Intent(this@SplashActivity, Login_Activity::class.java))
            finish()
        }
            }

        }, 2000)


        //                          SharedPre ... code in java
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                sharedPreferences = getSharedPreferences("SharePref",MODE_PRIVATE);
//                boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);
//                if (isFirstTime){
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("firstTime",false);
//                    editor.commit();
//                }else {
//                    Intent intent = new Intent(Onboard.this,Login_Activity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        },0);
    }

}