package org.sopt.and.presentation.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
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
            val pagerState = rememberPagerState(
                pageCount = { uiState.bannerContent.size },
                initialPage = 0
            )
            LaunchedEffect(key1 = pagerState) {
                while (true) {
                    delay(3000L)
                    val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                    pagerState.animateScrollToPage(nextPage)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ){
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 48.dp),
                    pageSpacing = 16.dp

                ) { page ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        RoundedImage(
                            imageRes = uiState.bannerContent[page].imageRes,
                            contentDescription = "Image${uiState.bannerContent[page].title}",
                            modifier = Modifier,
                            width = screenWidth - 96.dp,
                            height = 400.dp,
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.BottomCenter)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black
                                        )
                                    )
                                )
                        )

                        Text(
                            text = "$page / ${uiState.bannerContent.size}",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(horizontal = 32.dp, vertical = 16.dp)
                        )

                    }
                }
            }

        }

        // Editor's Picks
        item {
            SectionTitle(
                text = stringResource(R.string.editor_title),
                modifier = Modifier
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(uiState.editorPicks) { content ->
                    ImageWithTextCard(
                        imageRes = content.imageRes,
                        title = context.getString(content.title),
                        modifier = Modifier
                    )
                }
            }
        }

        // Top 20
        item {
            SectionTitle(
                text = stringResource(R.string.top_example_title),
                modifier = Modifier
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(uiState.top20) { content ->
                    ImageWithTextCard(
                        imageRes = content.imageRes,
                        title = "Image${content.title}",
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    color: Color = Color.White
) {
    Text(
        text = text,
        modifier = modifier.padding(16.dp),
        style = style,
        color = color
    )
}

@Composable
fun RoundedImage(
    imageRes: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    width: Dp = 120.dp,
    height: Dp = 180.dp,
    cornerRadius: Dp = 16.dp
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = contentDescription,
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(cornerRadius)),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ImageWithTextCard(
    imageRes: Int,
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        RoundedImage(
            imageRes = imageRes,
            contentDescription = "Image$title"
        )
        Text(
            text = title,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleSmall.copy(lineHeight = 32.sp)
        )
    }
}

@Preview
@Composable
fun HomeScreenPrevice(){
    HomeScreen()
}