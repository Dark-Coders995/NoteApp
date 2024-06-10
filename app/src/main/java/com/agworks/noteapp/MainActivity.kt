package com.agworks.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.agworks.noteapp.data.NoteDataSource
import com.agworks.noteapp.screen.NoteScreen
import com.agworks.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    NoteScreen(
                        notes = NoteDataSource().loadNotes(),
                        onAddNote = {},
                        onRemoveNote = {})
                }
            }
        }
    }
}