package com.wolking.whattheevent.presentation.event_detail.components

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.wolking.whattheevent.R

@Composable
fun DialogCheckIn(
    name: MutableState<String>,
    email: MutableState<String>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var isErrorName by rememberSaveable { mutableStateOf(false) }
    var isErrorEmail by rememberSaveable { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .padding(5.dp),
            shape = RoundedCornerShape(5.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = stringResource(R.string.make_check_in),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.padding(10.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = name.value,
                    onValueChange = { name.value = it },
                    singleLine = true,
                    label = { Text(text = stringResource(R.string.name)) },
                    placeholder = { Text(text = stringResource(R.string.name)) },
                    isError = isErrorName,
                    keyboardActions = KeyboardActions {
                        isErrorName = name.value.isEmpty()
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    value = email.value,
                    onValueChange = { email.value = it },
                    singleLine = true,
                    label = { Text(text = stringResource(R.string.email)) },
                    placeholder = { Text(text = stringResource(R.string.email)) },
                    isError = isErrorEmail,
                    keyboardActions = KeyboardActions {
                        isErrorEmail = email.value.isEmpty() || !isFormValid(email.value)
                    }
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    onClick = {
                        isErrorName = name.value.isEmpty()
                        isErrorEmail = email.value.isEmpty() || !isFormValid(email.value)
                        if (!isErrorName && !isErrorEmail) {
                            onConfirm.invoke()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(60.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = stringResource(R.string.confirm), color = Color.White,
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

fun isFormValid(emailAddress: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun DialogFriendTextFieldPreview() {
    DialogCheckIn(
        name = mutableStateOf(""),
        email = mutableStateOf(""),
        onConfirm = {},
        onDismiss = {}
    )
}