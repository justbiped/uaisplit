package com.hotmart.tests.instrumentation.runner

import androidx.annotation.IntRange

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Step(val displayName: String, @IntRange(from = 0L, to = 100) val order: Int = 0)