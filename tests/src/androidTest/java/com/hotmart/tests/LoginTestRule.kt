package com.hotmart.tests

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runner.JUnitCore
import org.junit.runners.model.Statement

object LoginTestRule : TestRule {
    private var isLogged: Boolean = false

    override fun apply(base: Statement, description: Description): Statement {
        return try {
            object : Statement() {
                @Throws(Throwable::class)
                override fun evaluate() {
                    performLogin()
                    base.evaluate()
                }
            }
        } finally {

        }
    }

    private fun performLogin() {
        if (!isLogged) {
            val result = JUnitCore.runClasses(LoginScenario::class.java)

            result.failures.forEach {
                throw Throwable(it.message, it.exception)
            }
        }
        isLogged = true
    }
}