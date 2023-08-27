package com.plcoding.stockmarketapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val items = listOf(
                    Screen.Home,
                    Screen.Search
                )

                val currentRoute = currentRoute(navController)

                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Search.route) {
                SearchScreen()
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

sealed class Screen(val route: String, val icon: ImageVector) {
    object Home : Screen("home", Icons.Default.Home)
    object Search : Screen("search", Icons.Default.Search)
}

@Composable
fun HomeScreen() {
    ScreenContent("Home Screen", Color.Green)
}

@Composable
fun SearchScreen() {
    ScreenContent("Search Screen", Color.Blue)
}

@Composable
fun ScreenContent(text: String, color: Color) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}
