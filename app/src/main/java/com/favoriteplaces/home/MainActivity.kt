package com.favoriteplaces.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.favoriteplaces.R
import com.favoriteplaces.databinding.ActivityMainBinding
import com.hotmart.locations.core.control.HOME_ACTION
import com.hotmart.locations.core.control.HomeAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy { findNavController(R.id.mainFragmentContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigationBar()
    }

    private fun setupNavigationBar() {
        binding.homeBottomNavigationView.setupWithNavController(navController)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        getHomeAction(intent)?.also { action ->
            binding.homeBottomNavigationView.changeVisibility(action.isNavBarVisible)
        }
    }

    private fun getHomeAction(intent: Intent): HomeAction? {
        return intent.getParcelableExtra(HOME_ACTION)
    }
}

fun View.changeVisibility(isVisible: Boolean) {
    if (isVisible) {
        animate().translationY(0f)
            .withStartAction { this.isVisible = isVisible }
            .start()
    } else {
        animate().translationY(y)
            .withEndAction { this.isVisible = isVisible }
            .start()
    }

}