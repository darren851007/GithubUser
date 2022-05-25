package com.example.github

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.github.api.ApiClientManager
import com.example.github.api.ApiService
import com.example.github.databinding.ActivityDetailUserBinding
import com.example.github.model.data.Data
import com.example.github.model.data.DetailUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.jvm.Throws

class DetailUserActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var userDetailList: List<DetailUser>
    private val apiService = ApiClientManager.retrofit.create(ApiService::class.java)



    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        if (username != null) {
            setUserDetail(username)
        }
        binding.ivClose.setOnClickListener {
            val i= Intent(this, MainActivity::class.java)
            startActivity(i)
        }


    }


    private fun setUserDetail (userName: String){
        apiService
            .getUserDetail(userName)
            .enqueue(object : Callback <DetailUser>{
                override fun onResponse(
                    call: Call <DetailUser>,
                    response: Response<DetailUser>) {

                    if(response.isSuccessful){
                        Log.e("Detail Success", "${response.body().toString()}")
//                        Log.e("Detail Data", "${response.body()?.login.toString()}")
                        setView(response)
                    }
                }

                override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                    Log.e("Detail Failure", t.message.toString())
                }


            })

    }

    private fun setView(response: Response<DetailUser>){
        if(response.body()?.name.toString()!="null"){
            binding.tvUesrName.text = response.body()?.name.toString()
        }

        if(response.body()?.bio.toString()!="null"){
            binding.tvBio.text = response.body()?.bio.toString()
        }

        Glide.with(binding.ivUser)
            .load(response.body()?.avatarUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .circleCrop()
            .into(binding.ivUser)

        if(response.body()?.login.toString()!="null"){
            binding.tvLogin.text = response.body()?.login.toString()
        }

        if (response.body()?.siteAdmin !=false) {
            binding.tvDetailStaff.visibility = View.VISIBLE
        }
        else binding.tvDetailStaff.visibility = View.INVISIBLE

        if(response.body()?.location.toString()!="null"){
            binding.tvLocation.text = response.body()?.location.toString()
        }

        if(response.body()?.blog.toString()!="null"){
            binding.tvUrl.text = response.body()?.blog.toString()
        }

    }


}




