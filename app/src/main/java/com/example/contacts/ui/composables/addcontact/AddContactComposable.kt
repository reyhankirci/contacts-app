package com.example.contacts.ui.composables.addcontact

import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contacts.R
import com.example.contacts.data.local.room.entities.ContactEntity


@Composable
fun AddContactComposable(
    innerPadding: PaddingValues,
    viewModel: AddContactViewModel = hiltViewModel<AddContactViewModel>(),
    navController: NavController
) {
    val isAddedContact by viewModel.isAddedContact

    LaunchedEffect(key1 = isAddedContact) {
        if (isAddedContact) {
            navController.navigateUp()
        }
    }

    AddContactComposableUI(
        innerPadding = innerPadding,
        onClickAddContact = { name, phoneNum, desc ->
            viewModel.insertContact(
                ContactEntity(
                    imageResId = R.drawable.ic_account_circle,
                    name = name,
                    phoneNum = phoneNum,
                    desc = desc
                )
            )
        },
        onClickCancel = { navController.navigateUp() }
    )
}

@Composable
fun AddContactComposableUI(
    innerPadding: PaddingValues,
    onClickAddContact: (name: String, phoneNum: String, desc: String) -> Unit,
    onClickCancel: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNum by remember { mutableStateOf(TextFieldValue("")) }
    var desc by remember { mutableStateOf(TextFieldValue("")) }

    val textFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.LightGray,
        cursorColor = Color.Black,
        focusedIndicatorColor = Color.Gray
    )

    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(64.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            label = { Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Name") },
            maxLines = 1,
            onValueChange = { newText ->
                name = newText
            },
            colors = textFieldColors,
        )
        Spacer(modifier = Modifier.size(1.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNum,
            label = { Text(text = "Phone Num") },
            maxLines = 1,
            leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "") },
            onValueChange = { newText ->
                phoneNum = newText
            },
            colors = textFieldColors,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.size(1.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = desc,


            label = { Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Desc") },
            maxLines = 1,
            onValueChange = { newText ->
                desc = newText
            },
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.imePadding()) {
            val interactionSourceCancel = remember { MutableInteractionSource() }
            val interactionSourceAdd = remember { MutableInteractionSource() }
            val indication = rememberRipple(color = Color.LightGray)
            Button(
                modifier = Modifier
                    .weight(0.5f)
                    .indication(interactionSourceCancel, indication),
                colors = ButtonColors(
                    Color.DarkGray,
                    Color.DarkGray,
                    Color.DarkGray,
                    Color.DarkGray
                ),
                onClick = onClickCancel,
                interactionSource = interactionSourceCancel
            ) {
                Text(text = "Cancel", color = Color.White)
            }

            Button(
                modifier = Modifier
                    .weight(0.5f)
                    .indication(interactionSourceAdd, indication),
                colors = ButtonColors(
                    Color.DarkGray,
                    Color.DarkGray,
                    Color.DarkGray,
                    Color.DarkGray
                ),
                onClick = {
                    onClickAddContact(name.text, phoneNum.text, desc.text)
                },
                enabled = phoneNum.text.isNotEmpty(),
                interactionSource = interactionSourceAdd
            ) {
                Text(
                    text = "Add Contact",
                    color = if (phoneNum.text.isEmpty()) Color.LightGray else Color.White
                )
            }

        }
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Preview
@Composable
fun AddContactComposablePreview() {
    AddContactComposableUI(
        PaddingValues(16.dp),
        onClickAddContact = { _, _, _ -> },
        onClickCancel = {})
}