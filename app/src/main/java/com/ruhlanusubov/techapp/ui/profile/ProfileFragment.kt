package com.ruhlanusubov.techapp.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding:FragmentProfileBinding?=null
    private val binding:FragmentProfileBinding get() = _binding!!
    private lateinit var sp:SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       setup()
    }

    private fun setup(){
        sp=requireContext().getSharedPreferences("User", Context.MODE_PRIVATE)
        with(binding){
            val first=sp.getString("first",null)
            val last=sp.getString("last",null)
            username.text="$first $last"
            val url=sp.getString("image",null)
            Glide.with(requireContext()).load(url).into(userimg)
            val email=sp.getString("email",null)
            emailText.text=email
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null

    }
}