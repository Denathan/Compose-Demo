package com.rodak.composeshowcase.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OrderExample() {
    Box(
        Modifier
            .background(Color.LightGray)
            .padding(16.dp)
            .clickable {  }
    ) {
        Box(
            modifier = Modifier
                .shadow(7.dp, RectangleShape)
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Yellow)
                        .size(40.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun OrderPreview() {
    OrderExample()
}

@Composable
fun RefactoredOrderExample() {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(16.dp)
            .shadow(7.dp, RectangleShape)
            .background(Color.White)
            .padding(16.dp)
            .clickable {  }
    ) {
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .size(40.dp)
        )
    }
}

@Preview
@Composable
fun RefactoredOrderPreview() {
    RefactoredOrderExample()
}
