package com.ruhlanusubov.techapp.ui.home

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.models.SlideModel
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.databinding.FragmentDetailsBinding
import com.ruhlanusubov.techapp.databinding.FragmentPageBinding

class DetailsFragment : Fragment() {
    private val args by navArgs<DetailsFragmentArgs>()
    private var _binding: FragmentDetailsBinding?= null
    private val binding: FragmentDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()
    }

    private fun setup(){
        val data=args.productdata
        val num=data.discountPercentage
        val decrease=(100.0-num!!).toInt()
        val firstprice=data.price?.let { it*100/decrease }
        binding.firstprice.text="${firstprice} $"
        binding.productprice.text="${data.price} $"
        binding.firstprice.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
        binding.productbrand.text=data.title
        binding.productrating.text="Reytinq: ${data.rating}⭐"
        binding.productcategory.text="Kateqoriya: ${data.category}"
        binding.productstock.text="Stokda var: ${data.stock} ədəd"
        binding.discount.text="${data.discountPercentage}%"
        binding.desc.text=data.description
        val imageList = ArrayList<SlideModel>() // Create image list

        for(img in data.images!!){
            imageList.add(SlideModel(img))
        }
        val imageSlider = binding.productslider
        imageSlider.setImageList(imageList)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}

