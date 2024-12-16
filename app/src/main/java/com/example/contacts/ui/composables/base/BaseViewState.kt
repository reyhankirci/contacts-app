package com.example.contacts.ui.composables.base

sealed class BaseViewState {
    object Inactive: BaseViewState()
    object Loading: BaseViewState()
    data class Success(val data: Any): BaseViewState()
    data class Error(val error: Throwable?): BaseViewState()
}