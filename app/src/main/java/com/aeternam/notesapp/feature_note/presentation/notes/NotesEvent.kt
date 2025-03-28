package com.aeternam.notesapp.feature_note.presentation.notes

import com.aeternam.notesapp.feature_note.domain.model.Note
import com.aeternam.notesapp.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder : NoteOrder) : NotesEvent()
    data class DeleteNote(val note : Note) : NotesEvent()
    data object RestoreNote : NotesEvent()
    data object ToggleOrderSection : NotesEvent()
}