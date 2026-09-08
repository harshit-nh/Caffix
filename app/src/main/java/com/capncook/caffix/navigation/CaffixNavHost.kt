package com.capncook.caffix.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.capncook.caffix.common.ui_components.splash_screen.CaffixSplashScreen
import com.capncook.caffix.feature.auth.presentation.login.components.LoginScreen
import com.capncook.caffix.feature.auth.presentation.otp_verification.components.OtpVerificationScreen
import com.capncook.caffix.feature.auth.presentation.registration.components.RegistrationScreen
import com.capncook.caffix.feature.main.presentation.MainScreen
import com.capncook.caffix.feature.user_onboarding.presentation.coffee_prefs.components.CoffeePrefsScreen
import com.capncook.caffix.feature.user_onboarding.presentation.final_screen.components.FinalOnboardingScreen
import com.capncook.caffix.feature.user_onboarding.presentation.location_perm.components.LocationPermScreen
import com.capncook.caffix.feature.user_onboarding.presentation.notification_prefs.components.NotificationPermsScreen
import com.capncook.caffix.feature.user_onboarding.presentation.profile.components.ProfileScreen
import com.capncook.caffix.screens.welcomescreen.WelcomeScreen


@Composable
fun CaffixNavHost(
    targetGraph: Any,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = SplashRoute,
        modifier = modifier
    ){


        composable<SplashRoute> {
            CaffixSplashScreen(
                onAnimationFinished = {
                    navController.navigate(targetGraph) {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }




        //AUTHENTICATION GRAPH

        navigation<AuthGraph>(startDestination = WelcomeRoute) {


            composable<WelcomeRoute>{
                WelcomeScreen(
                    onNavigateToRegister = {
                        navController.navigate(LoginRoute)
                    }
                )
            }


            composable<RegistrationRoute>{
                RegistrationScreen(
                    onNavigationToOtp = { phone ->
                        navController.navigate(OtpRoute(phoneNumber = phone, isFromLogin = false))
                    },
                    onNavigationToLogin = {
                        navController.navigate(LoginRoute) {
                            popUpTo(WelcomeRoute)
                        }
                    }
                )
            }


            composable<LoginRoute> {

                LoginScreen(
                    onNavigationToOtp = { phone ->
                        navController.navigate(OtpRoute(phoneNumber = phone, isFromLogin = true))
                    },
                    onNavigationToRegister = {
                        navController.navigate(RegistrationRoute) {
                            popUpTo(WelcomeRoute)
                        }
                    }
                )
            }



            composable<OtpRoute> {

                OtpVerificationScreen(
                    onNavigationToHome = {
                        navController.navigate(MainAppGraph) {
                            popUpTo(AuthGraph) { inclusive = true }
                        }
                    },
                    onNavigationToOnboarding = {
                        navController.navigate(OnboardingGraph) {
                            popUpTo(AuthGraph) { inclusive = true }
                        }
                    },
                    onNavigationToLogin = {
                        navController.navigate(LoginRoute) {
                            popUpTo(WelcomeRoute)
                        }
                    }
                )
            }


        }





        //ONBOARDING GRAPH

        navigation<OnboardingGraph>(startDestination = ProfileRoute) {

            composable<ProfileRoute> {
                ProfileScreen(

                    onNavigateNextFromUpload = {
                        navController.navigate(CoffeeTypeRoute) {
                            popUpTo(ProfileRoute) { inclusive = true }
                        }
                    },
                    onNavigateNextFromSkip = {
                        navController.navigate(CoffeeTypeRoute) {
                            popUpTo(ProfileRoute) { inclusive = true }
                        }
                    },
                    onForceLogout = {
                        navController.navigate(LoginRoute) {
                            popUpTo(OnboardingGraph) { inclusive = true }
                        }
                    }

                )
            }


            composable<CoffeeTypeRoute> {
                CoffeePrefsScreen(

                    onForceLogout = {
                        navController.navigate(LoginRoute) {
                            popUpTo(OnboardingGraph) { inclusive = true }
                        }
                    },
                    onNavigateNext = {
                        navController.navigate(NotificationPermRoute) {
                            popUpTo(CoffeeTypeRoute) { inclusive = true }
                        }
                    }
                )
            }


            composable<NotificationPermRoute> {
                NotificationPermsScreen(

                    onForceLogout = {
                        navController.navigate(LoginRoute) {
                            popUpTo(OnboardingGraph) { inclusive = true }
                        }
                    },
                    onNavigateNext = {
                        navController.navigate(LocationPermRoute) {
                            popUpTo(NotificationPermRoute) { inclusive = true }
                        }
                    }
                )
            }


            composable<LocationPermRoute> {
                LocationPermScreen(

                    onNavigationNext = {
                        navController.navigate(OnBoardingCompleteRoute) {
                            popUpTo(LocationPermRoute) { inclusive = true }
                        }
                    }
                )
            }



            composable<OnBoardingCompleteRoute> {
                FinalOnboardingScreen(

                    onForceLogout = {
                        navController.navigate(LoginRoute) {
                            popUpTo(OnboardingGraph) { inclusive = true }
                        }
                    },
                    onNavigationHome = {
                        navController.navigate(MainAppGraph) {
                            popUpTo(OnboardingGraph) { inclusive = true }
                        }
                    }
                )
            }


        }





        //HOME GRAPH
        navigation<MainAppGraph>(startDestination = MainHomeRoute) {

            composable<MainHomeRoute> {
                MainScreen(
                    rootNavController = navController
                )
            }
        }



    }
    
}