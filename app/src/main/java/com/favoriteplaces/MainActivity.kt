package com.favoriteplaces

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.favoriteplaces.core.extensions.navigateUp

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onBackPressed() {
        findNavController(R.id.mainFragmentContainer).navigateUp(this)
    }
}