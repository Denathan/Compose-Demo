@file:OptIn(ExperimentalFoundationApi::class, InternalCoroutinesApi::class)

package com.rodak.composeshowcase.views

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction.Release
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardActions.Companion.Default
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.VisualTransformation.Companion.None
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun PrimaryInput(
    query: String,
    modifier: Modifier = Modifier,
    borderColor: Color = LightGray,
    @StringRes placeholderRes: Int? = null,
    icon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = Text),
    singleLine: Boolean = true,
    keyboardActions: KeyboardActions = Default,
    onQueryChanged: (String) -> Unit,
) {
    val source = remember { MutableInteractionSource() }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    SlideToInput(source, bringIntoViewRequester)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(White, RoundedCornerShape(10.dp))
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            placeholder = { placeholderRes?.let { Text(text = stringResource(id = it)) } },
            trailingIcon = icon,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Transparent,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                cursorColor = Black
            ),
            singleLine = singleLine,
            modifier = Modifier.fillMaxWidth().bringIntoViewRequester(bringIntoViewRequester),
            keyboardActions = keyboardActions,
            interactionSource = source
        )
    }
}

@Composable
private fun SlideToInput(
    source: MutableInteractionSource,
    bringIntoViewRequester: BringIntoViewRequester
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(source) {
        source.interactions.collect {
            if (it is Release) {
                scope.launch {
                    delay(300)
                    bringIntoViewRequester.bringIntoView()
                }
            }
        }
    }
}
