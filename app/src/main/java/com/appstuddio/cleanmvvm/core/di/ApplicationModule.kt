package com.appstuddio.cleanmvvm.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.appstuddio.cleanmvvm.core.functional.Prefs
import com.appstuddio.cleanmvvm.data.local.MovieDatabase
import com.appstuddio.cleanmvvm.data.local.dao.MovieDao
import com.appstuddio.cleanmvvm.data.repository.MoviesRepository
import com.bumptech.glide.integration.recyclerview.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application){

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providePreferences() = Prefs(application)

    @Provides
    @Singleton
    fun providerRoom(): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "app").build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(demoDatabase:MovieDatabase) : MovieDao {
        return demoDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(dataSource: MoviesRepository.Network): MoviesRepository = dataSource

}