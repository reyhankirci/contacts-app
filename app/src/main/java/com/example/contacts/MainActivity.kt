package com.example.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contacts.ui.theme.ContactsappTheme
import com.example.contacts.ui.composables.addcontact.AddContactComposable
import com.example.contacts.ui.composables.contactlist.ContactListComposable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsappTheme {
                Scaffold(
                    topBar = @androidx.compose.runtime.Composable {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = Color.DarkGray,
                                titleContentColor = Color.White,
                            ),
                            title = {
                                Text("Contacts")
                            }

                        )
                    },
                    bottomBar = {},
                    content = { innerPadding ->
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = "contact-list") {
                            composable("contact-list") {
                                ContactListComposable(
                                    innerPadding = innerPadding,
                                    navController = navController
                                )
                            }
                            composable("add-contact") {
                                AddContactComposable(
                                    innerPadding = innerPadding,
                                    navController = navController
                                )
                            }
                        }
                    },
                    containerColor = Color.DarkGray
                )
            }
        }
    }
}