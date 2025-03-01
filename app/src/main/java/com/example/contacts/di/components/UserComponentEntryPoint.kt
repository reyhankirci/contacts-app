package com.example.contacts.di.components

import com.example.contacts.data.local.room.entities.UserEntity
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@InstallIn(UserComponent::class)
@EntryPoint
interface UserComponentEntryPoint {
    fun user(): UserEntity
}