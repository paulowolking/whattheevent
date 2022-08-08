package com.wolking.whattheevent.presentation.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wolking.whattheevent.R

@Composable
fun Error(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_no_connection),
                contentDescription = "no connection",
                modifier = Modifier.padding(16.dp)
            )
            Text(text = message)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ErrorPreview() {
    Error("nenhum resultado encontrado")
}