package com.example.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.contacts.data.local.room.entities.UserEntity
import com.example.contacts.di.managers.UserManager
import com.example.contacts.ui.theme.ContactsappTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity: ComponentActivity() {

    @Inject lateinit var userManager: UserManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ContactsappTheme {
                Scaffold(
                    topBar = {},
                    bottomBar = {},
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .padding(innerPadding)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonColors(
                                    Color.White,
                                    Color.White,
                                    Color.White,
                                    Color.White
                                ),
                                onClick = { createUserSessionAndFinish() },
                            ) {
                                Text(
                                    text = "Login",
                                    color = Color.DarkGray
                                )
                            }
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonColors(
                                    Color.White,
                                    Color.White,
                                    Color.White,
                                    Color.White
                                ),
                                onClick = { closeUserSessionAndFinish() },
                            ) {
                                Text(
                                    text = "Logout",
                                    color = Color.DarkGray
                                )
                            }
                        }
                    },
                    containerColor = Color.DarkGray
                )
            }
        }
    }

    private fun createUserSessionAndFinish() {
        userManager.createUserSession(UserEntity("12354485656dewedhqdjqwkjn", "test name", "test surname", "1234567"))
        finish()
    }

    private fun closeUserSessionAndFinish() {
        userManager.closeUserSession()
        finish()
    }
}