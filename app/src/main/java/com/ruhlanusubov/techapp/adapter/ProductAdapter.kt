package com.ruhlanusubov.techapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.databinding.ItemProductBinding
import com.ruhlanusubov.techapp.model.modelproduct.Product
import com.ruhlanusubov.techapp.model.modelproduct.Responseproduct
import com.ruhlanusubov.techapp.ui.home.HomeFragmentDirections
import com.shashank.sony.fancytoastlib.FancyToast

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    private val productlist=ArrayList<Product>()
    inner class ProductHolder(val binding:ItemProductBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
       val layout=ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductHolder(layout)
    }

    override fun getItemCount(): Int {
        return productlist.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = productlist[position]
        holder.binding.productname.text = item.title
        holder.binding.productcategory.text = item.category
        holder.binding.price.text = "${item.price} $"

        holder.binding.likebtn.setOnCheckedChangeListener{ checkbox,ischecked->
           if(checkbox.isChecked){
               FancyToast.makeText(holder.itemView.context,"Seçilmişlərə əlavə edildi!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
           }else{
               FancyToast.makeText(holder.itemView.context,"Seçilmişlərdən çıxarıldı!",FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show()
           }
       }
        Glide.with(holder.itemView.context).load(item.images!![0]).into(holder.binding.img)


        holder.binding.productcard.setOnClickListener {
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item))

        }
    }
    fun updatelist(list:List<Product>){
        productlist.clear()
        productlist.addAll(list)
        notifyDataSetChanged()
    }

}
