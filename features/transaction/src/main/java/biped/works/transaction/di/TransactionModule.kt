package biped.works.transaction.di

import biped.works.transaction.data.remote.TransactionApi
import com.favoriteplaces.core.http.HttpManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class TransactionModule {
    //@ViewModelScoped
    @Provides
    fun providesTransactionApi(httpManager: HttpManager): TransactionApi {
        return httpManager.instantiate(TransactionApi::class.java, "https://uaisplit.up.railway.app/")
    }
}