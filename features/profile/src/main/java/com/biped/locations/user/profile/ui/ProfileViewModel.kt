package com.biped.locations.user.profile.ui

import androidx.lifecycle.ViewModel
import com.favoriteplaces.core.coroutines.MutableWarmFlow

internal class ProfileViewModel : ViewModel() {

    private val _instruction = MutableWarmFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toWarmFlow()

}
