package com.biped.locations.user.settings.ui

import biped.works.coroutiens.test.TestFlowSubject.Companion.assertThat
import biped.works.coroutiens.test.runTest
import biped.works.coroutiens.test.testFlowOf
import com.biped.locations.theme.ColorTheme
import com.biped.locations.user.settings.ObserveUseSettingsUseCase
import com.biped.locations.user.settings.SaveUserSettingsUseCase
import com.biped.locations.user.settings.data.UserSettings
import com.biped.locations.user.themeFixture
import com.biped.locations.user.userSettingsFixture
import com.biped.test.unit.mock
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.flow.MutableSharedFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserSettingsViewModelTest {

    @get:Rule val coroutineTestRule = biped.works.coroutiens.test.CoroutineTestRule()

    private val observeUserSettings: ObserveUseSettingsUseCase = mock()
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
        val settings = listOf(
            userSettingsFixture(theme = themeFixture(ColorTheme.DARK)),
            userSettingsFixture(theme = themeFixture(ColorTheme.LIGHT)),
            userSettingsFixture(theme = themeFixture(ColorTheme.SYSTEM))
        )

        settingsFlow.emit(settings[0])
        settingsFlow.emit(settings[1])
        settingsFlow.emit(settings[2])

        assertThat(testFlow).hasCollectedExactly(
            Instruction.Loading,
            Instruction.UpdateSettings(settings[0]),
            Instruction.UpdateSettings(settings[1]),
            Instruction.UpdateSettings(settings[2]),
        )

        testFlow.finish()
    }
}