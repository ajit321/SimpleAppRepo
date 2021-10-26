package com.sample.dishes.di

import com.sample.dishes.BuildConfig
import com.sample.dishes.data.remote.api.ApiService
import com.sample.dishes.data.remote.utils.moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    companion object {
        const val TIME_OUT = 120
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Named("simple")
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    }

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .callTimeout(TIME_OUT.toLong(), TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .followRedirects(true)
            .followSslRedirects(true)
    }

    @Provides
    fun provideApiService(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("simple")
        retrofitBuilder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder,
    ): ApiService {
        return retrofitBuilder
            .client(
                okHttpClientBuilder
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
            )
            .build()
            .create(ApiService::class.java)
    }
}