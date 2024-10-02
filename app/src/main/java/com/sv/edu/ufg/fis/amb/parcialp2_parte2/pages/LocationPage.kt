package com.sv.edu.ufg.fis.amb.parcialp2_parte2.pages

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.cameraPosition
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.R
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonP
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonV
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarNavIconButtonV
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_FILES_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_MAIN_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_NOTIFY_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.ui.theme.ParcialP2_parte2Theme

@Composable
fun LocationPage(
    navController: NavHostController
){
    val context = LocalContext.current

    Scaffold(
        topBar = { LocationTopAppBar(navController) },
        bottomBar = { LocationBottomAppBar(context = context, navController = navController) }
    ){
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .fillMaxSize()
                .background(color = colorResource(R.color.background)),
            verticalArrangement = Arrangement.Center
        ) {
            BodyLocation(context = context, navController)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun LocationPagePreview(){

    ParcialP2_parte2Theme(
        dynamicColor = false
    ) {
        LocationPage(rememberNavController())
    }

}

//---------------------------------------------------------------- [TOP APP BAR]

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationTopAppBar(
    navController: NavHostController
){
    TopAppBar(
        title = {
            Text(
                "Localizacion",
                fontSize = 30.sp,
                fontWeight = FontWeight.W500
            )
        },
        navigationIcon = {
            AppBarNavIconButtonV(
                iconModifier = Modifier.fillMaxSize(0.75f),
                onClick = { navController.navigate(ROOT_MAIN_PAGE) },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Exit to App"
            )
        }
    )
}

//---------------------------------------------------------------- [BOTTOM APP BAR]

@Composable
fun LocationBottomAppBar(
    context : Context,
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
                    onClick = {
                        Toast
                            .makeText(context, "Opcion ya seleccionada", Toast.LENGTH_LONG)
                            .show()
                    },
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location",
                    text = "Ubicacion"
                )
                AppBarIconButtonP(
                    iconModifier = Modifier
                        .fillMaxSize(),
                    onClick = { navController.navigate(ROOT_FILES_PAGE) },
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

//---------------------------------------------------------------- [BODY LOCATION]

@Composable
fun BodyLocation(
    context: Context,
    navController: NavHostController
){

    var ubicacionPermiso by remember { mutableStateOf(false) }

    val ubicacionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
            permisos ->
        ubicacionPermiso = permisos[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permisos[Manifest.permission.ACCESS_COARSE_LOCATION] == true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults
            .cardColors(containerColor = colorResource(R.color.background)),
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if(ubicacionPermiso){
                MapLocation(context)
            }else{
                Image(
                    modifier = Modifier
                        .height(262.5.dp)
                        .width(262.5.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    painter = painterResource(R.drawable.location),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp),
                    text = "No se tienen permisos para acceder a la ubicacion",
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(7.5.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.70f),
                    onClick = {
                        ubicacionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                            )
                        )
                    }
                ) {
                    Text(
                        "Acceder a la ubicacion",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun MapLocation(
    context: Context
){
    val ubicacionClient = LocationServices.getFusedLocationProviderClient(context)

    var defaultLat = LatLng(13.794185, -88.89653)

    val posicionCamara = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(defaultLat, 12f)
    }

    if(ContextCompat
        .checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ){
        try{
            ubicacionClient.lastLocation.addOnSuccessListener {
                ubicacion ->
                if(ubicacion != null){
                    defaultLat = LatLng(ubicacion.latitude, ubicacion.longitude)
                    posicionCamara.position = CameraPosition.fromLatLngZoom(
                        LatLng(ubicacion.latitude, ubicacion.longitude), 15f
                    )
                    Toast
                        .makeText(
                            context,
                            "Ubicacion actual en el mapa",
                            Toast.LENGTH_LONG)
                        .show()
                }else{
                    Toast
                        .makeText(
                            context,
                            "No se pudo acceder a la ubicacion",
                            Toast.LENGTH_LONG)
                        .show()
                }
            }
        }catch (ex : Exception){
            Toast
                .makeText(context, "Error al acceder a la ubicacion", Toast.LENGTH_LONG)
                .show()
        }

    }else{
        Toast
            .makeText(context, "Ha ocurrido un error con los permisos", Toast.LENGTH_LONG)
            .show()
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.70f),
        cameraPositionState = posicionCamara
    ){
        Marker(
            state = MarkerState(position = defaultLat),
            title = "Ubicacion Actual",
            snippet = "Ubicacion tomada desde tu dispositivo",
        )
    }
}