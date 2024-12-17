package com.example.contacts.ui.composables.addcontact

import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.ui.composables.base.BaseIntent

sealed class AddContactIntent: BaseIntent {
    data class AddContact(val contactEntity: ContactEntity): AddContactIntent()
}