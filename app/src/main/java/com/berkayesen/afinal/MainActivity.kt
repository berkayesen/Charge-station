package com.berkayesen.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.berkayesen.afinal.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var  buttonLogin: Button
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        supportActionBar?.title = "180101030"

        buttonLogin.setOnClickListener{
            val intent = Intent(this,SearchScreenActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initViews(){
        buttonLogin = findViewById(R.id.buttonLogin)
    }

}