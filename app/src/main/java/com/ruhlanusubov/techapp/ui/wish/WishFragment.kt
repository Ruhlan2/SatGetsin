package com.ruhlanusubov.techapp.ui.wish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.databinding.FragmentMessageBinding
import com.ruhlanusubov.techapp.databinding.FragmentWishBinding

class WishFragment : Fragment() {
    private var _binding: FragmentWishBinding?=null
    private val binding: FragmentWishBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentWishBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}