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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newscompose.presentation.auth.signup.Content
import com.example.newscompose.presentation.auth.signup.SignupViewModel
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
    navController: NavController
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
            onNavigateToSignup = { navController.navigate(Route.SignupScreen) },
            onLogin = { email, password -> viewModel.login(email, password) },
            loginSuccess = { navController.navigate(Route.HomeScreen) },
            loginError = { scope.launch { hostState.showSnackbar(it) } }
        )
    }
}

@Composable
fun Content(
    paddingValues: PaddingValues,
    loginState:  Resource<AuthResult>,
    onLogin: (String, String) -> Unit,
    onNavigateToSignup: () -> Unit,
    loginSuccess: () -> Unit,
    loginError: (String) -> Unit
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
                .padding(20.dp),
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
        is Resource.Success<*> -> {
            onSuccess()
        }
    }
}