package com.rodak.composeshowcase.login

import com.rodak.composeshowcase.base.ViewState

data class LoginScreenViewState(
    val email: Email = Email(),
    val password: Password = Password(),
    val isLoading: Boolean = false
) : ViewState

data class Email(val value: String = "", val isError: Boolean = false)

data class Password(
    val value: String = "",
    val isError: Boolean = false,
    val isVisible: Boolean = false
)
