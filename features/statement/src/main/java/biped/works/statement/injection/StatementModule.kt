package biped.works.statement.injection

import biped.works.api.http.ApiBuilder
import biped.works.statement.data.remote.StatementApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StatementModule {

    @Provides
    @Singleton
    fun providesStatementApi(builder: ApiBuilder) = builder.create(StatementApi::class.java)
}