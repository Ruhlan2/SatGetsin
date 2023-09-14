package com.ruhlanusubov.techapp.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.databinding.FragmentAddBinding
import com.ruhlanusubov.techapp.databinding.FragmentWishBinding

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding?=null
    private val binding: FragmentAddBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAddBinding.inflate(inflater,container,false)
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