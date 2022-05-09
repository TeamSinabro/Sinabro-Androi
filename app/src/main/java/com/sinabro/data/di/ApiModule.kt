package com.sinabro.data.di

import androidx.databinding.ktx.BuildConfig
import com.sinabro.data.api.pronounce.PronounceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun pronounceBaseUrl() = "http://aiopen.etri.re.kr:8000/"

    @Singleton
    @Provides
    fun provideOkHttpClient() =if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient.Builder().build()
        }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(pronounceBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun pronounceService(retrofit: Retrofit) : PronounceService{
        return retrofit.create(PronounceService::class.java)
    }


}