package com.example.authapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authapp.remote.AuthApi
import com.example.authapp.remote.RetrofitClient
import com.example.authapp.repository.AuthRepository
import com.example.authapp.ui.screen.HomeScreen
import com.example.authapp.ui.screen.LoginScreen
import com.example.authapp.ui.screen.RegisterScreen
import com.example.authapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val authApi = RetrofitClient.instance.create(AuthApi::class.java)
            val authRepository = AuthRepository(authApi, this)
            val authViewModel = AuthViewModel(authRepository)

            NavHost(
                navController = navController,
                startDestination = "login"
            ) {
                composable("login") {
                    LoginScreen(
                        viewModel = authViewModel,
                        onAuthenticated = {
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToRegister = {
                            navController.navigate("register")
                        }
                    )
                }

                composable("register") {
                    RegisterScreen(
                        viewModel = authViewModel,
                        onRegisterSuccess = {
                            navController.navigate("home") {
                                popUpTo("register") { inclusive = true }
                            }
                        },
                        onNavigateToLogin = {
                            navController.navigate("login")
                        }
                    )
                }

                composable("home") {
                    HomeScreen(
                        authViewModel,
                        onLogout = {navController.navigate("login")}
                    )
                }
            }
        }
    }
}
