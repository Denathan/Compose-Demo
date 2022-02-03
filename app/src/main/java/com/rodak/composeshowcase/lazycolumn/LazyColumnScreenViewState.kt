package com.rodak.composeshowcase.lazycolumn

import com.rodak.composeshowcase.base.ViewState
import com.rodak.composeshowcase.views.CardLabelInfo

data class LazyColumnScreenViewState(val items: List<CardLabelInfo> = listOf()) : ViewState
