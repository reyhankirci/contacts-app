package com.example.contacts.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contacts.R
import com.example.contacts.data.local.room.entities.ContactEntity
import com.example.contacts.ui.theme.Pink40

@Composable
fun MContactItem(modifier: Modifier, item: ContactEntity) {
    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(4.dp)
                .size(52.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.LightGray)
                .align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = item.name,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )
            Text(
                text = item.desc,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Thin
            )
        }
    }

}

@Preview
@Composable
fun ContactItemPreview() {
    MContactItem(
        modifier = Modifier.background(Color.DarkGray),
        item = ContactEntity(R.drawable.ic_account_circle, "Tom Cruise", "545169721", "Actor")
    )
}

@Composable
fun MContentItemDelete(modifier: Modifier,onClick: () -> Unit) {
    TextButton(
        modifier = modifier
            .width(100.dp)
            .fillMaxHeight(),
        colors = ButtonColors(
            Pink40,
            Pink40,
            Pink40,
            Pink40
        ),
        shape = RectangleShape,
        onClick = onClick
    ) {
        Text(text = "Delete", color = Color.White)
    }
}