package co.deltapay.onboarding.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.deltapay.common.ui.components.PasswordTextField
import co.deltapay.common.ui.components.SimpleTextField
import co.deltapay.common.ui.components.TextButtonComponent
import co.deltapay.common.ui.components.TopLayout
import co.deltapay.common.utils.ProgressIndicator
import co.deltapay.onboarding.extensions.isValidEmail
import co.deltapay.common.R as RCommon

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginStates,
    onRegisterPress: () -> Unit,
    onLoginClicked: (username: String, password: String) -> Unit,
    onForgotPassword: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val nameErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    var isValidEmail by remember { mutableStateOf(true) }
    var showProgress by remember { mutableStateOf(false) }
    var hasEmail by remember {
        mutableStateOf(false)
    }
    when (state) {
        is LoginStates.HasEmail -> {
            email.value = state.email
            hasEmail = true
        }
        else -> {
        }
    }

    Scaffold(
        topBar = { TopLayout(leftIconClicked = {}) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
            ) {
                if (hasEmail) {
                    ExistingInstance(email.value)
                } else {
                    NewToInstance(email.value, isValidEmail){
                        email.value = it
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                PasswordTextField(
                    text = password.value,
                    labelText = stringResource(id = RCommon.string.sign_pin),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    password.value = it
                }

                TextButtonComponent(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(id = RCommon.string.forgot_password),
                    active = true,
                    clicked = { onForgotPassword() },
                    textColor = MaterialTheme.colorScheme.primary
                )


                Spacer(modifier = Modifier.size(32.dp))

                Row(modifier = Modifier) {
                    Button(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f),
                        onClick = {
                            focusManager.clearFocus()
                            showProgress = true
                            onLoginClicked(
                                email.value,
                                password.value
                            )
                        },
                        content = {
                            Text(
                                text = stringResource(id = RCommon.string.sign_in),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        enabled = (email.value.isValidEmail() && password.value.isNotEmpty()),
                        shape = RoundedCornerShape(10.dp)
                    )

                    val modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(
                                5.dp
                            )
                        )

                    Spacer(modifier = Modifier.size(32.dp))

                    Image(
                        modifier = modifier
                            .padding(6.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = RCommon.drawable.fingerprint),
                        contentDescription = "finger print",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                    )
                }

            }
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colorScheme.background)
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(modifier = Modifier) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = stringResource(id = RCommon.string.no_account)
                    )
                    TextButtonComponent(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = stringResource(id = RCommon.string.welcome_signup),
                        active = true,
                        clicked = { onRegisterPress() },
                        textColor = MaterialTheme.colorScheme.primary
                    )
                }
            }
            when (state) {
                is LoginStates.Error -> {

                }
                else -> {
                    if (showProgress) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.6f)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ProgressIndicator(Modifier.size(width = 100.dp, height = 100.dp))
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun NewToInstance(email: String, isValidEmail: Boolean, valueChanged: (email: String) -> Unit) {
    Text(
        text = stringResource(id = RCommon.string.sign_in),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.size(16.dp))

    SimpleTextField(
        text = email,
        onValueChange = { value ->
            valueChanged(value)
        },
        modifier = Modifier,
        isValid = isValidEmail,
        errorText = stringResource(
            id = RCommon.string.required,
            stringResource(id = RCommon.string.email)
        ),
        label = { Text(text = stringResource(id = RCommon.string.email)) }
    )
}

@Composable
fun ExistingInstance(email: String) {
    Text(
        text = "Welcome back,",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.size(32.dp))

    Text(
        text = email,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}
