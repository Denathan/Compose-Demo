package com.rodak.composeshowcase.preview

import com.rodak.composeshowcase.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreviewScreenViewModel @Inject constructor() :
    BaseViewModel<PreviewScreenEvent, PreviewScreenViewState, PreviewScreenEffect>() {

    override fun setInitialState(): PreviewScreenViewState = PreviewScreenViewState()

    override fun handleEvents(event: PreviewScreenEvent) {
    }
}
