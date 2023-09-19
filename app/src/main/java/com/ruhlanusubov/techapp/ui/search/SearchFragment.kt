package com.ruhlanusubov.techapp.ui.search

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.adapter.MostviewedAdapter
import com.ruhlanusubov.techapp.adapter.SearchAdapter
import com.ruhlanusubov.techapp.api.ApiUtils
import com.ruhlanusubov.techapp.databinding.FragmentSearchBinding
import com.ruhlanusubov.techapp.model.modelcategory.Category
import com.ruhlanusubov.techapp.model.modelproduct.Product
import com.ruhlanusubov.techapp.model.modelproduct.Responseproduct
import com.ruhlanusubov.techapp.ui.sheet.SheetFragment
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding?= null
    private val binding: FragmentSearchBinding get() = _binding!!
    private val service=ApiUtils.getService()
    private val searchAdapter=SearchAdapter()
    private val categoryadapter=MostviewedAdapter()
    private lateinit var sp:SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchadapter()
        adapter()
        mostviewed()
        bottomsheet()
        searchtext()

    }
    private fun searchtext() {

        sp=requireContext().getSharedPreferences("filters", Context.MODE_PRIVATE)
        val categoryfilter = sp.getString("category", null)
        val limit = sp.getInt("limit",2)
        with(binding) {

            if(categoryfilter!=null){
                filter(categoryfilter,limit)
            }else{
                only(limit)
            }
            searchtext.addTextChangedListener{
                if(it!=null){
                    val search=it.toString()
                    if(search.isNotEmpty()){
                        onlySearch(search)
                    }else{
                        Toast.makeText(requireContext(),"Empty",Toast.LENGTH_SHORT).show()
                    }
                }
            }


            }


        }

    private fun bottomsheet(){
        binding.ascendingbtn.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSheetFragment())
        }
        binding.filterbtn.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToFilterFragment())
        }
    }
    private fun filter(
        category:String,
        limit:Int
    ){
        service.filter(category,limit).enqueue(object: Callback<Responseproduct>{
            override fun onResponse(
                call: Call<Responseproduct>,
                response: Response<Responseproduct>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        searchAdapter.updatelist(it.products?: emptyList())
                       // Toast.makeText(requireContext(),"success",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Responseproduct>, t: Throwable) {
                Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun byCategory(
       category: String
    ){
        service.onlyCategory(category).enqueue(object:Callback<Responseproduct>{
            override fun onResponse(
                call: Call<Responseproduct>,
                response: Response<Responseproduct>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        searchAdapter.updatelist(it.products?: emptyList())
                        Toast.makeText(requireContext(),"$category success",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Responseproduct>, t: Throwable) {
                Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun only(
        limit:Int
    ){
       service.onlyLimit(limit).enqueue(object:Callback<Responseproduct>{
           override fun onResponse(
               call: Call<Responseproduct>,
               response: Response<Responseproduct>
           ) {
               if(response.isSuccessful){
                   response.body()?.let {
                       searchAdapter.updatelist(it.products?: emptyList())
                       Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()

                   }
               }else{
                   Toast.makeText(requireContext(),Log.ERROR,Toast.LENGTH_SHORT).show()
               }
           }

           override fun onFailure(call: Call<Responseproduct>, t: Throwable) {
               Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
           }

       })
    }
    private fun onlySearch(
        search:String
    ){
        service.onlySearch(search).enqueue(object:Callback<Responseproduct>{
            override fun onResponse(
                call: Call<Responseproduct>,
                response: Response<Responseproduct>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        searchAdapter.updatelist(it.products?: emptyList())
                    }
                }else{
                    Toast.makeText(requireContext(),Log.ERROR,Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Responseproduct>, t: Throwable) {
                Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun searchadapter(){
        binding.searchprod.layoutManager=GridLayoutManager(requireContext(),2)
        binding.searchprod.adapter=searchAdapter
    }
    private fun mostviewed(){
      service.getcategories().enqueue(object:Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.isSuccessful){
                   response.body()?.let {
                       categoryadapter.updatelist(it)
                   }
                }else{
                    Log.e("error","status")
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
               Log.e("error","status")
            }

        })
    }
    private fun adapter(){
        binding.most.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL)
        binding.most.adapter=categoryadapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}