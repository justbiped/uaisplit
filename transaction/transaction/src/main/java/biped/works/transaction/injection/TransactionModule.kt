package biped.works.transaction.injection

import biped.works.api.http.ApiBuilder
import biped.works.transaction.data.remote.TransactionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class TransactionModule {

    @Provides
    @ViewModelScoped
    fun providesTransactionApi(builder: ApiBuilder) = builder.create(TransactionApi::class.java)
}
