package com.biped.locations.instrumentation

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ApplicationStartScenario::class,
    LocationListScenario::class,
    LocationDetailsScenario::class
)
class InstrumentationTestSuite

const val TIMEOUT = 3000L
const val LONG_TIMEOUT = 8000L
const val TRASH_TIMEOUT = 15000L