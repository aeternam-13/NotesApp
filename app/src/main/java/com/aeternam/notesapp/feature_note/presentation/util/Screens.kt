package com.aeternam.notesapp.feature_note.presentation.util

sealed class Screens(val route : String) {
    data object NotesScreen : Screens("notes_screen")
    data object AddEditNoteScreen : Screens("add_edit_note_screen")
}