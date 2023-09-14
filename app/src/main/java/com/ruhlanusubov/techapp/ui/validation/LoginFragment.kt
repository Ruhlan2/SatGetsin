package com.ruhlanusubov.techapp.ui.validation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ruhlanusubov.techapp.api.ApiUtils
import com.ruhlanusubov.techapp.databinding.FragmentLoginBinding
import com.ruhlanusubov.techapp.model.modeluser.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding:FragmentLoginBinding?=null
    private val binding:FragmentLoginBinding get() = _binding!!
    private val service=ApiUtils.getService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginbtn.setOnClickListener {
            setup()

        }
        register()
    }

    private fun register(){
        binding.reg.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }
    private fun setup(){
            val user=binding.email.text.toString()
            val password=binding.password.text.toString()

        if(user.isNotEmpty()&&password.isNotEmpty()){
           findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }else{
            Toast.makeText(requireContext(),"Please fill blanks",Toast.LENGTH_SHORT).show()
        }

        val response=service.loginuser(user,password)

        response.enqueue(object:Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        Log.e("status",it.toString())
                         val sharedPreferences=requireContext().getSharedPreferences("preference",Context.MODE_PRIVATE)
                        val editor:SharedPreferences.Editor=sharedPreferences.edit()
                        editor.putString("username",it.username)
                        editor.putString("img",it.image)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}