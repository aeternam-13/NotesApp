package com.aeternam.notesapp.feature_note.presentation.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aeternam.notesapp.feature_note.presentation.notes.components.NoteItem
import com.aeternam.notesapp.feature_note.presentation.notes.components.OrderSection
import com.aeternam.notesapp.feature_note.presentation.util.Screens
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(
    navController: NavController, viewModel: NotesViewModel = hiltViewModel()

) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(snackHostState) }, floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screens.AddEditNoteScreen.route)
            }

        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add note")
        }
    }, content = { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding).padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "NotesApp", style = MaterialTheme.typography.headlineLarge
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(NotesEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(imageVector = Icons.Default.Build, contentDescription = "Sort")
                }

            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    noteOrder = state.noteOrder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    onOrderChange = { noteOrder ->
                        viewModel.onEvent(NotesEvent.Order(noteOrder))
                    })

            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.notes) { note ->
                    NoteItem(
                        note = note, modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp)
                            .clickable {
                                navController.navigate(
                                    Screens.AddEditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}"
                                )
                            },

                        onDeleteClick = {
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            scope.launch {
                                val res = snackHostState.showSnackbar(
                                    "Note deleted", actionLabel = "Undo"
                                )

                                if (res == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NotesEvent.RestoreNote)
                                }


                            }
                        })

                }
            }

        }
    }

    )


}