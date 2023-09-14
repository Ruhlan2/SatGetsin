package com.ruhlanusubov.techapp.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
   private var _binding:FragmentSplashBinding?=null
   private val binding:FragmentSplashBinding get() = _binding!!

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
     _binding= FragmentSplashBinding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      setup()
   }

   private fun setup(){
      val handler= Handler(Looper.getMainLooper())

      val runnable=Runnable{
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
      }
      handler.postDelayed(runnable,4000L)
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding=null
   }
}