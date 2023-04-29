package com.berkayesen.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SearchScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_screen)

        supportActionBar?.title = "Search Charging Stations"
    }
}