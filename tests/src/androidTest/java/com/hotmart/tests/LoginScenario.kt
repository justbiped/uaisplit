package com.hotmart.tests

import com.hotmart.tests.instrumentation.runner.AutomatorRunner
import com.hotmart.tests.instrumentation.runner.Step
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AutomatorRunner::class)
class LoginScenario {

    @Before
    fun setUp() {
        print("To logando")
    }

    @Test
    @Step("A method to see if i can print some other things")
    fun opaaa() {
        print("opaaaa")
        assertThat("Essa string").isEqualTo("Essa string")
    }
}

