package biped.works.statement.injection

import biped.works.api.http.ApiBuilder
import biped.works.statement.data.remote.StatementApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StatementModule {

    @Provides
    @Singleton
    fun providesStatementApi(apiFactory: ApiBuilder) = apiFactory.create(StatementApi::class.java)
}