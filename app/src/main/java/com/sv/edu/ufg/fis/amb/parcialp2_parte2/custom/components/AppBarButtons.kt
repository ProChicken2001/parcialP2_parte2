package com.sv.edu.ufg.fis.amb.parcialp2_parte2.custom.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

//---------------------------------------------------------------- [ICON BUTTON]

@Composable
fun AppBarIconButtonV(
    buttonModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick : () -> Unit,
    imageVector: ImageVector,
    contentDescription: String
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
fun AppBarIconButtonP(
    buttonModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick : () -> Unit,
    painter: Painter,
    contentDescription: String
){
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
}