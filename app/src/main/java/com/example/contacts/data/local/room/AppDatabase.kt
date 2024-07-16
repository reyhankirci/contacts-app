package com.example.contacts.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contacts.data.local.room.dao.ContactDao
import com.example.contacts.data.local.room.entities.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val contactDao: ContactDao

}