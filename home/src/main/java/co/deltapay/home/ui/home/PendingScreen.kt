package co.deltapay.home.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.deltapay.common.theme.AppTypography
//import co.deltapay.home.ui.LoggedInTopLayout
import co.deltapay.common.R as RCommon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            //LoggedInTopLayout(leftIconClicked = {})
        }
    ) {
        val state = homeViewModel.homeState.collectAsState().value

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Column(modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.TopStart),
                verticalArrangement = Arrangement.Top) {
                Text(text = "Pending registration", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "We are verifying your details", fontSize = 18.sp, fontWeight = FontWeight.Normal)
                Spacer(modifier = Modifier.size(32.dp))
                Card(
                    containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(75.dp, 75.dp)
                                .padding(6.dp)
                                .align(Alignment.CenterHorizontally),
                            painter = painterResource(id = RCommon.drawable.pending),
                            contentDescription = "pending icon",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Dear ",
                            fontSize = AppTypography.bodySmall.fontSize
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "You have successfully registered for Deltapay. \n" +
                                    "We will notify you once we process your application.\n" ,
                            fontSize = AppTypography.bodySmall.fontSize)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                /*when(state) {
                    is PendingState.StateReceived -> {
                        PendingItem("1", "Id Verification", state.idVerification)
                        Spacer(modifier = Modifier.height(16.dp))
                        PendingItem("2", "Phone Verification", state.phoneVerification)
                        Spacer(modifier = Modifier.height(16.dp))
                        PendingItem("3", "Email Verification", state.emailVerification)
                        Spacer(modifier = Modifier.height(16.dp))
                        PendingItem("4", "Document Verification", state.documentVerification)
                    }
                    else -> {}
                }*/
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter)
                    .padding(start = 16.dp, end = 16.dp, bottom = 72.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier,
                    onClick = { homeViewModel.goToMono()}) {
                    Text(text = "Mono", style = androidx.compose.material.MaterialTheme.typography.caption)
                }
                Button(
                    modifier = Modifier,
                    onClick = { homeViewModel.goHome()}) {
                    Text(text = "Explore", style = androidx.compose.material.MaterialTheme.typography.caption)
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingItem(
    number: String,
    title: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Card(
        containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$number. ",
                fontWeight = FontWeight.Bold,
                fontSize = AppTypography.labelMedium.fontSize
            )
            Text(
                text = title,
                fontSize = AppTypography.bodySmall.fontSize
            )
            Text(
                text = status,
                fontSize = AppTypography.bodySmall.fontSize
            )
        }
    }
}
