package com.rodak.composeshowcase.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object NavigationDirections {

    object Home : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val destination: String = "home"
    }

    object Input : NavigationCommand  {
        const val KEY_QUERY = "query"

        override val destination: String = "input/{$KEY_QUERY}"
        override val arguments: List<NamedNavArgument> =
            listOf(navArgument(KEY_QUERY) { type = NavType.StringType })

        fun buildDestination(query: String?) = object : NavigationCommand {
            override val arguments: List<NamedNavArgument> = Input.arguments

            override val destination: String = "input/$query"
        }
    }

    object Preview : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val destination: String = "preview"
    }

    object LazyColumn : NavigationCommand {

        override val arguments: List<NamedNavArgument> = emptyList()

        override val destination: String = "lazyColumn"
    }
}
