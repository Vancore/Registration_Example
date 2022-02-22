package com.example.registrationzkb.di

import android.content.Context
import com.example.registrationzkb.data.DataStoreManager
import com.example.registrationzkb.data.DataStoreManagerImpl
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
}