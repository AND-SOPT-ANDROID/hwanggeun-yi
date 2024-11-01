package org.sopt.and.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.components.InputField
import org.sopt.and.components.SocialLoginRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    modifier: Modifier,
    onNavigateToSignUp: () -> Unit,
    onSignInSuccess: (String, String) -> Unit,
    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    var passwordVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val errorMessage by viewModel.errorMessage.observeAsState("")

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(errorMessage)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.sign_in_title), style = MaterialTheme.typography.titleMedium) },
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
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                InputField(
                    modifier = Modifier,
                    placeholder = stringResource(R.string.sign_in_email_placeholder),
                    value = email,
                    onValueChange = { viewModel.setEmail(it) }
                )

                InputField(
                    modifier = Modifier,
                    placeholder = stringResource(R.string.sign_in_password_placeholder),
                    value = password,
                    onValueChange = { viewModel.setPassword(it) },
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onVisibilityChange = { passwordVisible = !passwordVisible },
                )


                Button(
                    onClick = { viewModel.onSignInClick(onSignInSuccess) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                ){

                    Text(stringResource(R.string.sign_in_button), color = Color.White, modifier = Modifier.padding(vertical = 8.dp))

                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(stringResource(R.string.sign_in_find_id), modifier = Modifier.padding(horizontal = 8.dp), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Normal, color = Color.Gray)

                        Text("|", modifier = Modifier.padding(horizontal = 8.dp), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Normal, color = Color.Gray)

                        Text(stringResource(R.string.sign_in_reset_password), modifier = Modifier.padding(horizontal = 8.dp), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Normal, color = Color.Gray)

                        Text("|", modifier = Modifier.padding(horizontal = 8.dp), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Normal,color = Color.Gray)

                        Text(
                            stringResource(R.string.sign_in_to_sign_up),
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clickable(onClick = onNavigateToSignUp),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(0.2f))
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
                        Text(stringResource(R.string.sign_in_social_title), modifier = Modifier.padding(horizontal = 8.dp), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Normal)
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
                    text = stringResource(R.string.sign_in_social_subtitle),

                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                    color = Color.Gray, fontWeight = FontWeight.Normal,
                )

                Spacer(modifier = Modifier.weight(1f))

            }
        }
    )
}
