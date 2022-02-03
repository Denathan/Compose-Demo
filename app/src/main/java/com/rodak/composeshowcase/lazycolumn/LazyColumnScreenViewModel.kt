package com.rodak.composeshowcase.lazycolumn

import com.rodak.composeshowcase.R
import com.rodak.composeshowcase.base.BaseViewModel
import com.rodak.composeshowcase.views.CardLabelInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LazyColumnScreenViewModel @Inject constructor() :
    BaseViewModel<LazyColumnScreenEvent, LazyColumnScreenViewState, LazyColumnScreenEffect>() {

    private val cards = List(100) { index ->
        CardLabelInfo("Card", "Subtitle", R.drawable.ic_android_black_24dp, index.toString())
    }

    init {
        setState { copy(items = cards) }
    }

    override fun setInitialState(): LazyColumnScreenViewState = LazyColumnScreenViewState()

    override fun handleEvents(event: LazyColumnScreenEvent) {
    }
}
