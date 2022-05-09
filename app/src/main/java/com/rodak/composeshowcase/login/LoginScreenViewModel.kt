package com.rodak.composeshowcase.login

import androidx.lifecycle.viewModelScope
import com.rodak.composeshowcase.base.BaseViewModel
import com.rodak.composeshowcase.login.LoginScreenEffect.HideKeyboard
import com.rodak.composeshowcase.login.LoginScreenEvent.ContinueButtonClicked
import com.rodak.composeshowcase.login.LoginScreenEvent.EmailChanged
import com.rodak.composeshowcase.login.LoginScreenEvent.PasswordChanged
import com.rodak.composeshowcase.login.LoginScreenEvent.PasswordVisibilityChanged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() :
    BaseViewModel<LoginScreenEvent, LoginScreenViewState, LoginScreenEffect>() {

    override fun setInitialState(): LoginScreenViewState = LoginScreenViewState()

    override fun handleEvents(event: LoginScreenEvent) {
        when (event) {
            is EmailChanged -> setState {
                copy(email = email.copy(value = event.email, isError = false))
            }
            is PasswordChanged -> setState {
                copy(password = password.copy(value = event.password, isError = false))
            }
            is PasswordVisibilityChanged -> setState {
                copy(password = password.copy(isVisible = event.visible))
            }
            is ContinueButtonClicked -> handleContinueClick()
        }
    }

    private fun handleContinueClick() = with(viewState.value) {
        setEffect { HideKeyboard }
        when {
            email.value.isEmpty() -> setState { copy(email = email.copy(isError = true)) }
            password.value.isEmpty() -> setState { copy(password = password.copy(isError = true)) }
            else -> viewModelScope.launch {
                setState { copy(isLoading = true) }
                delay(1000)
                setState { copy(isLoading = false) }
            }
        }
    }
}
