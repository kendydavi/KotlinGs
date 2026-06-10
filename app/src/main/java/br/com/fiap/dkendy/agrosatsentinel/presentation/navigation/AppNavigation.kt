package br.com.fiap.dkendy.agrosatsentinel.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.dkendy.agrosatsentinel.presentation.alerts.AlertsScreen
import br.com.fiap.dkendy.agrosatsentinel.presentation.field.FieldDetailScreen
import br.com.fiap.dkendy.agrosatsentinel.presentation.field.FieldListScreen
import br.com.fiap.dkendy.agrosatsentinel.presentation.home.HomeScreen
import br.com.fiap.dkendy.agrosatsentinel.presentation.monitoring.MonitoringScreen
import br.com.fiap.dkendy.agrosatsentinel.presentation.onboarding.OnboardingScreen
import br.com.fiap.dkendy.agrosatsentinel.presentation.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.SPLASH
    ) {
        composable(AppRoutes.SPLASH) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(AppRoutes.ONBOARDING) {
                        popUpTo(AppRoutes.SPLASH) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(AppRoutes.HOME) {
                        popUpTo(AppRoutes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoutes.ONBOARDING) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(AppRoutes.HOME) {
                        popUpTo(AppRoutes.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoutes.HOME) {
            HomeScreen(
                onFieldListClick = { navController.navigate(AppRoutes.FIELD_LIST) },
                onMonitoringClick = { navController.navigate(AppRoutes.MONITORING) },
                onAlertsClick = { navController.navigate(AppRoutes.ALERTS) }
            )
        }

        composable(AppRoutes.FIELD_LIST) {
            FieldListScreen(
                onBackClick = { navController.navigateUp() },
                onFieldClick = { fieldId ->
                    navController.navigate(AppRoutes.fieldDetail(fieldId))
                }
            )
        }

        composable(
            route = AppRoutes.FIELD_DETAIL,
            arguments = listOf(navArgument("fieldId") { type = NavType.IntType })
        ) { backStackEntry ->
            val fieldId = backStackEntry.arguments?.getInt("fieldId") ?: 0
            FieldDetailScreen(
                fieldId = fieldId,
                onBackClick = { navController.navigateUp() }
            )
        }

        composable(AppRoutes.MONITORING) {
            MonitoringScreen(
                onBackClick = { navController.navigateUp() }
            )
        }

        composable(AppRoutes.ALERTS) {
            AlertsScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}
