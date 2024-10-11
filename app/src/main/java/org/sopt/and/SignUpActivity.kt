package org.sopt.and

import android.R.attr
import android.graphics.Paint.Align
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme


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
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("회원가입", style = MaterialTheme.typography.titleMedium) },
                modifier = Modifier
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "이메일과 비밀번호만으로\n" +
                            "Wavve를 즐길 수 있어요!",
                    style = MaterialTheme.typography.titleLarge.copy(lineHeight = 32.sp),
                    color = Color.White, fontWeight = FontWeight.Normal,
                )

                InputField("wavve@email.com", email, {email = it})
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.",
                    style = MaterialTheme.typography.bodySmall, color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )

                PasswordInputField ("SoptAnd123@@", password, {password = it}, passwordVisible, {passwordVisible = it},)
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
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
                        Spacer(modifier = Modifier.weight(1f).height(1.dp).background(Color.Gray))
                        Text("또는 다른 서비스 계정으로 가입", modifier = Modifier.padding(horizontal = 8.dp), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Normal)
                        Spacer(modifier = Modifier.weight(1f).height(1.dp).background(Color.Gray))
                    }
                }
                SocialLoginRow()
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "SNS계정으로 간편하게 기입하여 서비스를 이용하실 수 있습니다. 기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요",
                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                    color = Color.Gray, fontWeight = FontWeight.Normal,
                )

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    shape = RectangleShape
                ){
                    Text("Wavve 회원가입")
                }
            }
        }
    )
}

@Composable
fun InputField(
    placeholder: String,
    value : String,
    onValueChange: (String) -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PasswordInputField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean,
    onVisibilityChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp), // 입력 필드 내부 패딩
        contentAlignment = Alignment.CenterStart
    ) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SocialLoginRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        SocialLoginButton(iconResId = R.drawable.ic_launcher_background)
        SocialLoginButton(iconResId = R.drawable.ic_launcher_background)
        SocialLoginButton(iconResId = R.drawable.ic_launcher_background)
        SocialLoginButton(iconResId = R.drawable.ic_launcher_background)
        SocialLoginButton(iconResId = R.drawable.ic_launcher_background)

    }
}

@Composable
fun SocialLoginButton(iconResId: Int) {
    IconButton(onClick = { TODO()}) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    ANDANDROIDTheme(true) {
        SignUpScreen()
    }
}