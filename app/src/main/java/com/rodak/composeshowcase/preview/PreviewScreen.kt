package com.rodak.composeshowcase.preview

import androidx.compose.runtime.Composable
import com.rodak.composeshowcase.R
import com.rodak.composeshowcase.views.CardLabel
import com.rodak.composeshowcase.views.CardLabelInfo

@Composable
fun PreviewScreen(
    viewState: PreviewScreenViewState,
    onEventSent: (event: PreviewScreenEvent) -> Unit
) {
    val card = CardLabelInfo("Title1", "subtitle1", R.drawable.baseline_search_24, "value1")
    CardLabel(card)
}
