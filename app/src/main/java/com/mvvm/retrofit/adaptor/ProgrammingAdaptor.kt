package com.mvvm.retrofit.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.retrofit.R
import com.mvvm.retrofit.models.QuotList
import com.mvvm.retrofit.models.Result

class ProgrammingAdaptor : ListAdapter<QuotList, ProgrammingAdaptor.ProgrammingViewHolder>(DiffUtil()){

    class ProgrammingViewHolder (view : View): RecyclerView.ViewHolder(view){

        private val author = view.findViewById<TextView>(R.id.author)
        private val content  = view.findViewById<TextView>(R.id.content)

        fun bind (item: QuotList){
            author.text = item.results[adapterPosition].author
            content.text = item.results[adapterPosition].content
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

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<QuotList>(){
        override fun areItemsTheSame(oldItem: QuotList, newItem: QuotList): Boolean {
            return  oldItem.results == newItem.results        }

        override fun areContentsTheSame(
            oldItem: QuotList,
            newItem: QuotList
        ): Boolean {
            return  oldItem == newItem
        }

    }
}