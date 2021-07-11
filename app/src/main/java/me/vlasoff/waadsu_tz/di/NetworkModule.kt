package me.vlasoff.waadsu_tz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.vlasoff.waadsu_tz.data.network.ApiService
import me.vlasoff.waadsu_tz.data.repos.GeoRepository
import me.vlasoff.waadsu_tz.domain.usecases.GetGeoDataUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val URL = "https://waadsu.com/api/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit : Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideRepository(apiService: ApiService) = GeoRepository(apiService)

    @Provides
    fun provideGetGeoDataUseCase(repository: GeoRepository) = GetGeoDataUseCase(repository)
}