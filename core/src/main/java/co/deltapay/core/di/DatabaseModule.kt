package co.deltapay.core.di

import android.content.Context
import co.deltapay.core.database.OnBoardingDataStorage
import co.deltapay.core.database.PreferenceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppPreferences(context: Context) = PreferenceStorage(context)

    @Singleton
    @Provides
    fun provideOnBoardingPreferences(context: Context) = OnBoardingDataStorage(context)
}