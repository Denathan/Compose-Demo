package com.rodak.composeshowcase.lazycolumn

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rodak.composeshowcase.R
import com.rodak.composeshowcase.views.LazyColumnExample

@Composable
fun LazyColumnScreen(
    viewState: LazyColumnScreenViewState,
    onEventSent: (event: LazyColumnScreenEvent) -> Unit
) {
    Column {
        TopAppBar(title = { Title() }, backgroundColor = Color.White)
        LazyColumnExample(items = viewState.items)
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(id = R.string.lazy_column_title),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
}

