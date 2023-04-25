package com.test.testapplication.data.di

import com.test.testapplication.data.service.ApiService
import com.test.testapplication.ui.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        apiService: ApiService
    ): LoginRepository {
        return LoginRepository(apiService)
    }
}
