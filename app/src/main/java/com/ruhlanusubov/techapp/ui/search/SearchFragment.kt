package com.ruhlanusubov.techapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ruhlanusubov.techapp.R
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
        adapter()
        searchadapter()
        bottomsheet()
    }
    private fun bottomsheet(){
        binding.ascendingbtn.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSheetFragment2())
        }
        binding.mostsearch.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSheetFragment2())
        }


    }
    private fun searchtext(){
        binding.searchtext.addTextChangedListener {
           if(it!=null){
               val search=it.toString()
               val brand=service.getsearch(search)
               brand.enqueue(object :Callback<Responseproduct>{
                   override fun onResponse(
                       call: Call<Responseproduct>,
                       response: Response<Responseproduct>
                   ) {
                       if(response.isSuccessful){
                           response.body()?.let {
                               searchAdapter.updatelist(it.products?: emptyList())
                           }
                       }else{
                           FancyToast.makeText(requireContext(),"Error",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
                       }
                   }

                   override fun onFailure(call: Call<Responseproduct>, t: Throwable) {
                       FancyToast.makeText(requireContext(),t.localizedMessage,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
                   }

               })
           }else{
               FancyToast.makeText(requireContext(),"Empty",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
           }
        }
    }
    private fun searchadapter(){
        binding.most.layoutManager=GridLayoutManager(requireContext(),2)
        binding.most.adapter=searchAdapter
    }
    private fun mostviewed(){
        val request=service.getcategories()
        request.enqueue(object:Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        categoryadapter.updatelist(it)
                    }


                //categoryadapter.updatelist(response.body()?: emptyList())
                }else{
                    FancyToast.makeText(requireContext(),"Error",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                FancyToast.makeText(requireContext(),t.localizedMessage,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
            }

        })
    }
    private fun adapter(){
       // binding.mostSearched.layoutManager=GridLayoutManager(requireContext(),3,GridLayoutManager.HORIZONTAL,false)
        binding.most.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.most.adapter=searchAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}