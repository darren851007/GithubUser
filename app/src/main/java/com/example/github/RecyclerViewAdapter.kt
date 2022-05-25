package com.example.github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.github.model.data.Data
import org.w3c.dom.Text


class RecyclerViewAdapter(
    private val dataList: MutableList<Data>,
    private val listener: OnItemClickListener
    ) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {


//    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val login: TextView = itemView.findViewById(R.id.tv_userName)
//        private val avatarUrl: ImageView = itemView.findViewById(R.id.iv_user)
//        private val siteAdmin: TextView = itemView.findViewById(R.id.tv_staff)
//
//        fun bindingView(data: Data) {
//
//            login.text = data.login
//            Glide.with(itemView)
//                .load(data.avatarUrl)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .circleCrop()
//                .into(avatarUrl)
//            if (data.siteAdmin) {
//                siteAdmin.visibility = View.VISIBLE
//            } else siteAdmin.visibility = View.INVISIBLE
//        }
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = dataList[position]
        // Set User picture
        Glide.with(holder.avatarUrl)
                .load(currentItem.avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .circleCrop()
                .into(holder.avatarUrl)
        //Set User Name
        holder.login.text = currentItem.login
        //User siteAdmin is ture, put staff Text
        if (currentItem.siteAdmin) {
                holder.siteAdmin.visibility = View.VISIBLE
            }
        else holder.siteAdmin.visibility = View.INVISIBLE
    }

    override fun getItemCount(): Int = dataList.size

    inner class RecyclerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val avatarUrl: ImageView = itemView.findViewById(R.id.iv_user)
        val login: TextView = itemView.findViewById(R.id.tv_userName)
        val siteAdmin: TextView = itemView.findViewById(R.id.tv_staff)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, login.text.toString())
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, userName: String)
    }

}

