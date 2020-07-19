package com.favoriteplaces.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.favoriteplaces.R
import com.favoriteplaces.core.HomeController
import com.favoriteplaces.core.extensions.navigateUp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main),
    HomeController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupListeners()
        setupNavigationBar()
    }

    private fun setupNavigationBar() {
        homeBottomNavigationView.setupWithNavController(getNavController())
    }

    private fun setupListeners() {
        getNavController().addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.locationListFragment) {
                showNavigationBar()
            }
        }
    }

    override fun hideNavigationBar() {
        homeBottomNavigationView.isGone = true
    }

    override fun showNavigationBar() {
        homeBottomNavigationView.isGone = false
    }

    override fun onBackPressed() {
        getNavController().navigateUp(this)
    }

    private fun getNavController() = findNavController(R.id.mainFragmentContainer)
}