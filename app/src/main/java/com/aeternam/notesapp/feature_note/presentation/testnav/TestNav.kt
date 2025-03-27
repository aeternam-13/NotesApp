package com.aeternam.notesapp.feature_note.presentation.testnav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aeternam.notesapp.feature_note.presentation.util.Screens

@Composable
fun TestNav(navController: NavController) {
    Scaffold(content = { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Hola Sabrina")
            Button(onClick = {
                val cadena = "Te_quiero_mucho_Sabrina"
                navController.navigate(Screens.TestNav2.route + "?cadena=${cadena}")

            }) {
                Text("Ir a TestNav2")
            }

            Button(onClick = {
                val cadena = "dsadsaasdasd"
                navController.navigate(Screens.TestNav2.route + "?cadena=${cadena}")

            }) {
                Text("Ir a 2")
            }

            Button(onClick = {
                navController.navigate(Screens.TestNav2.route)

            }) {
                Text("Ir a 3")
            }
        }


    }
    )
}