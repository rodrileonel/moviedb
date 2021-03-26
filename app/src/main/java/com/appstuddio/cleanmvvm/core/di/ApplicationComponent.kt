package com.appstuddio.cleanmvvm.core.di

import com.appstuddio.cleanmvvm.AndroidApplication
import com.appstuddio.cleanmvvm.core.di.viewmodel.ViewModelModule
import com.appstuddio.cleanmvvm.features.movies.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent{
    fun inject(application: AndroidApplication)
    fun inject(moviesFragment: MoviesFragment)
}