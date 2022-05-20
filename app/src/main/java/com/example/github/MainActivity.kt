package com.example.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.api.ApiClientManager
import com.example.github.api.ApiService
import com.example.github.databinding.ActivityMainBinding
import com.example.github.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiService = ApiClientManager.retrofit.create(ApiService::class.java)

         apiService.getPost().enqueue(object : Callback<MutableList<Data>> {
            override fun onResponse(
                call: Call<MutableList<Data>>,
                response: Response<MutableList<Data>>
            ) {
                if(response.isSuccessful){
                    Log.e("Success", "${response.body().toString()}")
                    binding.rvUser.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = RecyclerViewAdapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<Data>>, t: Throwable) {
                t.printStackTrace()
                Log.e("Failure", t.message.toString())
            }
        })

    }
}

