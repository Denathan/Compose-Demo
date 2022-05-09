package com.rodak.composeshowcase.login

import com.rodak.composeshowcase.base.ViewEvent

sealed interface LoginScreenEvent : ViewEvent {
    data class EmailChanged(val email: String) : LoginScreenEvent
    data class PasswordChanged(val password: String) : LoginScreenEvent
    data class PasswordVisibilityChanged(val visible: Boolean) : LoginScreenEvent

    object ContinueButtonClicked : LoginScreenEvent
}
