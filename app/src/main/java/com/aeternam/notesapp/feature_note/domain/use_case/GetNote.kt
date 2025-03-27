package com.aeternam.notesapp.feature_note.domain.use_case

import com.aeternam.notesapp.feature_note.domain.model.Note
import com.aeternam.notesapp.feature_note.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id : Int) : Note?{
        return repository.getNoteByID(id)
    }
}