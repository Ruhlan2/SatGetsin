package com.ruhlanusubov.techapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruhlanusubov.techapp.databinding.ItemCategorySearchBinding

class MostviewedAdapter: RecyclerView.Adapter<MostviewedAdapter.MostviewedHolder>() {
    private val title=ArrayList<String>()
    inner class MostviewedHolder(val binding: ItemCategorySearchBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostviewedHolder {
       val layout=ItemCategorySearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MostviewedHolder(layout)
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: MostviewedHolder, position: Int) {
        val item=title[position]
        holder.binding.name.text=item
    }
    fun updatelist(list: List<String>){
        title.clear()
        title.addAll(list)
        notifyDataSetChanged()
    }
}