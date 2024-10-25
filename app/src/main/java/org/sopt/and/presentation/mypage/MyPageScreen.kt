package org.sopt.and.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.ANDANDROIDTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    viewModel: MyPageViewModel = viewModel(),
    email : String?
) {
    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = stringResource(R.string.my_page_profile_image),
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(
                            text = if (email != null) email else stringResource(R.string.my_page_not_find_email),
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = { TODO() }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { TODO() }) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.my_page_first_subtitle),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = stringResource(R.string.my_page_first_title),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.my_page_second_subtitle),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = stringResource(R.string.my_page_second_title),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)){
                    Text(
                        text = stringResource(R.string.my_page_total_view_history),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    EmptyStateMessage(stringResource(R.string.my_page_not_view_history))
                }


                Column(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)){
                    Text(
                        text = stringResource(R.string.my_page_like_program),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    EmptyStateMessage(stringResource(R.string.my_page_not_found_program))
                }
            }

        }
    )

}

@Composable
fun EmptyStateMessage(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(60.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            color = Color.Gray,
            style = MaterialTheme.typography.titleMedium
        )
    }
}