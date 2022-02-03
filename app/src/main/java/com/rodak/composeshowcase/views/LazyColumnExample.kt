package com.rodak.composeshowcase.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodak.composeshowcase.R

@Composable
fun LazyColumnExample(items: List<CardLabelInfo>) {
    LazyColumn {
        item { Title() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        items(items) { card -> CardLabel(card = card) }
        item { EndOfTheList() }
    }
}

@Composable
private fun Title() {
    Text(text = "Title", fontSize = 18.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
}

@Composable
private fun EndOfTheList() {
    Text(text = "The End", fontSize = 18.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
}
