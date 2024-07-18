package com.example.contacts.ui.composables.contactlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.contacts.R
import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.ui.components.MContactItem

@Composable
fun ContactListComposable(
    innerPadding: PaddingValues,
    viewModel: ContactListViewModel = hiltViewModel<ContactListViewModel>(),
    navController: NavController
) {

    ContactListComposableUI(
        innerPadding = innerPadding,
        viewModel.contactList.collectAsStateWithLifecycle().value,
        onClickAddContact = { navController.navigate("add-contact") }
    )
}

@Composable
fun ContactListComposableUI(
    innerPadding: PaddingValues,
    contactList: List<ContactEntity>,
    onClickAddContact: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Row {
            MContactItem(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .weight(0.8f),
                item = ContactEntity(R.drawable.ic_account_circle, "Me", "1245635844", "My number")
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
                MContactItem(
                    modifier = Modifier.background(Color.DarkGray),
                    item = contact
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
        contactList = listOf(
            ContactEntity(R.drawable.ic_account_circle, "My Mom", "545169721", "Family"),
            ContactEntity(R.drawable.ic_account_circle, "My Dad", "545169721", "Family"),
            ContactEntity(R.drawable.ic_account_circle, "My Manager", "545169721", "Work"),
            ContactEntity(R.drawable.ic_account_circle, "Tom Cruise", "545169721", "Actor")
        ),
        onClickAddContact = {}
    )
}