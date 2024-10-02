package com.sv.edu.ufg.fis.amb.parcialp2_parte2.pages

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.layout.size
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.R
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonP
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarIconButtonV
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components.AppBarNavIconButtonV
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_LOCATION_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_MAIN_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.routes.ROOT_NOTIFY_PAGE
import com.sv.edu.ufg.fis.amb.parcialp2_parte2.ui.theme.ParcialP2_parte2Theme

@Composable
fun FilesPage(
    navController: NavHostController
){
    val context = LocalContext.current

    Scaffold(
        topBar = { FilesTopAppBar(navController) },
        bottomBar = { FilesBottomAppBar(context = context, navController = navController) }
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
            BodyFiles(context)
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
fun FilesPagePreview(){

    ParcialP2_parte2Theme(
        dynamicColor = false
    ) {
        FilesPage(rememberNavController())
    }

}

//---------------------------------------------------------------- [TOP APP BAR]

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilesTopAppBar(
    navController: NavHostController
){
    TopAppBar(
        title = {
            Text(
                "Subir Archivos",
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
fun FilesBottomAppBar(
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
                    onClick = { navController.navigate(ROOT_LOCATION_PAGE) },
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location",
                    text = "Ubicacion"
                )
                AppBarIconButtonP(
                    iconModifier = Modifier
                        .fillMaxSize(),
                    onClick = {
                        Toast
                            .makeText(context, "Opcion ya seleccionada", Toast.LENGTH_LONG)
                            .show()
                    },
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

//---------------------------------------------------------------- [BODY]

@Composable
fun BodyFiles(
    context: Context
){
    var mediaUri by remember { mutableStateOf<Uri?>(null) }
    var grantend by remember { mutableStateOf(false) }

    val launcherIntent = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result ->
        if(result.resultCode == Activity.RESULT_OK){
            result.data?.data?.let { uri ->
                mediaUri = uri
                Toast
                    .makeText(context, "Archivo cargado con exito", Toast.LENGTH_LONG)
                    .show()
            }
        }else{
            Toast
                .makeText(context, "No se pudo cargar el archivo", Toast.LENGTH_LONG)
                .show()
        }

    }

    val mediaLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        permisos ->
        grantend = permisos[Manifest.permission.READ_MEDIA_IMAGES] == true ||
                permisos[Manifest.permission.READ_MEDIA_VIDEO] == true

        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
        }
        launcherIntent.launch(intent)
    }


    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults
            .cardColors(containerColor = colorResource(R.color.background)),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.70f),
                onClick = {
                    mediaLauncher.launch(
                        arrayOf(
                            Manifest.permission.READ_MEDIA_IMAGES,
                            Manifest.permission.READ_MEDIA_VIDEO
                        )
                    )
                }
            ) {
                Text(
                    "Seleccionar imagen o video",
                    fontSize = 13.5.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            mediaUri?.let {

                val contentResolver = context.contentResolver
                val type = contentResolver.getType(it)

                if(type?.startsWith("image/") == true){
                    Image(
                        modifier = Modifier
                            .height(262.5.dp)
                            .width(262.5.dp),
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = "Image Selected"
                    )
                }else if(type?.startsWith("video/") == true){
                    Text(text = "Video seleccionado: ${it.lastPathSegment}")
                }

            }

//            if(grantend || mediaUri != null){
//
//            }else{
//                Image(
//                    modifier = Modifier
//                        .height(262.5.dp)
//                        .width(262.5.dp)
//                        .clip(RoundedCornerShape(25.dp)),
//                    painter = painterResource(R.drawable.upload),
//                    contentDescription = "Image",
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.height(15.dp))
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 15.dp, end = 15.dp),
//                    text = "No se tienen permisos para acceder a los archivos multimedia",
//                    textAlign = TextAlign.Center,
//                )
//                Spacer(modifier = Modifier.height(7.5.dp))
//                Button(
//                    modifier = Modifier
//                        .fillMaxWidth(0.70f),
//                    onClick = {
//                        mediaLauncher.launch(
//                            arrayOf(
//                                Manifest.permission.READ_MEDIA_IMAGES,
//                                Manifest.permission.READ_MEDIA_VIDEO
//                            )
//                        )
//                    }
//                ) {
//                    Text(
//                        "Acceder a los archivos multimedia",
//                        fontSize = 13.5.sp,
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
        }
    }
}
