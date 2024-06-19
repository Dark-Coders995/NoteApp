package com.agworks.noteapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agworks.noteapp.R
import com.agworks.noteapp.components.NoteButton
import com.agworks.noteapp.components.NoteInputText
import com.agworks.noteapp.data.NoteDataSource
import com.agworks.noteapp.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
) {

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(6.dp)
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon"
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF329C89))

        )
        //Content
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NoteInputText(
                modifier = Modifier
                    .padding(
                        top = 9.dp,
                        bottom = 8.dp,
                    ),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                }
            )

            NoteInputText(
                modifier = Modifier
                    .padding(
                        top = 9.dp,
                        bottom = 8.dp,
                    ),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }
            )

            NoteButton(
                modifier = Modifier
                    .padding(
                        top = 9.dp,
                        bottom = 8.dp,
                    ),
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        /* TODO
                            1.  save and add to list
                        */
                        onAddNote(
                            Note(
                                title = title,
                                description = description
                            )
                        )
                        title = ""
                        description = ""
                    }
                })
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(10.dp)
        )
        LazyColumn {
            items(notes) { note ->
                NoteRow(
                    note = note,
                    onNoteClick = { })
            }
        }
    }
}


@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick: (Note) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 25.dp,
                )
            )
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        tonalElevation = 6.dp,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = modifier
                .clickable { }
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = note.entryTime.format(DateTimeFormatter.ofPattern("EEE, d MMM")).toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}
