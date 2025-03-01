package com.example.contacts.data.repository.contact

import com.example.contacts.data.ResponseState
import com.example.contacts.data.local.room.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    suspend fun insert(contactEntity: ContactEntity): Flow<ResponseState<Long>>

    suspend fun delete(contactEntity: ContactEntity): Flow<ResponseState<Int>>

    suspend fun update(contactEntity: ContactEntity): Int

    fun getContact(phoneNum: String): Flow<ContactEntity>

    fun getAllContacts(): Flow<ResponseState<List<ContactEntity>>>
}