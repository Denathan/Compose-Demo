package com.rodak.composeshowcase.home

import androidx.annotation.StringRes
import com.rodak.composeshowcase.R
import com.rodak.composeshowcase.base.ViewState

data class HomeScreenViewState(
    val isLoading: Boolean = false,
    val features: List<Feature> = listOf(),
    val query: String = ""
) : ViewState

sealed class Feature(@StringRes val title: Int) {

    object Input : Feature(R.string.home_input_button)
    object Preview : Feature(R.string.home_preview_button)
    object LazyColumn : Feature(R.string.home_lazy_column_button)
    object Login : Feature(R.string.home_login_button)
}
