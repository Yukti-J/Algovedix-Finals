package com.vishishta.algovedixnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PhysicianLoginActivity : AppCompatActivity() {
    lateinit var buttonToLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physician_login)

        intent = Intent(this, PhysicianActivity::class.java)
        startActivity(intent)
    }
}