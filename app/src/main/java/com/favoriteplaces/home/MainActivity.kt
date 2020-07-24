package com.favoriteplaces.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.favoriteplaces.R
import com.favoriteplaces.core.HomeController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main),
    HomeController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigationBar()
    }

    private fun setupNavigationBar() {
        homeBottomNavigationView.setupWithNavController(getNavController())
    }

    override fun hideNavigationBar() {
        homeBottomNavigationView.isGone = true
    }

    override fun showNavigationBar() {
        homeBottomNavigationView.isGone = false
    }

    private fun getNavController() = findNavController(R.id.mainFragmentContainer)
}