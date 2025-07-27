package com.example.newscompose.presentation.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newscompose.presentation.common.LoadingView
import com.example.newscompose.presentation.navgraph.Route
import com.example.newscompose.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.launch

/*
 * Author: Shivang Yadav
 * Created: 6/16/25
 * Description: [Add description here]
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController
) {

    val viewModel: LoginViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    val hostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = hostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "Login",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge) }
            )
        }
    ) { paddingValues ->

        Content(
            paddingValues = paddingValues,
            loginState = viewModel.login,
            resetPassState = viewModel.resetPassword,
            onNavigateToSignup = { navController.navigate(Route.SignupScreen) },
            onLogin = { email, password -> viewModel.login(email, password) },
            onForgotPassword = {email -> viewModel.resetPassword(email)},
            loginSuccess = { navController.navigate(Route.MainNav) },
            loginError = { scope.launch { hostState.showSnackbar(it) } },
            resetPassSuccess = { scope.launch { hostState.showSnackbar(it) } },
            resetPassError = { scope.launch { hostState.showSnackbar(it) } },
            snackBarHost = hostState
        )
    }
}

@Composable
fun Content(
    paddingValues: PaddingValues,
    loginState:  Resource<AuthResult>,
    resetPassState: Resource<Void?>,
    onLogin: (String, String) -> Unit,
    onForgotPassword: (String) -> Unit,
    onNavigateToSignup: () -> Unit,
    loginSuccess: () -> Unit,
    loginError: (String) -> Unit,
    resetPassSuccess: (String) -> Unit,
    resetPassError: (String) -> Unit,
    snackBarHost: SnackbarHostState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        val txtFieldEmail = remember { mutableStateOf("") }
        val txtFieldEmailError = remember { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            isError = txtFieldEmailError.value,
            value = txtFieldEmail.value,
            leadingIcon = { Icon(Icons.Filled.Email, "email") },
            onValueChange = {
                txtFieldEmail.value = it
            },
            label = { Text(text = "Email") },
            singleLine = true,
            placeholder = { Text(text = "Enter your email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )

        val txtFieldPassword = remember { mutableStateOf("") }
        val txtFieldPasswordError = remember { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, end = 20.dp, top = 5.dp),
            isError = txtFieldPasswordError.value,
            value = txtFieldPassword.value,
            leadingIcon = { Icon(Icons.Filled.Lock, "password") },
            onValueChange = {
                txtFieldPassword.value = it
            },
            label = { Text(text = "Password") },
            singleLine = true,
            placeholder = { Text(text = "Enter your password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )


        Button(
            onClick = {
                txtFieldEmailError.value= txtFieldEmail.value.isEmpty()
                txtFieldPasswordError.value = txtFieldPassword.value.isEmpty()

                if (txtFieldEmailError.value || txtFieldPasswordError.value) {
                    return@Button
                }

                onLogin(
                    txtFieldEmail.value,
                    txtFieldPassword.value
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .height(50.dp),
            shape = RoundedCornerShape(50.dp),
            content = { Text(text = "Login") }
        )

        val showForgetPasswordDialog = remember { mutableStateOf(false) }

        val scope = rememberCoroutineScope()

        if (showForgetPasswordDialog.value)

            MyAlertDialog(
                onDismissRequest = { showForgetPasswordDialog.value = false },
                onConfirmation = {
                    if (txtFieldEmail.value != "") {
                        onForgotPassword(txtFieldEmail.value)
                        showForgetPasswordDialog.value = false
                    } else {
                        scope.launch {
                            snackBarHost.showSnackbar("Please enter email address")
                        }
                    }
                },
                title = "Forgot Password?",
                text = "Send a password reset email to entered email address.",
                confirmButtonText = "Send",
                dismissButtonText = "Cancel",
                cancelable = true
            )

        Text(
            style = MaterialTheme.typography.titleMedium.copy(
                MaterialTheme.colorScheme.primary
            ),
            text = "Forgot Password?",

            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(20.dp)
                .clickable { showForgetPasswordDialog.value= true }
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = buildAnnotatedString {
                append("Do not have an account?")
                withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) { append(" Signup") }
            },
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(20.dp)
                .clickable { onNavigateToSignup() }
        )
    }

    LoginState(
        loginState = loginState,
        onSuccess = { loginSuccess() },
        onError = { loginError(it) }
    )

    ResetPasswordState(
        resetPassState= resetPassState,
        onError = { resetPassError(it) },
        onSuccess = { resetPassSuccess(it) }
    )
}

@Composable
fun MyAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: String,
    text: String,
    confirmButtonText: String,
    dismissButtonText: String,
    cancelable: Boolean
) {
    AlertDialog(
        title = { Text(text = title) },
        text = { Text(text = text) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = { onConfirmation() }
            ) {
                Text(confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissRequest() }
            ) {
                Text(dismissButtonText)
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = cancelable,
            dismissOnClickOutside = cancelable,
        )
    )
}

@Composable
fun LoginState(
    loginState: Resource<AuthResult>,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {

    when(loginState) {
        is Resource.Error -> {
            onError(loginState.message!!)
        }
        is Resource.Idle -> {

        }
        is Resource.Loading -> {
            LoadingView(PaddingValues(10.dp))
        }
        is Resource.Success -> {
            onSuccess()
        }
    }
}


@Composable
fun ResetPasswordState(
    resetPassState: Resource<Void?>,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
) {
    when (resetPassState) {
        is Resource.Error -> {
            onError("Error in resetting password")
        }

        is Resource.Idle -> {

        }

        is Resource.Loading -> {
            LoadingView(PaddingValues(10.dp))
        }

        is Resource.Success -> {
            onSuccess("Check your email")
        }
    }
}