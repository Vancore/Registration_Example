package com.example.registrationzkb.di

import android.content.Context
import com.example.registrationzkb.data.DataStoreManager
import com.example.registrationzkb.data.DataStoreManagerImpl
import com.example.registrationzkb.usecases.ValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistrationModule {

    @Provides
    @Singleton
    fun providesLocalDatabase(@ApplicationContext appContext: Context) : DataStoreManager {
        return DataStoreManagerImpl(appContext)
    }

    @Provides
    @Singleton
    fun providesValidationUseCase(): ValidationUseCase {
        // here you could provide the repository that fetches the global time
        return ValidationUseCase()
    }
}