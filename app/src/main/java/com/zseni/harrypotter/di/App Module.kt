package com.zseni.harrypotter.di

import android.content.Context
import com.zseni.harrypotter.HogwartsApp
import com.zseni.harrypotter.core.data.HarryPotterApi
import com.zseni.harrypotter.core.domain.use_cases.*
import com.zseni.harrypotter.core.presentation.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
/**
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(AppInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    **/

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext app:Context):
        HogwartsApp{
        return app as HogwartsApp
    }

    @Provides
    @Singleton
    fun providesRetrofit():HarryPotterApi{
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(HarryPotterApi::class.java)
    }
/**
    @Provides
    @Singleton
    fun provideHarryPotterApi(retrofit: Retrofit): HarryPotterApi =
        retrofit.create(HarryPotterApi::class.java)
    **/

    @Provides
    @Singleton
    fun provideHogwartsUseCases(
        harryPotterApi: HarryPotterApi
    ) =
        HogwartsUseCases(
            hogwartsCharacterIdUseCase = HogwartsCharacterIdUseCase(harryPotterApi),
            hogwartsCharacter = HogwartsCharacters(harryPotterApi),
            hogwartsAllStaff = HogwartsStaffsUseCase(harryPotterApi),
            hogwartsSpells = HogwartsSpells(harryPotterApi),
            hogwartsStudents = HogwartsStudents(harryPotterApi),
            hogwartsCharacterByHouse = HogwartsCharactersByHouse(harryPotterApi)
        )
}

