package com.hotmart.test

import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions

@CucumberOptions(features = ["features"], strict = true)
class CucumberRunner : CucumberAndroidJUnitRunner()