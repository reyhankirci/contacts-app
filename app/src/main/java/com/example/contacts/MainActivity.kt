package com.example.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contacts.di.managers.UserManager
import com.example.contacts.ui.theme.ContactsappTheme
import com.example.contacts.ui.composables.addcontact.AddContactComposable
import com.example.contacts.ui.composables.contactlist.ContactListComposable
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userManager: UserManager

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
                            },
                            actions = {
                                IconButton(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .weight(0.2f)
                                        .wrapContentWidth(Alignment.End),
                                    colors = IconButtonColors(
                                        Color.Blue,
                                        Color.White,
                                        Color.DarkGray,
                                        Color.LightGray
                                    ),
                                    onClick = { navigateLoginActivity() }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_account_circle),
                                        contentDescription = "Login/Logout"
                                    )
                                }
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

    private fun navigateLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}