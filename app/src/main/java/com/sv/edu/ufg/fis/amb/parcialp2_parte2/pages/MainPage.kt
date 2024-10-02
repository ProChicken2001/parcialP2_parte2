package com.sv.edu.ufg.fis.amb.parcialp2_parte2.pages

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.R
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonP
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonV
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarNavIconButtonV
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_FILES_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_LOCATION_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_NOTIFY_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.ui.theme.ParcialP2_parte2Theme

@Composable
fun MainPage(
    navController : NavHostController
){
    val context = LocalContext.current

    Scaffold(
        topBar = { MainTopAppBar(context) },
        bottomBar = { MainBottomAppBar(navController) }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                )
                .fillMaxSize()
                .background(color = colorResource(R.color.background)),
            verticalArrangement = Arrangement.Center
        ) {
            BodyMain()
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun MainPagePreview(){

    ParcialP2_parte2Theme(
        dynamicColor = false
    ) {
        MainPage(rememberNavController())
    }

}

//---------------------------------------------------------------- [TOP APP BAR]

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    context: Context
){
    TopAppBar(
        title = {
            Text(
                "Permisos App",
                fontSize = 30.sp,
                fontWeight = FontWeight.W500
            )
        },
        navigationIcon = {
            AppBarNavIconButtonV(
                iconModifier = Modifier.fillMaxSize(0.75f),
                onClick = {
                    (context as? Activity)?.finishAffinity()
                },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Exit to App"
            )
        }
    )
}

//---------------------------------------------------------------- [BOTTOM APP BAR]

@Composable
fun MainBottomAppBar(
    navController: NavHostController
){
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
                    onClick = { navController.navigate(ROOT_LOCATION_PAGE) },
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location",
                    text = "Ubicacion"
                )
                AppBarIconButtonP(
                    iconModifier = Modifier
                        .fillMaxSize(),
                    onClick = {navController.navigate(ROOT_FILES_PAGE) },
                    painter = painterResource(R.drawable.baseline_upload_file_24),
                    contentDescription = "Upload",
                    text = "Subir"
                )
                AppBarIconButtonP(
                    iconModifier = Modifier
                        .fillMaxSize(),
                    onClick = { navController.navigate(ROOT_NOTIFY_PAGE) },
                    painter = painterResource(R.drawable.baseline_notifications_none_24),
                    contentDescription = "Notifications",
                    text = "Notificacion"
                )
            }
        }
    )
}

//---------------------------------------------------------------- [BODY MAIN]

@Composable
fun BodyMain(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(start = 15.dp, end = 15.dp),
        colors = CardDefaults
            .cardColors(containerColor = colorResource(R.color.background)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .fillMaxHeight(0.75f)
                    .clip(RoundedCornerShape(25.dp)),
                painter = painterResource(R.drawable.home),
                contentDescription = "Image",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Sin una opcion seleccionada, por favor seleccionar una opcion",
                textAlign = TextAlign.Center,
            )
        }
    }
}