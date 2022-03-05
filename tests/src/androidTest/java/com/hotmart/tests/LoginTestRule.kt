package com.hotmart.tests

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

object LoginTestRule : TestRule {
    private var isLogged: Boolean = false

    override fun apply(base: Statement, description: Description): Statement {
        return try {
            object : Statement() {
                @Throws(Throwable::class)
                override fun evaluate() {
                    base.evaluate()
                }
            }
        } finally {

        }
    }
}