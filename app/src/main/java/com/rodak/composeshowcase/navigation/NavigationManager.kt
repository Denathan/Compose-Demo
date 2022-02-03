package com.rodak.composeshowcase.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {

    private var mutableNavigationCommands = MutableSharedFlow<NavigationCommand>()
    val navigationCommands: Flow<NavigationCommand> = mutableNavigationCommands

    suspend fun navigate(command: NavigationCommand) {
        mutableNavigationCommands.emit(command)
    }

    companion object {
        const val LISTEN_FOR_NAVIGATION = "listen_for_navigation"
    }
}
