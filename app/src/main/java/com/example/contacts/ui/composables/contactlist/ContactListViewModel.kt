package com.example.contacts.ui.composables.contactlist

import androidx.lifecycle.viewModelScope
import com.example.contacts.data.ResponseState
import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.data.repository.ContactRepository
import com.example.contacts.ui.composables.base.BaseViewModel
import com.example.contacts.ui.composables.base.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(private val contactRepo: ContactRepository) :
    BaseViewModel() {

    private val intentChannel = Channel<ContactListIntent>(Channel.UNLIMITED)

    init {
        handleIntent()
        sendIntent(ContactListIntent.FetchContactList)
    }

    fun sendIntent(intent: ContactListIntent) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when(it) {
                    ContactListIntent.FetchContactList -> {
                        getContactList()
                    }
                    is ContactListIntent.DeleteContactItem -> {
                        deleteContact(it.contactEntity)
                    }
                }
            }
        }
    }

    private fun getContactList() {
        viewModelScope.launch {
            contactRepo.getAllContacts().collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        viewStateFlow.value = BaseViewState.Success(response.data)
                    }
                    is ResponseState.Loading -> {
                        viewStateFlow.value = BaseViewState.Loading
                    }
                    is ResponseState.Error -> {
                        viewStateFlow.value = BaseViewState.Error(response.exception)
                    }
                }
            }
        }
    }

    private fun deleteContact(contactEntity: ContactEntity) {
        viewModelScope.launch {
            contactRepo.delete(contactEntity).collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        viewStateFlow.value = BaseViewState.Success(response.data)
                    }
                    is ResponseState.Loading -> {
                        viewStateFlow.value = BaseViewState.Loading
                    }
                    is ResponseState.Error -> {
                        viewStateFlow.value = BaseViewState.Error(response.exception)
                    }
                }
            }
        }
    }
}