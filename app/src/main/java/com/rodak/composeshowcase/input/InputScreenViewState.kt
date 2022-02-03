package com.rodak.composeshowcase.input

import com.rodak.composeshowcase.base.ViewState

data class InputScreenViewState(val isLoading: Boolean = false, val query: String= "") : ViewState
