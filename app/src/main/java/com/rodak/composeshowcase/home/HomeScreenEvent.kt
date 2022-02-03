package com.rodak.composeshowcase.home

import com.rodak.composeshowcase.base.ViewEvent

sealed interface HomeScreenEvent : ViewEvent {

    data class FeatureButtonClicked(val feature: Feature) : HomeScreenEvent
    data class OnQueryChanged(val value: String) : HomeScreenEvent
}
