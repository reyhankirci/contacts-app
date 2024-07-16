package com.example.contacts.data.repository

import com.example.contacts.data.ResponseState
import com.example.contacts.data.asResponseState
import com.example.contacts.data.local.room.dao.ContactDao
import com.example.contacts.data.local.room.entities.ContactEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(private val contactDao: ContactDao) :
    ContactRepository {

    override suspend fun insert(contactEntity: ContactEntity): Flow<ResponseState<Long>> {
        return flow {
            emit(contactDao.insert(contactEntity))
        }.asResponseState()
    }

    override suspend fun delete(contactEntity: ContactEntity): Int {
        return contactDao.delete(contactEntity)
    }

    override suspend fun update(contactEntity: ContactEntity): Int {
        return contactDao.update(contactEntity)
    }

    override fun getContact(phoneNum: String): Flow<ContactEntity> {
        return contactDao.getContact(phoneNum)
    }

    override fun getAllContacts(): Flow<ResponseState<List<ContactEntity>>> {
        return contactDao.getAllContacts()
            .asResponseState()
    }

}