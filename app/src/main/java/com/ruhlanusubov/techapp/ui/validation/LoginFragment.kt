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
    private lateinit var sp:SharedPreferences

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
           with(binding){
               val user=email.text.toString()
               val password=password.text.toString()

               if(user.isNotEmpty()&&password.isNotEmpty()){
                   validation(user,password)
               }else{
                   Toast.makeText(requireContext(),"Please fill blanks",Toast.LENGTH_SHORT).show()
               }
           }



    }
    private fun validation(
    username:String,
    password:String
    ){
            service.loginuser(username,password).enqueue(object: Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                            sp=requireContext().getSharedPreferences("User",Context.MODE_PRIVATE)
                            it.firstName?.let { first->
                                sp.edit().putString("first",first).apply()
                            }
                            it.lastName?.let { last->
                                sp.edit().putString("last",last).apply()
                            }
                            it.image?.let { url->
                                sp.edit().putString("image",url).apply()

                            }
                            it.email?.let {email->
                                sp.edit().putString("email",email).apply()
                            }
                        }
                    }else{
                        Toast.makeText(requireContext(),"Failed",Toast.LENGTH_SHORT).show()
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