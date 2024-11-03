package org.sopt.and.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.components.InputField
import org.sopt.and.components.PasswordInputField
import org.sopt.and.components.SocialLoginRow
import org.sopt.and.navigation.Screen
import org.sopt.and.utils.KeyStorage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onSignUpSuccess: (String, String) -> Unit
){
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val signUpSuccess by viewModel.signUpSuccess.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState("")
    val snackbarHostState = remember { SnackbarHostState() }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(signUpSuccess) {
        if (signUpSuccess) {
            onSignUpSuccess(email, password)
        }
    }

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(errorMessage)
            viewModel.errorMessage.value = ""
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.sign_up_top_bar), style = MaterialTheme.typography.titleMedium) },
                modifier = Modifier
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = stringResource(R.string.sign_up_title),
                    style = MaterialTheme.typography.titleLarge.copy(lineHeight = 32.sp),
                    color = Color.White, fontWeight = FontWeight.Normal,
                )

                InputField(
                    modifier = Modifier,
                    placeholder = stringResource(R.string.sign_up_email_input_example),
                    value = email,
                    onValueChange = { viewModel.setEmail(it) }
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = stringResource(R.string.sign_up_email_sub_title),
                    style = MaterialTheme.typography.bodySmall, color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )

                PasswordInputField(
                    modifier = Modifier,
                    placeholder = stringResource(R.string.sign_up_password_input_example),
                    value = password,
                    onValueChange = { viewModel.setPassword(it) },
                    passwordVisible = passwordVisible,
                    onVisibilityChange = {passwordVisible = !passwordVisible}
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = stringResource(R.string.sign_up_password_sub_title),
                    style = MaterialTheme.typography.bodySmall, color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.weight(0.5f))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Gray))
                        Text(stringResource(R.string.sign_up_social_title), modifier = Modifier.padding(horizontal = 8.dp), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Normal)
                        Spacer(modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Gray))

                    }
                }
                SocialLoginRow()
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = stringResource(R.string.sign_up_social_subtitle),

                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                    color = Color.Gray, fontWeight = FontWeight.Normal,
                )

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { viewModel.onSignUpClick { onSignUpSuccess(email, password) } },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    shape = RectangleShape
                ) {
                    Text(stringResource(R.string.sign_up_btn))
                }
            }
        }
    )
}

private fun isValidEmail(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
private fun isValidPassword(password: String): Boolean = Regex(KeyStorage.EMAIL_REGEX).matches(password)

@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen(
        modifier = Modifier,
        onSignUpSuccess = { email, password ->

        }
    )
}