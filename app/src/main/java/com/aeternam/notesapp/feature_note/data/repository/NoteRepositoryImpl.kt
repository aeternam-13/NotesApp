package com.aeternam.notesapp.feature_note.data.repository

import com.aeternam.notesapp.feature_note.data.data_source.NoteDao
import com.aeternam.notesapp.feature_note.domain.model.Note
import com.aeternam.notesapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao : NoteDao
) : NoteRepository{
    override fun getNotes(): Flow<List<Note>> {
        return  dao.getNotes()
    }

    override suspend fun getNoteByID(id: Int): Note? {
        return  dao.getNoteById(id)
    }

    override suspend fun insertNode(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}