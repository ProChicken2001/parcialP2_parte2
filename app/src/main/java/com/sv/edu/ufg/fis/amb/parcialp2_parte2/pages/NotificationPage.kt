package com.sv.edu.ufg.fis.amb.parcialp2_parte2.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.R
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonP
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonV
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.ui.theme.ParcialP2_parte2Theme

@Composable
fun NotifyPage(
    navController: NavHostController
){
    Scaffold(
        topBar = { NotifyTopAppBar() },
        bottomBar = { NotifyBottomAppBar() }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {

        }
    }
}

@Preview(
    showBackground = false
)
@Composable
fun NotifyPagePreview(){

    ParcialP2_parte2Theme(
        dynamicColor = false
    ) {
        NotifyPage(rememberNavController())
    }

}

//---------------------------------------------------------------- [TOP APP BAR]

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotifyTopAppBar(){
    TopAppBar(
        title = {
            Text(
                "Notificaciones",
                fontSize = 30.sp,
                fontWeight = FontWeight.W500
            )
        },
        navigationIcon = {
            AppBarIconButtonV(
                iconModifier = Modifier.fillMaxSize(0.75f),
                onClick = {},
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Exit to App"
            )
        }
    )
}

//---------------------------------------------------------------- [BOTTOM APP BAR]

@Composable
fun NotifyBottomAppBar(){
    BottomAppBar(
        modifier = Modifier
            .background(colorResource(R.color.bottomBarBackground)),
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AppBarIconButtonV(
                    iconModifier = Modifier.fillMaxSize(),
                    onClick = {},
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location"
                )
                AppBarIconButtonP(
                    iconModifier = Modifier
                        .fillMaxSize(),
                    onClick = {},
                    painter = painterResource(R.drawable.baseline_upload_file_24),
                    contentDescription = "Upload"
                )
                AppBarIconButtonP(
                    iconModifier = Modifier
                        .fillMaxSize(),
                    onClick = {},
                    painter = painterResource(R.drawable.baseline_notifications_none_24),
                    contentDescription = "Notifications"
                )
            }
        }
    )
}