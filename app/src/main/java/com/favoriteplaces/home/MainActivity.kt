package com.favoriteplaces.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.favoriteplaces.R
import com.favoriteplaces.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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

    private fun getNavController() = findNavController(R.id.mainFragmentContainer)
}