package com.ruhlanusubov.techapp.ui.sheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ruhlanusubov.techapp.R
import com.ruhlanusubov.techapp.databinding.FragmentFilterBinding

class FilterFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFilterBinding?= null
    private val binding: FragmentFilterBinding get() = _binding!!
    private var category:String?=null
    private var limit:Int=30

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentFilterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data()
       binding.savebtn.setOnClickListener {
           share()
       }

    }
    private fun data() {

        with(binding) {
            rgCategory.setOnCheckedChangeListener { group, i ->

                    when (i) {
                        R.id.rball -> category = null
                        R.id.rbsmartphones-> category="smartphones"
                        R.id.rblaptop->category="laptop"
                        R.id.rbfragrances->category="fragrances"
                        R.id.rbskincare->category="laptop"
                        R.id.rbgroceries->category="groceries"
                        R.id.rbhomedec->category="home-decoration"
                        R.id.rbfurniture->category="furniture"
                        R.id.rbtops->category="tops"
                        R.id.rbwomensdress->category="womens-dresses"
                        R.id.rbwomensshoes->category="womens-shoes"
                        R.id.rbmensshirts->category="mens-shirts"
                        R.id.rbmensshoes->category="mens-shoes"
                        R.id.rbmenswatches->category="mens-watches"
                        R.id.rbwomenswatch->category="womens-watches"
                        R.id.rbwomensbag->category="womens-bags"
                        R.id.rbwomensjew->category="womens-jewellery"
                        R.id.rbsunglasses->category="sunglasses"
                        R.id.rbautomotive->category="automotive"
                        R.id.rbmotorcycle->category="motorcycle"
                        R.id.rblighting->category="lighting"



                }
            }
            rglimit.setOnCheckedChangeListener{group,i->

                    when(i){
                        R.id.rb5->limit=5
                        R.id.rb10->limit=10
                        R.id.rb15->limit=15
                        R.id.choose->limit=30
                    }

            }

        }
    }
    private fun share(){
        val sp=requireContext().getSharedPreferences("filters", Context.MODE_PRIVATE)
        sp.edit().putString("category",category).apply()
        sp.edit().putInt("limit",limit).apply()
        findNavController().navigate(FilterFragmentDirections.actionFilterFragmentToSearchFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}