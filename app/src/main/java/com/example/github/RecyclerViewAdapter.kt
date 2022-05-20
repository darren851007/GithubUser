package com.example.github

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.github.databinding.UserItemBinding
import com.example.github.model.Data



class RecyclerViewAdapter(private val dataList: MutableList<Data>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {




    inner class RecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val login : TextView = itemView.findViewById(R.id.tv_userName)
        private val avatarUrl : ImageView = itemView.findViewById(R.id.iv_user)
        private val siteAdmin : TextView = itemView.findViewById(R.id.tv_staff)

        fun bindingView(data : Data){
            login.text = data.login
            Glide.with(itemView)
                .load(data.avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .circleCrop()
                .into(avatarUrl)
            if(data.siteAdmin){
                siteAdmin.visibility = View.VISIBLE
            }
            else siteAdmin.visibility = View.INVISIBLE
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.user_item, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindingView(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}

class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){



}


