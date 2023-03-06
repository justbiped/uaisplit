package com.biped.works.settings

import biped.works.coroutiens.test.TestFlowSubject.Companion.assertThat
import biped.works.coroutiens.test.runTest
import biped.works.coroutiens.test.testFlowOf
import com.biped.locations.theme.ColorTheme
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.flow.MutableSharedFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class ProfileSettingsViewModelTest {

    @get:Rule val coroutineTestRule = biped.works.coroutiens.test.CoroutineTestRule()

    private val observeUserSettings: ObserveUserSettingsUseCase = mock()
    private val saveUserSettings: SaveUserSettingsUseCase = mock()
    private lateinit var viewModel: com.biped.works.settings.ui.UserSettingsViewModel

    private val settingsFlow = MutableSharedFlow<com.biped.works.settings.data.UserSettings>()

    @Before
    fun setUp() {
        every { observeUserSettings() } returns settingsFlow
        viewModel = com.biped.works.settings.ui.UserSettingsViewModel(observeUserSettings, saveUserSettings)
    }

    @Test
    fun `starts to observe user settings on view model init`() {
        verify { observeUserSettings() }
    }

    @Test
    fun `emmit update settings instruction on receive a distinct user settings`() = runTest {
        val testFlow = testFlowOf(viewModel.instruction)

        settingsFlow.emit(userSettingsFixture())

        assertThat(testFlow).hasCollected(com.biped.works.settings.ui.Instruction.UpdateSettings(userSettingsFixture()))
        testFlow.finish()
    }

    @Test
    fun `emmit user settings update for each distinct user settings update`() = runTest {
        val testFlow = testFlowOf(viewModel.instruction)
        val settings = listOf(
            userSettingsFixture(theme = themeFixture(ColorTheme.DARK)),
            userSettingsFixture(theme = themeFixture(ColorTheme.LIGHT)),
            userSettingsFixture(theme = themeFixture(ColorTheme.SYSTEM))
        )

        settingsFlow.emit(settings[0])
        settingsFlow.emit(settings[1])
        settingsFlow.emit(settings[2])

        assertThat(testFlow).hasCollectedExactly(
            com.biped.works.settings.ui.Instruction.Loading,
            com.biped.works.settings.ui.Instruction.UpdateSettings(settings[0]),
            com.biped.works.settings.ui.Instruction.UpdateSettings(settings[1]),
            com.biped.works.settings.ui.Instruction.UpdateSettings(settings[2]),
        )

        testFlow.finish()
    }
}