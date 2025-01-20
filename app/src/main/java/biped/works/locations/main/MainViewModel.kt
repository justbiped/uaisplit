package biped.works.locations.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.asUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val settingsRepository: com.biped.works.settings.data.SettingsRepository
) : ViewModel() {

    private val _instruction = MutableStateFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.asUiState(viewModelScope)

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