package com.ruhlanusubov.techapp.ui.home

import android.os.Bundle
import android.transition.Slide
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.adapter.CategoryAdapter
import com.ruhlanusubov.techapp.adapter.ProductAdapter
import com.ruhlanusubov.techapp.api.ApiUtils
import com.ruhlanusubov.techapp.databinding.FragmentHomeBinding
import com.ruhlanusubov.techapp.model.modelcategory.Category
import com.ruhlanusubov.techapp.model.modelproduct.Product
import com.ruhlanusubov.techapp.model.modelproduct.Responseproduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    //0lelplR
    private var _binding:FragmentHomeBinding?=null
    private val binding:FragmentHomeBinding get() = _binding!!
    private val adapter=ProductAdapter()
    private val category=CategoryAdapter()
    private val service=ApiUtils.getService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.setOnClickListener {
            search()
        }
        setadapter()
        setcategoryadapter()
        requestdata()


    }
    private fun search(){
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
    }
    private fun setcategoryadapter(){
        binding.categoryrv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.categoryrv.adapter=category
    }

    private fun requestcategory(){
        val cresponse=service.getcategories()

   cresponse.enqueue(object:Callback<Category>{
       override fun onResponse(call: Call<Category>, response: Response<Category>) {
           if(response.isSuccessful){
               response.body()?.let {
                   category.updateCategory(it)
               }
           }
       }

       override fun onFailure(call: Call<Category>, t: Throwable) {
           Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
       }

   })
    }
    private fun setadapter(){
        binding.rvproduct.layoutManager=GridLayoutManager(requireContext(),2)
        binding.rvproduct.adapter=adapter
    }
    private fun slider(
        list: List<Product>
    ){
        val imagelist=ArrayList<SlideModel>()

       list.forEach {
          imagelist.add(SlideModel(it.thumbnail))
       }
        val slider=binding.imageSlider
        slider.setImageList(imagelist)
        slider.startSliding(2500)
        slider.setSlideAnimation(AnimationTypes.BACKGROUND_TO_FOREGROUND)
        /*
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.macbook1))
        imageList.add(SlideModel(R.drawable.macbook2))
        imageList.add(SlideModel(R.drawable.mac3))
        imageList.add(SlideModel(R.drawable.mac4))
        imageList.add(SlideModel(R.drawable.mac5))
        imageList.add(SlideModel(R.drawable.iphone))



        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)

         */

    }
    private fun requestdata(){
        val response=service.getproduct()

         response.enqueue(object :Callback<Responseproduct>{
             override fun onResponse(
                 call: Call<Responseproduct>,
                 response: Response<Responseproduct>
             ) {
                 if(response.isSuccessful){
                     response.body()?.let {
                         requestcategory()
                         it.products?.let { it1 -> adapter.updatelist(it1) }
                         slider(it.products?: emptyList())
                         binding.loading.visibility=View.GONE
                     }
                 }else{
                     Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                 }
             }

             override fun onFailure(call: Call<Responseproduct>, t: Throwable) {
                 Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
             }

         })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}