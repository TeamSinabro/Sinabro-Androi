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
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun pronounceBaseUrl() = "http://aiopen.etri.re.kr:8000/"


    @Provides
    fun SinabroBaseUrl() = "http://192.168.25.59:8080/"

    @Singleton
    @Provides
    fun provideOkHttpClient() =if (!BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient.Builder().build()
        }

    @PronounceBaseRetrofit
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(pronounceBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SinabroBaseRetrofit
    @Singleton
    @Provides
    fun provideBaseRetrofit(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(SinabroBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PronounceBaseRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SinabroBaseRetrofit

}