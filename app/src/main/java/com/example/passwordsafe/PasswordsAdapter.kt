package com.example.passwordsafe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PasswordsAdapter(val context : Context,val purposes : ArrayList<String>) : RecyclerView.Adapter<PasswordsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item,parent,false),mListner)
    }

    private lateinit var mListner : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position : Int)

    }

    fun setOnItemClickListener(listener : onItemClickListener) {
        mListner = listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.purpose.text = purposes[position]
    }

    override fun getItemCount(): Int {
        return purposes.size
    }

    class MyViewHolder(itemView : View,listener : onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val purpose = itemView.findViewById<TextView>(R.id.txtPurposeRecyclerViewItem)

        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
