package com.agworks.noteapp.data

import com.agworks.noteapp.model.Note

class NoteDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Note 1", description = "Hello 1")
        )
    }
}