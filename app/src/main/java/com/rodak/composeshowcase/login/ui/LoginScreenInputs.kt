@file:OptIn(ExperimentalComposeUiApi::class)

package com.rodak.composeshowcase.login.ui

import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection.Companion.Down
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType.Companion.Email
import androidx.compose.ui.text.input.KeyboardType.Companion.Password
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation.Companion.None
import androidx.compose.ui.unit.dp
import com.rodak.composeshowcase.R
import com.rodak.composeshowcase.login.Email
import com.rodak.composeshowcase.login.LoginScreenEvent
import com.rodak.composeshowcase.login.LoginScreenEvent.EmailChanged
import com.rodak.composeshowcase.login.LoginScreenEvent.PasswordChanged
import com.rodak.composeshowcase.login.LoginScreenEvent.PasswordVisibilityChanged
import com.rodak.composeshowcase.login.LoginScreenViewState
import com.rodak.composeshowcase.login.Password
import com.rodak.composeshowcase.views.PrimaryInput


@Composable
fun ColumnScope.LoginScreenInputs(
    viewState: LoginScreenViewState,
    onEventSent: (event: LoginScreenEvent) -> Unit,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?,
) {
    Column(Modifier.weight(1f), verticalArrangement = Bottom) {
        EmailInput(viewState.email, onEventSent, focusManager)
        PasswordInput(viewState.password, onEventSent, keyboardController, focusManager)
    }
}

@Composable
private fun EmailInput(
    email: Email,
    onEventSent: (event: LoginScreenEvent) -> Unit,
    focusManager: FocusManager
) {
    PrimaryInput(
        query = email.value,
        modifier = Modifier.padding(horizontal = 16.dp),
        placeholderRes = R.string.login_email_input,
        keyboardOptions = KeyboardOptions(keyboardType = Email, imeAction = Next),
        borderColor = if (email.isError) Red else LightGray,
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(Down) })
    ) { onEventSent(EmailChanged(it)) }
}

@Composable
private fun PasswordInput(
    password: Password,
    onEventSent: (event: LoginScreenEvent) -> Unit,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
) {
    PrimaryInput(
        query = password.value,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 40.dp),
        placeholderRes = R.string.login_password_input,
        visualTransformation = if (password.isVisible) None else PasswordVisualTransformation(),
        icon = {
            val iconRes =
                if (password.isVisible) R.drawable.ic_eye_close else R.drawable.ic_eye_open
            IconButton(onClick = { onEventSent(PasswordVisibilityChanged(!password.isVisible)) }) {
                Icon(
                    painterResource(id = iconRes),
                    contentDescription = null
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = Password, imeAction = Done),
        borderColor = if (password.isError) Red else LightGray,
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            focusManager.clearFocus()
        })
    ) { onEventSent(PasswordChanged(it)) }
}
