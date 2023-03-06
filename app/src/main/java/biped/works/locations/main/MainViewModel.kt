package biped.works.locations.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableViewStateFlow
import com.biped.works.settings.data.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val settingsRepository: com.biped.works.settings.data.SettingsRepository
) : ViewModel() {

    private val _instruction = MutableViewStateFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toViewStateFlow()

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