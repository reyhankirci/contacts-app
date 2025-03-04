package com.example.contacts.ui.composables.contactlist

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.contacts.R
import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.data.local.room.entities.UserEntity
import com.example.contacts.ui.components.MAnchoredDraggableBox
import com.example.contacts.ui.components.MContactItem
import com.example.contacts.ui.components.MContentItemDelete

@Composable
fun ContactListComposable(
    innerPadding: PaddingValues,
    viewModel: ContactListViewModel = hiltViewModel<ContactListViewModel>(),
    navController: NavController
) {
    var contactList by remember { mutableStateOf(listOf<ContactEntity>()) }
    var isLoading by remember { mutableStateOf(false) }
    val isUserSessionStarted by remember { viewModel.userManager.isUserSessionStarted }

    var userData: UserEntity? = null
    if (isUserSessionStarted) {
        userData = viewModel.userManager.getUserData()
    }

    viewModel.viewState.collectAsStateWithLifecycle().value.let { value ->
        when (value) {
            ContactListViewState.Inactive -> {}
            ContactListViewState.Loading -> {
                isLoading = true
            }

            is ContactListViewState.ContactListData -> {
                contactList = value.data
                isLoading = false
            }

            is ContactListViewState.ContactIsDeleted -> {
                isLoading = false
                Toast.makeText(LocalContext.current, "The contact is deleted!", Toast.LENGTH_LONG)
                    .show()
            }

            is ContactListViewState.Error -> {
                isLoading = false
                Toast.makeText(LocalContext.current, "Somethings went wrong!", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }



    ContactListComposableUI(
        innerPadding = innerPadding,
        isLoading,
        userData,
        contactList,
        onClickAddContact = { navController.navigate("add-contact") },
        onClickDelete = { contactEntity ->
            viewModel.deleteContact(contactEntity)
        }
    )
}

@Composable
fun ContactListComposableUI(
    innerPadding: PaddingValues,
    isLoading: Boolean,
    userData: UserEntity?,
    contactList: List<ContactEntity>,
    onClickAddContact: () -> Unit,
    onClickDelete: (contactEntity: ContactEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            Column(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(48.dp),
                    color = Color.White,
                    trackColor = Color.Gray,
                )
            }
            return
        }
        Row {
            MContactItem(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .weight(0.8f),
                item = ContactEntity(R.drawable.ic_account_circle, userData?.userName ?: "", userData?.phoneNum ?: "", "")
            )
            IconButton(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.2f)
                    .wrapContentWidth(Alignment.End),
                colors = IconButtonColors(Color.Blue, Color.White, Color.DarkGray, Color.LightGray),
                onClick = onClickAddContact
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
            }

        }
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn {
            items(contactList) { contact ->
                val isItemDeleted = remember { mutableStateOf(false) }
                val deleteComponentWidthSize = 100.dp
                MAnchoredDraggableBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    firstContent = { modifier ->
                        MContactItem(
                            modifier = modifier.background(Color.DarkGray),
                            item = contact
                        )
                    },
                    secondContent = { modifier ->
                        MContentItemDelete(
                            modifier = modifier.width(deleteComponentWidthSize),
                            onClick = {
                                onClickDelete.invoke(contact)
                                isItemDeleted.value = true
                            })
                    },
                    offsetSize = deleteComponentWidthSize,
                    returnInitialState = isItemDeleted.value
                )
                HorizontalDivider(color = Color.Gray)
            }
        }
    }
}

@Preview
@Composable
fun ContactListComposableUIPreview() {
    ContactListComposableUI(
        innerPadding = PaddingValues(16.dp),
        isLoading = false,
        userData = UserEntity("","","",""),
        contactList = listOf(
            ContactEntity(R.drawable.ic_account_circle, "My Mom", "545169721", "Family"),
            ContactEntity(R.drawable.ic_account_circle, "My Dad", "545169721", "Family"),
            ContactEntity(R.drawable.ic_account_circle, "My Manager", "545169721", "Work"),
            ContactEntity(R.drawable.ic_account_circle, "Tom Cruise", "545169721", "Actor")
        ),
        onClickAddContact = {},
        onClickDelete = { _ -> }
    )
}