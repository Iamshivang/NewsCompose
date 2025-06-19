package com.example.newscompose.presentation.auth.signup

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
import androidx.compose.runtime.MutableState
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
fun SignupScreen(
    navController: NavHostController
) {

    val viewModel: SignupViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    val hostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = hostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "Signup",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Content(
            paddingValues = paddingValues,
            signupState = viewModel.signup,
            onNavigateToLogin = { navController.popBackStack() },
            onSignup = { name, email, password -> viewModel.signup(name, email, password) },
            registerSuccess = { navController.navigate(Route.MainNav)},
            registerError = { scope.launch { hostState.showSnackbar(it) } }
        )
    }
}

@Composable
fun Content(
    paddingValues: PaddingValues,
    signupState:  Resource<AuthResult>,
    onSignup: (String, String, String) -> Unit,
    onNavigateToLogin: () -> Unit,
    registerSuccess: @Composable () -> Unit,
    registerError: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        val txtFieldName = remember { mutableStateOf("") }
        val txtFieldNameError = remember { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            isError = txtFieldNameError.value,
            value = txtFieldName.value,
            leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "name") },
            onValueChange = {
                txtFieldName.value = it
            },
            label = { Text(text = "Name") },
            singleLine = true,
            placeholder = { Text(text = "Enter your name") }
        )

        val txtFieldEmail = remember { mutableStateOf("") }
        val txtFieldEmailError = remember { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, end = 20.dp, top = 5.dp),
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
                txtFieldNameError.value = txtFieldName.value.isEmpty()
                txtFieldEmailError.value= txtFieldEmail.value.isEmpty()
                txtFieldPasswordError.value = txtFieldPassword.value.isEmpty()

                if (txtFieldNameError.value || txtFieldEmailError.value || txtFieldPasswordError.value) {
                    return@Button
                }

                onSignup(
                    txtFieldName.value,
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
            content = { Text(text = "Signup") }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = buildAnnotatedString {
                append("Already have an account?")
                withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) { append(" Login") }
            },
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(20.dp)
                .clickable { onNavigateToLogin() }
        )
    }
    SignupState(
        signupState = signupState,
        onSuccess = { registerSuccess() },
        onError = { registerError(it) }
    )
}

@Composable
fun SignupState(
    signupState: Resource<AuthResult>,
    onSuccess: @Composable () -> Unit,
    onError: (String) -> Unit
) {

    when(signupState) {
        is Resource.Error -> {
//            ErrorView(signupState.message!!, PaddingValues(10.dp))
            onError(signupState.message!!)
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

//@Composable
//@Preview(showBackground = true)
//fun PreviewSignupScreen(){
//
//    SignupScreen(rememberNavController())
//}
//
//@Composable
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//fun PreviewSignupScreenDark(){
//
//    SignupScreen(rememberNavController())
//}