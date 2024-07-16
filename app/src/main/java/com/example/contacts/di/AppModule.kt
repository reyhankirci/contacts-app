package com.example.contacts.di

import android.app.Application
import androidx.room.Room
import com.example.contacts.data.local.room.AppDatabase
import com.example.contacts.data.repository.ContactRepository
import com.example.contacts.data.repository.ContactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDataBase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "MyDataBase"
        ).build()
    }



    @Provides
    @Singleton
    fun provideContactRepository(database: AppDatabase) : ContactRepository {
        return ContactRepositoryImpl(database.contactDao)
    }
}