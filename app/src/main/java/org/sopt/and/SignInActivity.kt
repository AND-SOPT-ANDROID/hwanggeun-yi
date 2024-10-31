package org.sopt.and

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.components.InputField
import org.sopt.and.components.PasswordInputField
import org.sopt.and.components.SocialLoginRow
import org.sopt.and.presentation.MainActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.utils.KeyStorage
import java.io.Console

class SignInActivity : ComponentActivity() {
    private var savedEmail: String? = null
    private var savedPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedEmail = intent.getStringExtra(KeyStorage.EMAIL)
        savedPassword = intent.getStringExtra(KeyStorage.PASSWORD)

        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme(true) {
                SignInScreen(savedEmail, savedPassword)
            }
        }
    }
}

fun handleSignUp(context : Context){
    val intent = Intent(context, SignUpActivity::class.java)
    context.startActivity(intent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    savedEmail: String?,
    savedPassword: String?
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    fun onSignInClick() {
        coroutineScope.launch {
            if(email == savedEmail && password == savedPassword) {
                snackbarHostState.showSnackbar("로그인에 성공했습니다.")
                delay(1L)
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra(KeyStorage.EMAIL, email)
                    putExtra(KeyStorage.PASSWORD, password)

                }
                context.startActivity(intent)
            } else {
                snackbarHostState.showSnackbar("로그인에 실패했습니다.")
            }
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
                    Modifier,
                    stringResource(R.string.sign_in_email_placeholder),
                    email,
                    {email = it}
                )

                PasswordInputField(
                    Modifier,
                    stringResource(R.string.sign_in_password_placeholder),
                    password,
                    { password = it },
                    passwordVisible,
                    { passwordVisible = !passwordVisible },
                )


                Button(
                    onClick = { onSignInClick() },
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
                                .clickable(onClick = {
                                    handleSignUp(context)
                                }),
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



@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    ANDANDROIDTheme(true) {
        SignInScreen("","")
    }
}