package com.rodak.composeshowcase.home

import androidx.lifecycle.viewModelScope
import com.rodak.composeshowcase.base.BaseViewModel
import com.rodak.composeshowcase.home.Feature.Input
import com.rodak.composeshowcase.home.Feature.LazyColumn
import com.rodak.composeshowcase.home.Feature.Login
import com.rodak.composeshowcase.home.Feature.Preview
import com.rodak.composeshowcase.navigation.NavigationDirections
import com.rodak.composeshowcase.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val navigationManager: NavigationManager) :
    BaseViewModel<HomeScreenEvent, HomeScreenViewState, HomeScreenEffect>() {

    init {
        fetchFeatures()
    }

    override fun setInitialState(): HomeScreenViewState = HomeScreenViewState(isLoading = true)

    override fun handleEvents(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.FeatureButtonClicked -> navigateToFeature(event.feature)
            is HomeScreenEvent.OnQueryChanged -> setState { copy(query = event.value) }
        }
    }

    private fun fetchFeatures() = viewModelScope.launch {
        delay(300)
        val result = listOf(Input, Preview, LazyColumn, Login)
        setState { copy(features = result, isLoading = false) }
    }

    private fun navigateToFeature(feature: Feature) = viewModelScope.launch {
        when (feature) {
            is Input -> navigationManager.navigate(
                NavigationDirections.Input.buildDestination(viewState.value.query.takeIf { it.isNotEmpty() })
            )
            is Preview -> navigationManager.navigate(NavigationDirections.Preview)
            is LazyColumn -> navigationManager.navigate(NavigationDirections.LazyColumn)
            is Login -> navigationManager.navigate(NavigationDirections.Login)
        }
    }
}
