package com.example.contacts.ui.composables.contactlist

import androidx.lifecycle.viewModelScope
import com.example.contacts.data.ResponseState
import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.data.repository.ContactRepository
import com.example.contacts.ui.composables.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(private val contactRepo: ContactRepository) :
    BaseViewModel<ContactListIntent, ContactListViewState>(ContactListViewState.Inactive) {

    init {
        sendIntent(ContactListIntent.FetchContactList)
    }

    private fun getContactList() {
        viewModelScope.launch {
            contactRepo.getAllContacts().collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        viewStateFlow.value = ContactListViewState.ContactListData(response.data)
                    }
                    is ResponseState.Loading -> {
                        viewStateFlow.value = ContactListViewState.Loading
                    }
                    is ResponseState.Error -> {
                        viewStateFlow.value = ContactListViewState.Error(response.exception)
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
                        viewStateFlow.value = ContactListViewState.ContactIsDeleted(true)
                    }
                    is ResponseState.Loading -> {
                        viewStateFlow.value = ContactListViewState.Loading
                    }
                    is ResponseState.Error -> {
                        viewStateFlow.value = ContactListViewState.Error(response.exception)
                    }
                }
            }
        }
    }

    override fun handleIntent(intent: ContactListIntent) {
        when(intent) {
            ContactListIntent.FetchContactList -> {
                getContactList()
            }
            is ContactListIntent.DeleteContactItem -> {
                deleteContact(intent.contactEntity)
            }
        }
    }
}