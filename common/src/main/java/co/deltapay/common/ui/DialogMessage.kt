package co.deltapay.common.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogMessage(
    title: String,
    message: String,
    show: Boolean = true,
    onDismiss: () -> Unit
) {
    val openDialog = remember { mutableStateOf(show)  }
    if (openDialog.value) {
        AlertDialog(
            title = { Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)},
            text = { Text(text = message, color = MaterialTheme.colorScheme.onBackground)},
            containerColor = MaterialTheme.colorScheme.background,
            confirmButton = {

                TextButton(onClick = { openDialog.value = false }) {
                    Text(text = "OK")
                }
            },
            onDismissRequest = {
                openDialog.value = false
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}