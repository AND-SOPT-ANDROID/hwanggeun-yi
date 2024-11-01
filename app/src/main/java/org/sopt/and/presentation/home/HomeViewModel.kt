package org.sopt.and.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.and.R


class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
}

data class HomeUiState(
    val bannerContent: List<ContentItem> = listOf(
        ContentItem(R.string.banner_example_1, R.drawable.img1),
        ContentItem(R.string.banner_example_2, R.drawable.img2),
        ContentItem(R.string.banner_example_3, R.drawable.img3),
        ContentItem(R.string.banner_example_4, R.drawable.img4)
    ),
    val editorPicks: List<ContentItem> = listOf(
        ContentItem(R.string.banner_example_1, R.drawable.img1),
        ContentItem(R.string.banner_example_2, R.drawable.img2),
        ContentItem(R.string.banner_example_3, R.drawable.img3),
        ContentItem(R.string.banner_example_4, R.drawable.img4)
    ),
    val top20: List<ContentItem> = listOf(
        ContentItem(R.string.banner_example_1, R.drawable.img1),
        ContentItem(R.string.banner_example_2, R.drawable.img2),
        ContentItem(R.string.banner_example_3, R.drawable.img3),
        ContentItem(R.string.banner_example_4, R.drawable.img4)
    )
)


data class ContentItem(
    @StringRes val title: Int,
    @DrawableRes val imageRes: Int
)
