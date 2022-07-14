package com.biped.locations.instrumentation

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(ApplicationStartScenario::class, LocationDetailsScenario::class)
class InstrumentationTestSuite