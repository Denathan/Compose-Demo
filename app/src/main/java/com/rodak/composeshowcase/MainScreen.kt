package com.rodak.composeshowcase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodak.composeshowcase.home.HomeScreen
import com.rodak.composeshowcase.home.HomeScreenViewModel
import com.rodak.composeshowcase.input.ui.InputScreen
import com.rodak.composeshowcase.input.InputScreenViewModel
import com.rodak.composeshowcase.lazycolumn.LazyColumnScreen
import com.rodak.composeshowcase.lazycolumn.LazyColumnScreenViewModel
import com.rodak.composeshowcase.login.ui.LoginScreen
import com.rodak.composeshowcase.login.LoginScreenViewModel
import com.rodak.composeshowcase.navigation.NavigationDirections
import com.rodak.composeshowcase.navigation.NavigationManager
import com.rodak.composeshowcase.navigation.NavigationManager.Companion.LISTEN_FOR_NAVIGATION
import com.rodak.composeshowcase.preview.PreviewScreen
import com.rodak.composeshowcase.preview.PreviewScreenViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun MainScreen(navigationManager: NavigationManager) {
    val navController = rememberNavController()

    HandleEffects(navigationManager, navController)

    NavHost(
        navController = navController,
        startDestination = NavigationDirections.Home.destination
    ) {
        composable(route = NavigationDirections.Home.destination) {
            HomeScreenDestination()
        }
        composable(
            route = NavigationDirections.Input.destination,
            arguments = NavigationDirections.Input.arguments
        ) {
            InputScreenDestination()
        }
        composable(route = NavigationDirections.Preview.destination) {
            PreviewScreenDestination()
        }
        composable(route = NavigationDirections.LazyColumn.destination) {
            LazyColumnScreenDestination()
        }
        composable(route = NavigationDirections.Login.destination) {
            LoginScreenDestination()
        }
    }
}

@Composable
private fun HandleEffects(navigationManager: NavigationManager, navController: NavController) {
    LaunchedEffect(LISTEN_FOR_NAVIGATION) {
        navigationManager.navigationCommands.onEach { command ->
            navController.navigate(command.destination)
        }.launchIn(this)
    }
}

@Composable
private fun HomeScreenDestination() {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val viewState = viewModel.viewState.value

    HomeScreen(viewState = viewState) { event -> viewModel.setEvent(event) }
}

@Composable
private fun InputScreenDestination() {
    val viewModel: InputScreenViewModel = hiltViewModel()
    val viewState = viewModel.viewState.value

    InputScreen(viewState = viewState) { event -> viewModel.setEvent(event) }
}

@Composable
private fun PreviewScreenDestination() {
    val viewModel: PreviewScreenViewModel = hiltViewModel()
    val viewState = viewModel.viewState.value

    PreviewScreen(viewState = viewState) { event -> viewModel.setEvent(event) }
}

@Composable
private fun LazyColumnScreenDestination() {
    val viewModel: LazyColumnScreenViewModel = hiltViewModel()
    val viewState = viewModel.viewState.value

    LazyColumnScreen(viewState = viewState) { event -> viewModel.setEvent(event) }
}

@Composable
private fun LoginScreenDestination() {
    val viewModel: LoginScreenViewModel = hiltViewModel()
    val viewState = viewModel.viewState.value
    LoginScreen(viewState = viewState, effectFlow = viewModel.effect) { event -> viewModel.setEvent(event) }
}
