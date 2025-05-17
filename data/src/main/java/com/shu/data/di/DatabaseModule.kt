package com.shu.data.di

import android.content.Context
import com.shu.data.db.AppDatabase
import com.shu.data.db.OffersDao
import com.shu.data.db.VacanciesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideOffersDao(appDatabase: AppDatabase): OffersDao {
        return appDatabase.offersDao()
    }

    @Provides
    fun provideVacanciesDao(appDatabase: AppDatabase): VacanciesDao {
        return appDatabase.vacanciesDao()
    }
}
