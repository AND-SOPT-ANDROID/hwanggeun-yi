package org.sopt.and.presentation.home

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context : Context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        // Banner
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ){
                items(uiState.bannerContent) { content ->
                    Card(
                        modifier = Modifier
                            .width(screenWidth - 32.dp) // 화면 너비에서 패딩 제외
                            .height(400.dp)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = context.getString(content.title),
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }

            }

        }

        // Editor's Picks
        item {
            Text(
                text = stringResource(R.string.editor_title),
                modifier = Modifier.padding(16.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(uiState.editorPicks) { content ->
                    Card(
                        modifier = Modifier
                            .width(120.dp)
                            .height(180.dp)
                    ) {
                        Text(
                            text = context.getString(content.title),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }

        // Top 20
        item {
            Text(
                text = stringResource(R.string.top_example_title),
                modifier = Modifier.padding(16.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(uiState.top20) { content ->
                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .height(240.dp)
                    ) {
                        Text(
                            text = context.getString(content.title),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}