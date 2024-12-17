package com.example.contacts.ui.composables.contactlist

import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.ui.composables.base.BaseIntent

sealed class ContactListIntent: BaseIntent {
    object FetchContactList: ContactListIntent()
    data class DeleteContactItem(val contactEntity: ContactEntity): ContactListIntent()
}