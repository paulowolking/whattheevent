package com.wolking.whattheevent.presentation.events.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.wolking.whattheevent.R
import com.wolking.whattheevent.domain.event.entities.Event


@ExperimentalMaterialApi
@Composable
fun EventRow(
    event: Event,
    onClickEvent: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        onClick = onClickEvent,
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .height(intrinsicSize = IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            Card(
                shape = CircleShape,
                border = BorderStroke(1.dp, color = Color(R.color.purple_200)),
                modifier = Modifier.size(64.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(event.image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .background(color = Color(R.color.purple_200)),
                    contentDescription = "Image event",
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
            ) {
                Text(event.title, fontWeight = FontWeight.Bold)
                Text(
                    text = event.description,
                    style = MaterialTheme.typography.body2,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun EventRowPreview() {
    EventRow(
        event = Event(
            title = "Evento ",
            description = "Description",
            id = "",
            date = 0,
            image = "",
            longitude = 0.0,
            latitude = 0.0,
            price = 1.99
        ), onClickEvent = {})
}