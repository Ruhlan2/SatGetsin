package com.ruhlanusubov.techapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ruhlanusubov.techapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setup()
    }

    private fun setup(){

        val navhost=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val controller=navhost.navController

        NavigationUI.setupWithNavController(binding.bottomnav,controller)
        controller.addOnDestinationChangedListener{ _,destination,_->
            when(destination.id){
                    R.id.splashFragment,
                    R.id.loginFragment ,
                    R.id.registerFragment,
                    R.id.searchFragment,
                    R.id.sheetFragment,
                    R.id.filterFragment,
                    R.id.detailsFragment->{
                            binding.bottomnav.visibility= View.GONE
                        }else->{
                            binding.bottomnav.visibility=View.VISIBLE
                        }
            }

        }
    }
}


