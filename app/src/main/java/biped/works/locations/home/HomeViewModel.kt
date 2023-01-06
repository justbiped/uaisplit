package biped.works.locations.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.user.settings.data.SettingsRepository
import biped.works.coroutines.MutableWarmFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _instruction = MutableWarmFlow<HomeInstruction>(HomeInstruction.Default)
    val instruction = _instruction.toWarmFlow()

    init {
        loadThemeSettings()
    }

    private fun loadThemeSettings() {
        settingsRepository.themeSettingsStream
            .onEach { _instruction.post(HomeInstruction.UpdateTheme(it)) }
            .launchIn(viewModelScope)
    }

    fun selectHomeDestination(destination: HomeDestination) {
        _instruction.emit(HomeInstruction.Navigate(destination))
    }
}