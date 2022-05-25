package com.example.github

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.api.ApiClientManager
import com.example.github.api.ApiService
import com.example.github.databinding.ActivityMainBinding
import com.example.github.model.data.Data
import com.example.github.model.data.DetailUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val apiService = ApiClientManager.retrofit.create(ApiService::class.java)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserList()

    }

    private fun getUserList(){
        apiService
            .getPost()
            .enqueue(object : Callback<MutableList<Data>> {
                override fun onResponse(
                    call: Call<MutableList<Data>>,
                    response: Response<MutableList<Data>>
                ) {
                    if(response.isSuccessful){
                        Log.e("Success", "${response.body().toString()}")
                        binding.rvUser.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = RecyclerViewAdapter(response.body()!!,this@MainActivity)
                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<Data>>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("Failure", t.message.toString())
                }
            })


    }

    override fun onItemClick(position: Int, userName: String) {
//        Toast.makeText(this,"Item $position Clicked", Toast.LENGTH_SHORT).show()
        Intent (this@MainActivity, DetailUserActivity::class.java).also {
            it.putExtra(DetailUserActivity.EXTRA_USERNAME, userName )
            startActivity(it)

        }
    }



}

