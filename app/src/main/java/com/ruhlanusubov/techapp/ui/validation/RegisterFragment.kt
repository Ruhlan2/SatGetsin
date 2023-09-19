package com.ruhlanusubov.techapp.ui.validation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.api.ApiUtils
import com.ruhlanusubov.techapp.databinding.FragmentRegisterBinding
import com.ruhlanusubov.techapp.model.detailuser.UserDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    private var _binding:FragmentRegisterBinding?=null
    private val binding:FragmentRegisterBinding get() = _binding!!
    private val service=ApiUtils.getService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }
    private fun setup(){
        with(binding){
            registerBtn.setOnClickListener {
                data(1)
            }
        }
    }
    private fun data(
        id:Int
    ){
        service.detailUser(id).enqueue(object:Callback<UserDetail>{
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
               if(response.isSuccessful){
                   response.body()?.let {
                       Log.e("status","success")
                       Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                   }
               }else{
                   Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
               }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}