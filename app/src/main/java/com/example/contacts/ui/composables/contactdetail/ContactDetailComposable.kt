package com.example.contacts.ui.composables.contactdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contacts.R
import com.example.contacts.data.local.room.entities.ContactEntity

@Composable
fun ContactDetailComposable(innerPadding: PaddingValues, item: ContactEntity) {
    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(4.dp)
                .size(86.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.LightGray)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = item.name,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = item.desc,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_call),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = item.phoneNum,
                color = Color.LightGray
            )
        }
        Spacer(modifier = Modifier
            .fillMaxSize()
            .weight(1f))
        Column {
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray),
                onClick = { }
            ) {
                Text(text = "Delete Contact", color = Color.Red)
            }
            Spacer(modifier = Modifier.size(1.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray),
                onClick = { }
            ) {
                Text(text = "Block Contact", color = Color.Red)
            }
        }
        Spacer(modifier = Modifier.size(64.dp))
    }

}

@Preview
@Composable
fun ContactDetailComposablePreview() {
    ContactDetailComposable(
        innerPadding = PaddingValues(16.dp),
        item = ContactEntity(
            R.drawable.ic_account_circle,
            "Tom Cruise",
            "545169721",
            "Actor"
        )
    )
}