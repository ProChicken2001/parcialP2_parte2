package com.sv.edu.ufg.fis.amb.parcialp2_parte2.pages

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.R
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonP
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonV
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarNavIconButtonV
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_FILES_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_LOCATION_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_MAIN_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_NOTIFY_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.ui.theme.ParcialP2_parte2Theme

@Composable
fun NotifyPage(
    navController: NavHostController
){
    val context = LocalContext.current

    Scaffold(
        topBar = { NotifyTopAppBar(navController) },
        bottomBar = { NotifyBottomAppBar(context = context, navController = navController) }
    ) {
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
            BodyNotify(context)
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
fun NotifyTopAppBar(
    navController: NavHostController
){
    TopAppBar(
        title = {
            Text(
                "Notificaciones",
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
fun NotifyBottomAppBar(
    context: Context,
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
                    onClick = { navController.navigate(ROOT_FILES_PAGE) },
                    painter = painterResource(R.drawable.baseline_upload_file_24),
                    contentDescription = "Upload",
                    text = "Subir"
                )
                AppBarIconButtonP(
                    iconModifier = Modifier
                        .fillMaxSize(),
                    onClick = {
                        Toast
                            .makeText(context, "Opcion ya seleccionada", Toast.LENGTH_LONG)
                            .show()
                    },
                    painter = painterResource(R.drawable.baseline_notifications_none_24),
                    contentDescription = "Notifications",
                    text = "Notificacion"
                )
            }
        }
    )
}

//---------------------------------------------------------------- [BODY]

@Composable
fun BodyNotify(
    context: Context
){
    var notiPermiso by remember { mutableStateOf(false) }

    val notiLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGratend: Boolean ->
        notiPermiso = isGratend
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
        ){
            if(notiPermiso){
                Image(
                    modifier = Modifier
                        .height(262.5.dp)
                        .width(262.5.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    painter = painterResource(R.drawable.notify),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp),
                    text = "Las notificaciones estan activadas",
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(7.5.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.70f),
                    onClick = {
                        EnviarNotificacion(context)
                    }
                ) {
                    Text(
                        "Enviar notificacion",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }else{
                Image(
                    modifier = Modifier
                        .height(262.5.dp)
                        .width(262.5.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    painter = painterResource(R.drawable.notify),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp),
                    text = "Las notificaciones estan desactivadas",
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(7.5.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.70f),
                    onClick = {
                        notiLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                ) {
                    Text(
                        "Activar notificaciones",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

//---------------------------------------------------------------- [NOTIFICACIONES]

fun EnviarNotificacion(
    context: Context
){
    val notificationId = 1
    val channelId = "important_alerts"
    val titulo = "Alertas importantes"
    val descripcion = "Notificaciones para alertas críticas"
    val nivel = NotificationManager.IMPORTANCE_HIGH
    val canal = NotificationChannel(channelId, titulo, nivel).apply {
        description = descripcion
    }
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(canal)

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_dialog_alert)
        .setContentTitle("App permiso alert")
        .setContentText("Esta es una alerta crítica.")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notify(notificationId, builder.build())
        }else{
            Toast
                .makeText(
                    context,
                    "No tiene permiso para enviar notificaciones",
                    Toast.LENGTH_LONG)
                .show()
        }
    }
}