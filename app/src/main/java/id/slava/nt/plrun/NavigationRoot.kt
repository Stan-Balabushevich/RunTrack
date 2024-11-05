package id.slava.nt.plrun

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import id.slava.nt.auth.presentation.intro.IntroScreenRoot
import id.slava.nt.auth.presentation.register.RegisterScreenRoot


@Composable
fun NavigationRoot(
    navController: NavHostController,
//    isLoggedIn: Boolean,
//    onAnalyticsClick: () -> Unit
) {
    NavHost(
        navController = navController,
//        startDestination = if (isLoggedIn) "run" else "auth"
        startDestination = "auth"

    ) {
        authGraph(navController)
//        runGraph(navController, onAnalyticsClick)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = "intro",
        route = "auth"
    ) {
        composable(route = "intro") {
            IntroScreenRoot(
                onSignUpClick = {
                    navController.navigate("register")
                },
                onSignInClick = {
                    navController.navigate("login")
                }
            )
        }
        composable(route = "register") {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate("login")
//                    {
//                        popUpTo("register") {
//                            inclusive = true
//                            saveState = true
//                        }
//                        restoreState = true
//                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate("login")
                }
            )
        }
        composable("login") {

            Text(text = "Login")
//            LoginScreenRoot(
//                onLoginSuccess = {
//                    navController.navigate("run") {
//                        popUpTo("auth") {
//                            inclusive = true
//                        }
//                    }
//                },
//                onSignUpClick = {
//                    navController.navigate("register") {
//                        popUpTo("login") {
//                            inclusive = true
//                            saveState = true
//                        }
//                        restoreState = true
//                    }
//                }
//            )
        }
    }
}

//private fun NavGraphBuilder.runGraph(
//    navController: NavHostController,
//    onAnalyticsClick: () -> Unit
//) {
//    navigation(
//        startDestination = "run_overview",
//        route = "run"
//    ) {
//        composable("run_overview") {
//            RunOverviewScreenRoot(
//                onStartRunClick = {
//                    navController.navigate("active_run")
//                },
//                onAnalyticsClick = onAnalyticsClick,
//                onLogoutClick = {
//                    navController.navigate("auth") {
//                        popUpTo("run") {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
//        composable(
//            route = "active_run",
//            deepLinks = listOf(
//                navDeepLink {
//                    uriPattern = "runique://active_run"
//                }
//            )
//        ) {
//            val context = LocalContext.current
//            ActiveRunScreenRoot(
//                onBack = {
//                    navController.navigateUp()
//                },
//                onFinish = {
//                    navController.navigateUp()
//                },
//                onServiceToggle = { shouldServiceRun ->
//                    if (shouldServiceRun) {
//                        context.startService(
//                            ActiveRunService.createStartIntent(
//                                context = context,
//                                activityClass = MainActivity::class.java
//                            )
//                        )
//                    } else {
//                        context.startService(
//                            ActiveRunService.createStopIntent(context = context)
//                        )
//                    }
//                }
//            )
//        }
//    }
//}