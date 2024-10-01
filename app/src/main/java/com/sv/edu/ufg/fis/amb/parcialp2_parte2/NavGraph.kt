package com.sv.edu.ufg.fis.amb.parcialp2_parte2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.pages.FilesPage
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.pages.LocationPage
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.pages.MainPage
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.pages.NotifyPage
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.Route
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.ui.theme.ParcialP2_parte2Theme


@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Route.MainRoutePage.route
    ){
        composable(
            route = Route.MainRoutePage.route
        ){
            ParcialP2_parte2Theme(
                dynamicColor = false
            ) {
                MainPage(navController)
            }

        }
        composable(
            route = Route.LocationRoutePage.route
        ){
            ParcialP2_parte2Theme(
                dynamicColor = false
            ) {
                LocationPage(navController)
            }

        }
        composable(
            route = Route.FilesRoutePage.route
        ){
            ParcialP2_parte2Theme(
                dynamicColor = false
            ) {
                FilesPage(navController)
            }
        }
        composable(
            route = Route.NotifyRoutePage.route
        ){
            ParcialP2_parte2Theme(
                dynamicColor = false
            ) {
                NotifyPage(navController)
            }
        }

    }
}