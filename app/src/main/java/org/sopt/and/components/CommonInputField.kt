package org.sopt.and.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onVisibilityChange: (() -> Unit)? = null,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        if (value.isEmpty()) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            modifier = Modifier.fillMaxWidth(),

            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isPassword && onVisibilityChange != null) {
                    Text(
                        modifier = Modifier
                            .clickable(onClick = onVisibilityChange),
                        text = if (passwordVisible) "Hide" else "Show",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
                        color = Color.White
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,  // 포커스 됐을 때 테두리 색
                unfocusedBorderColor = Color.Transparent,  // 포커스 해제됐을 때 테두리 색
                disabledBorderColor = Color.Transparent  // 비활성화됐을 때 테두리 색
            )
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

@Composable
@Preview
fun InputFieldPreview() {
    InputField(
        placeholder = "Placeholder",
        value = "Value",
        onValueChange = {},
        isPassword = true,
        passwordVisible = true,
        onVisibilityChange = {}
    )

}