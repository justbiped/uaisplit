package biped.works.locations.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableWarmFlow
import biped.works.user.settings.data.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _instruction = MutableWarmFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toWarmFlow()

    init {
        observeThemeSettings()
    }

    private fun observeThemeSettings() {
        viewModelScope.launch {
            settingsRepository.themeSettingsStream.collect {
                _instruction.value = Instruction.UpdateTheme(it)
            }
        }
    }
}