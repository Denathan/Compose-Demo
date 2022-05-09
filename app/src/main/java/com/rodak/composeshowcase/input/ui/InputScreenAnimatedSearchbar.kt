package com.rodak.composeshowcase.input.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.rodak.composeshowcase.R

@Composable
fun InputScreenAnimatedSearchbar(
    queryText: String,
    isExpanded: Boolean,
    onExpandToggled: () -> Unit,
    onQueryTextChanged: (value: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 300)),
        contentAlignment = CenterStart
    ) {
            if (isExpanded) SearchBarExpanded(
                queryText = queryText,
                onExpandToggled = onExpandToggled,
                onQueryTextChanged = onQueryTextChanged,
            ) else SearchIconButton(
                onExpandToggled = onExpandToggled
            )
    }
}

@Composable
private fun SearchIconButton(onExpandToggled: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val pulseAnimation by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .size(80.dp)
            .scale(pulseAnimation)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .shadow(7.dp, CircleShape)
                .clip(CircleShape)
                .align(Alignment.Center)
                .background(White)
                .clickable { onExpandToggled() }
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(23.dp),
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun SearchBarExpanded(
    queryText: String,
    onExpandToggled: () -> Unit,
    onQueryTextChanged: (value: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .defaultMinSize(minHeight = 72.dp)
            .wrapContentHeight()
    ) {
        TextField(
            value = queryText,
            onValueChange = { onQueryTextChanged(it) },
            placeholder = {
                Text(text = stringResource(id = R.string.input_search_placeholder))
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Black,
                disabledTextColor = Transparent,
                backgroundColor = White,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                disabledIndicatorColor = Transparent,
                placeholderColor = LightGray,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(vertical = 8.dp)
                .shadow(7.dp, CircleShape)
                .defaultMinSize(minHeight = 56.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(30.dp)),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onExpandToggled()
                        if (queryText.isNotEmpty()) {
                            onQueryTextChanged("")
                        }
                    }
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            singleLine = true
        )
    }
}
