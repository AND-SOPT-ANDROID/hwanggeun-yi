package org.sopt.and

import android.R.attr
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Paint.Align
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import org.sopt.and.components.InputField
import org.sopt.and.components.PasswordInputField
import org.sopt.and.components.SocialLoginRow
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.utils.KeyStorage


class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme (true) {
                SignUpScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(){
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showErrorSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    fun onSignUpClick() {
        if (!isValidEmail(email) || !isValidPassword(password)) {
            showErrorSnackbar = true
            return
        }

        val intent = Intent(context, SignInActivity::class.java).apply {
            putExtra(KeyStorage.EMAIL, email)
            putExtra(KeyStorage.PASSWORD, password)
        }

        (context as SignUpActivity).setResult(RESULT_OK,intent)
        context.startActivity(intent)
    }

    LaunchedEffect(showErrorSnackbar) {
        if (showErrorSnackbar) {
            snackbarHostState.showSnackbar(context.getString(R.string.sign_up_failure))
            showErrorSnackbar = false
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
                    onValueChange = {email = it})

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = stringResource(R.string.sign_up_email_sub_title),
                    style = MaterialTheme.typography.bodySmall, color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )

                PasswordInputField(
                    Modifier,
                    stringResource(R.string.sign_up_password_input_example),
                    password,
                    { password = it },
                    passwordVisible,
                    { passwordVisible = !passwordVisible },
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
                    onClick = { onSignUpClick() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    shape = RectangleShape
                ){
                    Text(stringResource(R.string.sign_up_btn))
                }
            }
        }
    )
}


private fun isValidEmail(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
private fun isValidPassword(password: String): Boolean = Regex(KeyStorage.EMAIL_REGEX).matches(password)



@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    ANDANDROIDTheme(true) {
        SignUpScreen()
    }
}