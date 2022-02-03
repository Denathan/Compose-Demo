package com.rodak.composeshowcase.input

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.rodak.composeshowcase.base.BaseViewModel
import com.rodak.composeshowcase.input.InputScreenEvent.OnQueryChange
import com.rodak.composeshowcase.navigation.NavigationDirections.Input.KEY_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputScreenViewModel @Inject constructor(stateHandle: SavedStateHandle) :
    BaseViewModel<InputScreenEvent, InputScreenViewState, InputScreenEffect>() {

    private val queryArg = stateHandle.get<String>(KEY_QUERY)

    override fun setInitialState(): InputScreenViewState = InputScreenViewState(isLoading = true)

    init {
        viewModelScope.launch {
            delay(1000)
            setState { copy(isLoading = false, query = queryArg.takeIf { it != "null" }.orEmpty()) }
        }
    }

    override fun handleEvents(event: InputScreenEvent) {
        when (event) {
            is OnQueryChange -> setState { copy(query = event.value, isLoading = false) }
        }
    }
}
