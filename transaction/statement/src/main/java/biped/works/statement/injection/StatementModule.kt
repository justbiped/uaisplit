package biped.works.statement.injection

import biped.works.api.http.ApiBuilder
import biped.works.statement.data.remote.StatementApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class StatementModule {

    @Provides
    @ViewModelScoped
    fun providesStatementApi(builder: ApiBuilder) : StatementApi = builder.create(StatementApi::class.java)
}