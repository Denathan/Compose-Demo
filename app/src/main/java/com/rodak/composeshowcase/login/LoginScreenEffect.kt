package com.rodak.composeshowcase.login

import com.rodak.composeshowcase.base.ViewEffect

sealed interface LoginScreenEffect : ViewEffect {
    object HideKeyboard : LoginScreenEffect
}
