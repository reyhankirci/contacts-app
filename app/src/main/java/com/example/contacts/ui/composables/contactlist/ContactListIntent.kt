package com.example.contacts.ui.composables.contactlist

import com.example.contacts.data.local.room.entities.ContactEntity

sealed class ContactListIntent {
    object FetchContactList: ContactListIntent()
    data class DeleteContactItem(val contactEntity: ContactEntity): ContactListIntent()
}