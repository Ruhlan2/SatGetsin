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
        val sharedPreferences=requireContext().getSharedPreferences("preference", Context.MODE_PRIVATE)
        val link=sharedPreferences.getString("img",null)
        val user=sharedPreferences.getString("username",null)
        Glide.with(requireContext()).load(link).into(binding.userimg)
        binding.username.text=user

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null

    }
}