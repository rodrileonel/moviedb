package com.appstuddio.cleanmvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appstuddio.cleanmvvm.data.local.dao.MovieDao
import com.appstuddio.cleanmvvm.features.movies.models.Movie

@Database(entities = [Movie::class],version = 2)
abstract class MovieDatabase: RoomDatabase(){
    abstract fun movieDao(): MovieDao

}