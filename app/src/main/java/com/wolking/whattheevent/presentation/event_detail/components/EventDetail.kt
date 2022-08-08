package com.wolking.whattheevent.presentation.event_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.wolking.whattheevent.R
import com.wolking.whattheevent.domain.event.entities.Event
import com.wolking.whattheevent.extensions.toMoneyReal

@Composable
fun EventDetail(event: Event) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = rememberAsyncImagePainter(event.image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(color = Color(R.color.purple_200)),
            contentDescription = "Image event",
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.h5,
            )
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(bottom = 8.dp),
            ) {
                Text(
                    text = event.price.toMoneyReal(),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = event.description,
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun EventDetailPreview() {
    EventDetail(
        event = Event(
            title = "Evento ",
            description = "Description",
            id = "",
            date = 0,
            image = "",
            longitude = 0.0,
            latitude = 0.0,
            price = 1.99
        )
    )
}