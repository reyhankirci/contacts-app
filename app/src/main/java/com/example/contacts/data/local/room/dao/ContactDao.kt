package com.example.contacts.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contacts.data.local.room.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contactEntity: ContactEntity): Long

    @Delete
    suspend fun delete(contactEntity: ContactEntity): Int

    @Update
    suspend fun update(contactEntity: ContactEntity): Int

    @Query("SELECT * FROM contacts WHERE phoneNum = :phoneNum")
    fun getContact(phoneNum: String): Flow<ContactEntity>

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<ContactEntity>>
}