package com.plcoding.stockmarketapp.presentation


import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.plcoding.stockmarketapp.presentation.movie_listings.CompanyListingsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun MainScreen(
    navigator: DestinationsNavigator
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = {
                        navigator.navigate("CompanyListingsScreenDestination")
                    },
                    icon = { Icon(Icons.Default.List, contentDescription = "Company Listings") },
                    label = { Text("Company Listings") }
                )
            }
        }
    ) {
        CompanyListingsScreen(navigator)
    }
}
