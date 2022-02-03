package com.rodak.composeshowcase.views

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodak.composeshowcase.R
import com.rodak.composeshowcase.ui.theme.ComposeShowcaseTheme

data class CardLabelInfo(
    val title: String,
    val subtitle: String,
    @DrawableRes val image: Int,
    val value: String
)

@Composable
fun CardLabel(
    card: CardLabelInfo,
    backgroundColor: Color = Color.White,
    onCardClick: () -> Unit = {}
) =
    with(card) {
        Card(
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            backgroundColor = backgroundColor
        ) {
            Box(modifier = Modifier.clickable { onCardClick() }) {
                Row(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        Row {
                            Text(
                                text = title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1.0f),
                                color = Color.Black
                            )
                            Text(
                                text = value,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = subtitle, fontSize = 16.sp, color = Color.Black)
                    }
                }
            }
        }
    }

@Preview(name = "Preview1", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Preview with background", showBackground = true)
@Preview(name = "Preview with dark mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCardLabel() {
    ComposeShowcaseTheme {
        val card = CardLabelInfo("Title1", "subtitle1", R.drawable.baseline_search_24, "value1")
        CardLabel(card, MaterialTheme.colors.background)
    }
}

@Preview(name = "Preview yellow background")
@Composable
private fun PreviewCardLabel_Yellow() {
    val card = CardLabelInfo("Title1", "subtitle1", R.drawable.baseline_search_24, "value1")
    CardLabel(card, Color.Yellow)
}

@Preview(name = "Preview full screen", showSystemUi = true)
@Composable
private fun PreviewCardLabel_SystemUI() {
    val card = CardLabelInfo("Title2", "subtitle2", R.drawable.baseline_search_24, "value2")
    val cardsList = List(10) { card }

    LazyColumn {
        items(cardsList) { item ->
            CardLabel(card = item)
        }
    }
}
