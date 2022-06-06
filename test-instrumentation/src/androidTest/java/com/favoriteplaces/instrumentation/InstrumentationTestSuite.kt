package com.favoriteplaces.instrumentation

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(LocationListScenario::class, LocationDetailsScenario::class)
class InstrumentationTestSuite