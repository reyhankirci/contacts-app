package com.example.contacts.ui.composables.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel: ViewModel() {

    protected val viewStateFlow = MutableStateFlow<BaseViewState>(BaseViewState.Inactive)
    val viewState = viewStateFlow.asStateFlow()

}