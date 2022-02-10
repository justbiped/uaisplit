package com.hotmart.tests.instrumentation.runner

import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions

@CucumberOptions(features = ["features"], strict = true, glue = ["com.hotmart.test"])
class CucumberRunner : CucumberAndroidJUnitRunner()