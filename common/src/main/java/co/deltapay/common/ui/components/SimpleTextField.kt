package co.deltapay.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleTextField(
    text: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    helper: String? = null,
    errorText: String? = null,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    label: @Composable (() -> Unit)? = null,
    hasFocus: () -> Unit = {},
    enabled: Boolean = true,
    modifierTextFiled: Modifier = Modifier.fillMaxWidth(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        autoCorrect = true,
        keyboardType = KeyboardType.Text,
        imeAction = imeAction,
    ),
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            modifier = modifierTextFiled
                .onFocusChanged {
                    if (it.hasFocus) {
                        hasFocus()
                    }
                },
            label = label,
            isError = !isValid,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() },
            ),
            enabled = enabled,
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                backgroundColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
        )
        if (!isValid) {
            Text(
                text = errorText.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
            )
        } else {
            helper?.let {
                Text(
                    text = helper,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
