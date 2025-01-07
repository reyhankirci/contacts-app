package com.example.contacts.ui.composables.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S : BaseViewState>(
    initialViewState: S
) : ViewModel() {

    protected val viewStateFlow = MutableStateFlow(initialViewState)
    val viewState = viewStateFlow.asStateFlow()

}