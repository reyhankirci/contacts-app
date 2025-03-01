package com.example.contacts.di.managers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.contacts.data.local.room.entities.UserEntity
import com.example.contacts.di.components.UserComponent
import com.example.contacts.di.components.UserComponentEntryPoint
import dagger.hilt.EntryPoints
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


// UserManager will be in charge of managing the UserComponent's
@Singleton
class UserManager @Inject constructor(
    private val userComponentProvider: Provider<UserComponent.Builder>
) {
    var userComponent: UserComponent? = null
        private set

    var isUserSessionStarted: MutableState<Boolean> = mutableStateOf(false)
        private set


    fun createUserSession(userEntity: UserEntity) {
        userComponent = userComponentProvider.get().setUser(userEntity).build()
        isUserSessionStarted.value = true
    }

    fun closeUserSession() {
        userComponent = null
        isUserSessionStarted.value = false
    }

    fun getUserData(): UserEntity {
        return EntryPoints.get(
            userComponent, UserComponentEntryPoint::class.java
        ).user()
    }
}