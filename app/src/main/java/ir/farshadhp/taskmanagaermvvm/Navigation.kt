package ir.farshadhp.taskmanagaermvvm

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import ir.farshadhp.taskmanagaermvvm.ui.DetailScreen
import ir.farshadhp.taskmanagaermvvm.ui.HomeScreen
import ir.farshadhp.taskmanagaermvvm.util.AndroidAlarmScheduler

sealed class NavRoute(val route: String) {
    object Home : NavRoute("home_route")
    object Detail : NavRoute("detail_route")
}

@Composable
fun TaskNavHost(scheduler: AndroidAlarmScheduler) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
    ) {
        composable(NavRoute.Home.route) {
            HomeScreen {
                navController.navigate(NavRoute.Detail.route + "/${it?.id ?: -1}") {

                }
            }
        }
        composable(
            NavRoute.Detail.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType }),
        ) {
            DetailScreen(selectedId = it.arguments?.getLong("id") ?: -1, scheduler) {
                navController.navigateUp()
            }
        }

    }

}