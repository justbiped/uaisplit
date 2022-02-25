package com.favoriteplaces.home

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.favoriteplaces.R
import com.favoriteplaces.databinding.ActivityMainBinding
import com.hotmart.locations.core.control.HOME_ACTION_INTENT
import com.hotmart.locations.core.control.HomeAction
import com.hotmart.locations.core.extensions.changeVisibility
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

        registerReceiver()
        setupListeners()
        setupNavigationBar()
    }

    private fun registerReceiver() {
        LocalBroadcastManager.getInstance(baseContext)
            .registerReceiver(homeActionReceiver, IntentFilter(HOME_ACTION_INTENT))
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
}
