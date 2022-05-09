package com.rodak.composeshowcase.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodak.composeshowcase.R
import com.rodak.composeshowcase.views.SearchInput

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewState: HomeScreenViewState,
    onEventSent: (event: HomeScreenEvent) -> Unit
) {
    BottomSheetScaffold(
        sheetPeekHeight = 20.dp,
        sheetContent = { BottomSheetContent(viewState, onEventSent) },
        sheetShape = RoundedCornerShape(
            topStart = 32.dp,
            topEnd = 32.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            TopAppBar(title = { Title() }, backgroundColor = Color.White)
            when {
                viewState.isLoading -> Loader()
                else -> FeatureList(viewState, onEventSent)
            }
        }
    }
}

@Composable
private fun BottomSheetContent(
    viewState: HomeScreenViewState,
    onEventSent: (event: HomeScreenEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(vertical = 48.dp)
    ) {
        SearchInput(viewState.query) { onEventSent(HomeScreenEvent.OnQueryChanged(it)) }
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(id = R.string.home_title),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
}

@Composable
private fun Loader() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
}

@Composable
private fun FeatureList(
    viewState: HomeScreenViewState,
    onEventSent: (event: HomeScreenEvent) -> Unit
) {
    LazyColumn(Modifier.padding(top = 40.dp)) {
        items(viewState.features) { feature ->
            FeatureLabel(feature = feature, onEventSent = onEventSent)
        }
    }
}

@Composable
private fun FeatureLabel(feature: Feature, onEventSent: (event: HomeScreenEvent) -> Unit) {
    Card(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(7.dp, CircleShape)
            .background(Color.White)
            .clickable { onEventSent(HomeScreenEvent.FeatureButtonClicked(feature)) },
    ) {
        Text(
            text = stringResource(id = feature.title),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
