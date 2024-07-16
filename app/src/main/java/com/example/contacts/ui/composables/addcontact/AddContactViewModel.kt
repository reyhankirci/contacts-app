package com.example.contacts.ui.composables.addcontact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.data.ResponseState
import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.data.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(private val contactRepo: ContactRepository) :
    ViewModel() {

    private val isAddedContactStateFlow = mutableStateOf(false)
    val isAddedContact: State<Boolean> = isAddedContactStateFlow

    fun insertContact(contactEntity: ContactEntity) {
        viewModelScope.launch {
            contactRepo.insert(contactEntity).collect { response ->
                when (response) {
                    is ResponseState.Success -> {
                        isAddedContactStateFlow.value = response.data != 0L
                    }

                    is ResponseState.Loading -> {}
                    is ResponseState.Error -> {}
                }
            }
        }
    }
}