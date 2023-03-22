package co.deltapay.common.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SolidButtonComponent(
    text: String,
    active: Boolean = true,
    modifier: Modifier = Modifier,
    hasImage: Boolean = false,
    drawableId: Int = co.deltapay.common.R.drawable.check,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    clicked: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            clicked()
        },
        content = {
            if (hasImage){
                Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = "check mark"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = text, color = textColor)
            } else {
                Text(text = text, color = textColor)
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        enabled = active,
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun TextButtonComponent(
    text: String,
    active: Boolean = false,
    modifier: Modifier = Modifier,
    textColor: Color,
    clicked: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = {
            clicked()
        },
        content = {
            Text(text = text, color = textColor)
        },
        enabled = active)
}

@Composable
fun OutlineButtonComponent(
    text: String,
    active: Boolean = false,
    modifier: Modifier = Modifier,
    textColor: Color,
    clicked: () -> Unit
) {
    OutlinedButton(
        onClick = { clicked() },
        modifier = modifier,
        content = {
            Text(text = text, color = textColor)
        },
        border = BorderStroke(1.dp, Color.Gray),
        enabled = active,
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun OutlineImageButtonComponent(
    text: String,
    active: Boolean = false,
    modifier: Modifier = Modifier,
    textColor: Color,
    clicked: () -> Unit
) {
    OutlinedButton(
        onClick = { clicked() },
        modifier = modifier,
        border = BorderStroke(1.dp, Color.Gray),
        enabled = active,
        shape = RoundedCornerShape(10.dp)
    ) {
        Image(
            painter = painterResource(id = co.deltapay.common.R.drawable.check),
            contentDescription = "check mark"
        )
        Text(text = text, color = textColor)
    }
}