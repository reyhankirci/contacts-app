package com.example.contacts.ui.composables.addcontact

import androidx.lifecycle.viewModelScope
import com.example.contacts.data.ResponseState
import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.data.repository.ContactRepository
import com.example.contacts.ui.composables.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(private val contactRepo: ContactRepository) :
    BaseViewModel<AddContactViewState>(AddContactViewState.Inactive) {

        fun insertContact(contactEntity: ContactEntity) {
        viewModelScope.launch {
            contactRepo.insert(contactEntity).collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        viewStateFlow.value =
                            AddContactViewState.ContactIsAdded(response.data != 0L)
                    }

                    is ResponseState.Loading -> {
                        viewStateFlow.value = AddContactViewState.Loading
                    }

                    is ResponseState.Error -> {
                        viewStateFlow.value = AddContactViewState.Error(response.exception)
                    }
                }
            }
        }
    }
}