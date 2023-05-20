package com.berkayesen.afinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.berkayesen.afinal.retrofit2.data.api.ApiService
import com.berkayesen.afinal.viewmodel.MainActivityViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
    }

    private lateinit var  buttonLogin: Button
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getCurrentData()
        supportActionBar?.title = "180101030"

        buttonLogin.setOnClickListener{
            val intent = Intent(this,SearchScreenActivity::class.java)
            startActivity(intent)
        }

    }
    private fun initViews(){
        buttonLogin = findViewById(R.id.buttonLogin)

    }

    private fun getCurrentData(){
        val retrofitInstance = Retrofit.Builder()
            .baseUrl("https://api.openchargemap.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofitInstance.create(ApiService::class.java)


        lifecycleScope.launch {
            val response = api.getChargeData().awaitResponse()
            //val response = client.newCall(request).execute()
            if(response.isSuccessful){
                val data = response.body()
                Log.d(TAG,"getCurrentData: $data")
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity,"Beklenmedik bir hata olustu!",Toast.LENGTH_LONG).show()
                }
            }

        }

    }

}