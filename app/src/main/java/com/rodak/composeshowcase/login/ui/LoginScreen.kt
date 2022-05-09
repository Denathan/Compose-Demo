@file:OptIn(ExperimentalComposeUiApi::class)

package com.rodak.composeshowcase.login.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusDirection.Companion.Next
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodak.composeshowcase.R
import com.rodak.composeshowcase.base.BaseViewModel.Companion.LAUNCH_LISTEN_FOR_EFFECTS
import com.rodak.composeshowcase.login.LoginScreenEffect
import com.rodak.composeshowcase.login.LoginScreenEffect.HideKeyboard
import com.rodak.composeshowcase.login.LoginScreenEvent
import com.rodak.composeshowcase.login.LoginScreenEvent.ContinueButtonClicked
import com.rodak.composeshowcase.login.LoginScreenViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreen(
    viewState: LoginScreenViewState,
    effectFlow: Flow<LoginScreenEffect>?,
    onEventSent: (event: LoginScreenEvent) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    HandleEffects(effectFlow, keyboardController, focusManager)

    Background {
        Title()
        if (viewState.isLoading) {
            Loader()
        } else {
            LoginScreenInputs(viewState, onEventSent, focusManager, keyboardController)
            ContinueButton(onEventSent)
        }
    }
}

@Composable
private fun HandleEffects(
    effectFlow: Flow<LoginScreenEffect>?,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is HideKeyboard -> {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            }
        }
    }
}

@Composable
private fun ContinueButton(onEventSent: (event: LoginScreenEvent) -> Unit) {
    OutlinedButton(
        onClick = { onEventSent(ContinueButtonClicked) },
        border = BorderStroke(1.dp, LightGray),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.login_continue_button),
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

@Composable
private fun Loader() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
}

@Composable
private fun Title() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.paddingMedium)),
        contentAlignment = Center
    ) {
        Text(
            text = stringResource(id = R.string.login_title),
            fontWeight = Bold,
            fontSize = 24.sp,
        )
    }
}

@Composable
private fun Background(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        content()
    }
}
