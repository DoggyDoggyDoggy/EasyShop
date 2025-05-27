package denys.diomaxius.easyshop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.easyshop.domain.repository.AuthRepository
import denys.diomaxius.easyshop.data.repository.AuthRepositoryImpl
import denys.diomaxius.easyshop.domain.repository.BannerRepository
import denys.diomaxius.easyshop.data.repository.BannerRepositoryImpl
import denys.diomaxius.easyshop.domain.repository.CategoryRepository
import denys.diomaxius.easyshop.data.repository.CategoryRepositoryImpl
import denys.diomaxius.easyshop.data.repository.ProductDetailsRepositoryImpl
import denys.diomaxius.easyshop.data.repository.ProductRepositoryImpl
import denys.diomaxius.easyshop.domain.repository.ProductDetailsRepository
import denys.diomaxius.easyshop.domain.repository.ProductRepository
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

    @Provides
    @Singleton
    fun provideCategoriesRepository(): CategoryRepository = CategoryRepositoryImpl()

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository = ProductRepositoryImpl()

    @Provides
    @Singleton
    fun provideProductDetailsRepository(): ProductDetailsRepository = ProductDetailsRepositoryImpl()
}