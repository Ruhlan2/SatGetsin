package com.ruhlanusubov.techapp.ui.sheet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.api.ApiUtils
import com.ruhlanusubov.techapp.databinding.FragmentPageBinding
import com.ruhlanusubov.techapp.databinding.FragmentSheetBinding
import com.ruhlanusubov.techapp.model.modelproduct.Responseproduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSheetBinding?= null
    private val binding: FragmentSheetBinding get() = _binding!!
    private val service=ApiUtils.getService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSheetBinding.inflate(inflater,container,false)
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