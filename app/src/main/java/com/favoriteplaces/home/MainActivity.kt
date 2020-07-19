package com.favoriteplaces.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.navigation.findNavController
import com.favoriteplaces.R
import com.favoriteplaces.core.HomeController
import com.favoriteplaces.core.extensions.navigateUp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main),
    HomeController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListeners()
    }

    override fun onBackPressed() {
        findNavController().navigateUp(this)
    }

    override fun hideNavigationBar() {
        homeBottomNavigationView.isGone = true
    }

    override fun showNavigationBar() {
        homeBottomNavigationView.isGone = false
    }

    private fun setupListeners() {
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.locationListFragment){
                showNavigationBar()
            }
        }
    }

    private fun findNavController() = findNavController(R.id.mainFragmentContainer)
}