package com.favoriteplaces.home

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.favoriteplaces.R
import com.favoriteplaces.core.control.HOME_ACTION_INTENT
import com.favoriteplaces.core.control.HomeAction
import com.favoriteplaces.core.extensions.changeVisibility
import com.favoriteplaces.core.extensions.registerLocalBroadcast
import com.favoriteplaces.core.extensions.unregisterLocalBroadcast
import com.favoriteplaces.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeActionReceiver = HomeActionReceiver()

    private val navController by lazy { findNavController(R.id.mainFragmentContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerLocalBroadcast(homeActionReceiver, IntentFilter(HOME_ACTION_INTENT))
        setupListeners()
        setupNavigationBar()
    }

    private fun setupListeners() {
        homeActionReceiver.onReceiveAction { onHomeActionReceived(it) }
    }

    private fun onHomeActionReceived(action: HomeAction) {
        binding.homeBottomNavigationView.changeVisibility(action.isNavBarVisible)
    }

    private fun setupNavigationBar() {
        binding.homeBottomNavigationView.setupWithNavController(navController)
    }

    override fun onDestroy() {
        unregisterLocalBroadcast(homeActionReceiver)
        super.onDestroy()
    }
}
