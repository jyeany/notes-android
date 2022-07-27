package com.immaterial.notes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.immaterial.notes.models.Note
import com.immaterial.notes.viewmodels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesList(notesViewModel: NotesViewModel, navController: NavController) {
    val notesState = notesViewModel.notes.observeAsState()
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        notesState.value?.forEach {
            NoteCard(it, navController)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun NoteCard(note: Note, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(note.title)
                Text(note.body)
            }
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End) {
                Row {
                    Button(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = { navController.navigate("noteEdit") }
                    ) {
                        Text("Edit")
                    }
                    Button(
                        modifier = Modifier.padding(start = 4.dp),
                        onClick = {}
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}