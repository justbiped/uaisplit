package biped.works.coroutines

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DispatcherDefault

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DispatcherIO

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher