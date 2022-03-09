package com.tok2sumit.surehelp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

private lateinit var btn_login:Button

class HomescreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        btn_login = findViewById(R.id.btn_login)
        btn_login.setOnClickListener{
            startActivity(Intent(this@HomescreenActivity,Login_Activity::class.java))
        }
    }
}