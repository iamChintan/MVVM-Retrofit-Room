package com.mvvm.retrofit.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.retrofit.R
import com.mvvm.retrofit.models.Result

class ProgrammingAdaptor : ListAdapter<Result, ProgrammingAdaptor.ProgrammingViewHolder>(DiffUtil()){

    class ProgrammingViewHolder (view : View): RecyclerView.ViewHolder(view){

        private val author = view.findViewById<TextView>(R.id.author)
        private val content  = view.findViewById<TextView>(R.id.content)

        fun bind (item: Result){
            author.text = item.author
            content.text = item.content
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgrammingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ProgrammingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgrammingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return  oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return  oldItem == newItem
        }

    }
}