package denys.diomaxius.easyshop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.easyshop.data.repository.AuthRepository
import denys.diomaxius.easyshop.data.repository.AuthRepositoryImpl
import denys.diomaxius.easyshop.data.repository.BannerRepository
import denys.diomaxius.easyshop.data.repository.BannerRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

    @Provides
    @Singleton
    fun provideBannerRepository(): BannerRepository = BannerRepositoryImpl()
}