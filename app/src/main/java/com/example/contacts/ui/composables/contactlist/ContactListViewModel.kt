package com.example.contacts.ui.composables.contactlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.data.ResponseState
import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.data.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(private val contactRepo: ContactRepository) : ViewModel() {

    init {
        getContactList()
    }

    private val contactListStateFlow = MutableStateFlow(emptyList<ContactEntity>())
    val contactList = contactListStateFlow.asStateFlow()

    private fun getContactList() {
        viewModelScope.launch {
            contactRepo.getAllContacts().collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        contactListStateFlow.value = response.data
                    }
                    is ResponseState.Loading -> {}
                    is ResponseState.Error -> {}
                }
            }
        }
    }
}