package com.favoriteplaces.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.favoriteplaces.R
import com.favoriteplaces.databinding.ActivityMainBinding
import com.hotmart.locations.core.HomeController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), HomeController {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigationBar()
    }

    private fun setupNavigationBar() {
        binding.homeBottomNavigationView.setupWithNavController(getNavController())
    }

    override fun hideNavigationBar() {
        binding.homeBottomNavigationView.isGone = true
    }

    override fun showNavigationBar() {
        binding.homeBottomNavigationView.isGone = false
    }

    private fun getNavController() = findNavController(R.id.mainFragmentContainer)
}