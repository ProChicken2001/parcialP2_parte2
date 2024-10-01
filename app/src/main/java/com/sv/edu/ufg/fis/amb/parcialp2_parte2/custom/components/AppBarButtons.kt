package com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//---------------------------------------------------------------- [ICON BUTTON]

@Composable
fun AppBarNavIconButtonV(
    buttonModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick : () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
){
    IconButton(
        modifier = buttonModifier,
        onClick = onClick
    ) {
        Icon(
            modifier = iconModifier,
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun AppBarIconButtonV(
    buttonModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick : () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
    text: String = "opcion"
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            modifier = buttonModifier,
            onClick = onClick
        ) {
            Icon(
                modifier = iconModifier,
                imageVector = imageVector,
                contentDescription = contentDescription
            )
        }
        Text(
            text = text,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Composable
fun AppBarIconButtonP(
    buttonModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick : () -> Unit,
    painter: Painter,
    contentDescription: String,
    text: String = "opcion"
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            modifier = buttonModifier,
            onClick = onClick
        ) {
            Icon(
                modifier = iconModifier,
                painter = painter,
                contentDescription = contentDescription,
            )
        }
        Text(
            text = text,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.W600
        )
    }
}