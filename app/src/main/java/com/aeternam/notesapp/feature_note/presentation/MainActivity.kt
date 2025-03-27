package com.aeternam.notesapp.feature_note.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aeternam.notesapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.aeternam.notesapp.feature_note.presentation.notes.NotesScreen
import com.aeternam.notesapp.feature_note.presentation.testnav.TestNav
import com.aeternam.notesapp.feature_note.presentation.testnav.TestNav2
import com.aeternam.notesapp.feature_note.presentation.util.Screens
import com.aeternam.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screens.NotesScreen.route,

                    ) {
                    composable(route = Screens.NotesScreen.route) {
                        NotesScreen(navController = navController)
                    }

                    composable(
                        route = Screens.TestNav2.route + "?cadena={cadena}",
                        arguments = listOf(
                            navArgument(name = "cadena") {
                                type = NavType.StringType
                                defaultValue = ""
                            })
                    ) {
                        val cadena = it.arguments?.getString("cadena") ?: "default"
                        TestNav2(navController = navController ,cadena = cadena )
                    }

                    composable(route = Screens.TestNav.route) {
                        TestNav(navController = navController)
                    }

                    composable(
                        route = Screens.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(navArgument(name = "noteId") {
                            type = NavType.IntType
                            defaultValue = -1
                        }, navArgument(name = "noteColor") {
                            type = NavType.IntType
                            defaultValue = -1
                        })
                    ) {
                        val color = it.arguments?.getInt("noteColor") ?: -1
                        AddEditNoteScreen(navController = navController, noteColor = color)
                    }
                }

            }
        }
    }
}
