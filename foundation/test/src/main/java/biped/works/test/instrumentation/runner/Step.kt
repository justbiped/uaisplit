package biped.works.test.instrumentation.runner

import androidx.annotation.IntRange

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Step(val displayName: String, @IntRange(from = 0L, to = 100) val order: Int = 0)