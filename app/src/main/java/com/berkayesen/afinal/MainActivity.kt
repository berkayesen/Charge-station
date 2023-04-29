package com.berkayesen.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var  buttonLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        supportActionBar?.title = "Your Student ID"

        buttonLogin.setOnClickListener{
            val intent = Intent(this,SearchScreenActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initViews(){
        buttonLogin = findViewById(R.id.buttonLogin)
    }

}