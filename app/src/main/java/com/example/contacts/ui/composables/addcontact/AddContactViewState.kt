package com.example.contacts.ui.composables.addcontact

import com.example.contacts.ui.composables.base.BaseViewState

abstract class AddContactViewState: BaseViewState {
    object Inactive: AddContactViewState()
    object Loading: AddContactViewState()
    data class Error(val error: Throwable?): AddContactViewState()
    data class ContactIsAdded(val data:Boolean): AddContactViewState()
}
