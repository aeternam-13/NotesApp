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
fun TestNav2(navController: NavController, cadena : String) {
    Scaffold(content = { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("TestNav2 $cadena ")
            Button(onClick = {
                navController.popBackStack()

            }) {
                Text("Volver")
            }
        }


    }
    )
}