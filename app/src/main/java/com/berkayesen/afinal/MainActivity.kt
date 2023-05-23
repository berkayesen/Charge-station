package com.berkayesen.afinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.berkayesen.afinal.retrofit2.data.api.ApiService
import com.berkayesen.afinal.retrofit2.data.utils.Constants.BASE_URL
import com.berkayesen.afinal.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch
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

        getCurrentData()
        supportActionBar?.title = "180101030"


    }


    private fun getCurrentData(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        //val api = retrofitInstance.create(ApiService::class.java)


        lifecycleScope.launch {
            /*val response = api.getChargeData().awaitResponse()
            if(response.isSuccessful){
                val data = response.body()
                Log.d(TAG,"getCurrentData: $data")
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity,"Beklenmedik bir hata olustu!",Toast.LENGTH_LONG).show()
                }
            }*/

            val result =
                api.getChargeData(key = "c1916f96-601b-4ca9-bfdb-8b5f95eb84e5", output = "json", countryCode = "TR", maxResults = "10")
                    .awaitResponse()
            if(result.isSuccessful){
                val data = result.body()
                Log.d(TAG,"getChargeData:$data")
            }
        }

    }

}