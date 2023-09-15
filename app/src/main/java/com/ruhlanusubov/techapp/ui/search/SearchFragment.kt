package com.ruhlanusubov.techapp.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.adapter.CategoryAdapter
import com.ruhlanusubov.techapp.adapter.MostviewedAdapter
import com.ruhlanusubov.techapp.adapter.SearchAdapter
import com.ruhlanusubov.techapp.api.ApiUtils
import com.ruhlanusubov.techapp.databinding.FragmentSearchBinding
import com.ruhlanusubov.techapp.model.modelcategory.Category
import com.ruhlanusubov.techapp.model.modelproduct.Product
import com.ruhlanusubov.techapp.model.modelproduct.Responseproduct
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
        mostviewed()
        searchtext()
        searchadapter()
        adapter()
        bottomsheet()
    }
    private fun bottomsheet(){
        binding.ascendingbtn.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSheetFragment2())
        }
        binding.filterbtn.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToFilterFragment())
        }


    }
    private fun searchtext(){
        binding.searchtext.addTextChangedListener {

            val sp=requireContext().getSharedPreferences("filters", Context.MODE_PRIVATE)
            val categoryfilter=sp.getString("category",null)
            val limit=sp.getInt("limit",30)
            Log.e("categoryfilter","$categoryfilter $limit")
           if(it!=null){

               val search=it.toString()

                   val brand = service.getsearch(search)

                   brand.enqueue(object : Callback<Responseproduct> {
                       override fun onResponse(
                           call: Call<Responseproduct>,
                           response: Response<Responseproduct>
                       ) {
                           if (response.isSuccessful) {
                               response.body()?.let {
                                   searchAdapter.updatelist(it.products ?: emptyList())
                               }
                           } else {
                               FancyToast.makeText(
                                   requireContext(),
                                   "Error",
                                   FancyToast.LENGTH_SHORT,
                                   FancyToast.ERROR,
                                   false
                               ).show()
                           }
                       }

                       override fun onFailure(call: Call<Responseproduct>, t: Throwable) {
                           FancyToast.makeText(
                               requireContext(),
                               t.localizedMessage,
                               FancyToast.LENGTH_SHORT,
                               FancyToast.ERROR,
                               false
                           ).show()
                       }

                   })

           }else{
               FancyToast.makeText(requireContext(),"Empty",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
           }
        }
    }
    private fun searchadapter(){
        binding.searchprod.layoutManager=GridLayoutManager(requireContext(),2)
        binding.searchprod.adapter=searchAdapter
    }
    private fun mostviewed(){
      val request=service.getcategories()
        request.enqueue(object:Callback<Category>{
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