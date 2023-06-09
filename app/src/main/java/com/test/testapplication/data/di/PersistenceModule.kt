package com.test.testapplication.data.di

import android.app.Application
import androidx.room.Room
import com.test.myapplication.R
import com.test.testapplication.data.persistence.AppDatabase
import com.test.testapplication.data.persistence.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                application.getString(R.string.database)
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePosterDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.posterDao()
    }
}
