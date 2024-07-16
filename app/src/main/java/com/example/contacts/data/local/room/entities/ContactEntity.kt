package com.example.contacts.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    val imageResId: Int,
    val name: String,
    val phoneNum: String,
    val desc: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
