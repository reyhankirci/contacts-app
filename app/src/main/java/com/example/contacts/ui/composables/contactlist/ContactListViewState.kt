package com.example.contacts.ui.composables.contactlist

import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.ui.composables.base.BaseViewState

abstract class ContactListViewState: BaseViewState {
    object Inactive:ContactListViewState()
    object Loading: ContactListViewState()
    data class Error(val error: Throwable?): ContactListViewState()
    data class ContactListData(val data:List<ContactEntity>): ContactListViewState()
    data class ContactIsDeleted(val data:Boolean): ContactListViewState()
}