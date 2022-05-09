package com.rodak.composeshowcase.input

import com.rodak.composeshowcase.base.ViewEvent

sealed interface InputScreenEvent : ViewEvent {
    data class OnQueryChange(val value: String) : InputScreenEvent
    object SearchBarExpandToggled : InputScreenEvent
}
