package com.example.contacts.di.components

import com.example.contacts.data.local.room.entities.UserEntity
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Scope



// to able to scope types to UserComponent.
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class UserScope




@UserScope
@DefineComponent(parent = SingletonComponent::class)
interface UserComponent {

    // Builder to create instances of UserComponent
    @DefineComponent.Builder
    interface Builder {
        @BindsInstance
        fun setUser(user: UserEntity): Builder
        fun build(): UserComponent
    }
}