package com.ruhlanusubov.techapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruhlanusubov.techapp.databinding.ItemSearchBinding
import com.ruhlanusubov.techapp.model.modelproduct.Product

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchHolder>() {

    private val searchList=ArrayList<Product>()
    inner class SearchHolder(val binding:ItemSearchBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val layout=ItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchHolder(layout)
    }

    override fun getItemCount(): Int {
       return searchList.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val item=searchList[position]
        Glide.with(holder.itemView.context).load(item.thumbnail).into(holder.binding.img)
        holder.binding.brand.text=item.brand
        holder.binding.desc.text=item.description
        holder.binding.price.text="$${item.price}"
    }
    fun updatelist(list: List<Product>){
        searchList.clear()
        searchList.addAll(list)
        notifyDataSetChanged()
    }
}