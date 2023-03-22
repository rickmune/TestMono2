package co.deltapay.common.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.deltapay.common.R

@Composable
fun TopLayout(
    leftIconClicked: () -> Unit = {}
) {
    TopAppBar(
        content = {
            Spacer(modifier = Modifier.size(16.dp))
            Icon(
                modifier = Modifier.size(36.dp, 36.dp),
                painter = painterResource(id = R.drawable.dp_logo),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                modifier = Modifier.size(72.dp, 36.dp).align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.dp_text_green),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Logo"
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}
