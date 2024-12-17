package com.example.contacts.ui.composables.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<I : BaseIntent, S : BaseViewState>(
    initialViewState: S
) : ViewModel() {

    protected val viewStateFlow = MutableStateFlow(initialViewState)
    val viewState = viewStateFlow.asStateFlow()

    private val intentChannel = Channel<I>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                handleIntent(it)
            }
        }
    }

    fun sendIntent(intent: I) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    abstract fun handleIntent(intent: I)
}