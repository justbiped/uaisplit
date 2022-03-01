package com.hotmart.test

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(LoginScenario::class, SeeLocationsOnLaunchScenario::class)
class InstrumentationTestSuit