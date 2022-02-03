package com.rodak.composeshowcase.input

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import com.rodak.composeshowcase.R
import com.rodak.composeshowcase.input.InputScreenEvent.OnQueryChange
import com.rodak.composeshowcase.views.EditText

@Composable
fun InputScreen(
    viewState: InputScreenViewState,
    onEventSent: (event: InputScreenEvent) -> Unit
) {
    Title()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.paddingMedium)),
        contentAlignment = Center
    ) {
        when {
            viewState.isLoading -> Loader()
            else -> EditText(query = viewState.query) { onEventSent(OnQueryChange(it)) }
        }
    }
}

@Composable
private fun Title() {
    Box(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.paddingMedium)),
        contentAlignment = Center
    ) {
        Text(
            text = stringResource(id = R.string.input_title),
            fontWeight = Bold,
            fontSize = 24.sp
        )
    }
}

@Composable
private fun Loader() {
    CircularProgressIndicator(color = MaterialTheme.colors.primary)
}
