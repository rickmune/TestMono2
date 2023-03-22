package co.deltapay.core.di

import co.deltapay.core.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
   abstract fun providesNoAuthRepository(noAuthRepository: NoAuthRepositoryImpl): NoAuthRepository

}