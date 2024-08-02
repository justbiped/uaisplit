package com.biped.works.settings

import biped.works.coroutiens.test.CoroutineTestRule
import biped.works.coroutiens.test.TestFlowSubject.Companion.assertThat
import biped.works.coroutiens.test.runTest
import biped.works.coroutiens.test.testFlowOf
import biped.works.test.unit.mock
import com.biped.locations.theme.ColorTheme
import com.biped.works.settings.data.UserSettings
import com.biped.works.settings.ui.Instruction
import com.biped.works.settings.ui.UserSettingsViewModel
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.flow.MutableSharedFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(value = JUnit4::class)
class UserSettingsViewModelTest {

    @get:Rule val coroutineTestRule = CoroutineTestRule()

    private val observeUserSettings: ObserveUserSettingsUseCase = mock()
    private val saveUserSettings: SaveUserSettingsUseCase = mock()
    private lateinit var viewModel: UserSettingsViewModel

    private val settingsFlow = MutableSharedFlow<UserSettings>()

    @Before
    fun setUp() {
        every { observeUserSettings() } returns settingsFlow
        viewModel = UserSettingsViewModel(observeUserSettings, saveUserSettings)
    }

    @Test
    fun `starts to observe user settings on view model init`() {
        verify { observeUserSettings() }
    }

    @Test
    fun `emmit update settings instruction on receive a distinct user settings`() = runTest {
        val testFlow = testFlowOf(viewModel.instruction)

        settingsFlow.emit(userSettingsFixture())

        assertThat(testFlow).hasCollected(Instruction.UpdateSettings(userSettingsFixture()))
        testFlow.finish()
    }

    @Test
    fun `emmit user settings update for each distinct user settings update`() = runTest {
        val testFlow = testFlowOf(viewModel.instruction)

        settingsFlow.emit(userSettingsFixture(theme = themeFixture(ColorTheme.DARK)))
        settingsFlow.emit(userSettingsFixture(theme = themeFixture(ColorTheme.LIGHT)))
        settingsFlow.emit(userSettingsFixture(theme = themeFixture(ColorTheme.SYSTEM)))

        assertThat(testFlow).hasCollectedExactly(
            Instruction.UpdateSettings(isLoading = true),
            Instruction.UpdateSettings(userSettingsFixture(theme = themeFixture(ColorTheme.DARK)), isLoading = false),
            Instruction.UpdateSettings(userSettingsFixture(theme = themeFixture(ColorTheme.LIGHT)), isLoading = false),
            Instruction.UpdateSettings(userSettingsFixture(theme = themeFixture(ColorTheme.SYSTEM)), isLoading = false),
        )

        testFlow.finish()
    }

    @Test
    fun `update user settings on change theme settings`(){
        val userSettings = UserSettings()

        viewModel.changeThemeSettings(userSettings)

        coVerify { saveUserSettings(userSettings) }
    }
}