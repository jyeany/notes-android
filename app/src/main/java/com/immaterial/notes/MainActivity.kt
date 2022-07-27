package com.immaterial.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.immaterial.notes.models.Note
import com.immaterial.notes.services.NotesService
import com.immaterial.notes.ui.theme.NotesAndroidTheme
import com.immaterial.notes.viewmodels.NotesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

val job = Job()
val ioScope = CoroutineScope(Dispatchers.IO + job)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notesViewModel = NotesViewModel()
        val notesService = NotesService()
        val noteText = mutableStateOf("N/A")
        setContent {
            NotesAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesList(notesViewModel)
                }
            }
        }
        ioScope.launch {
            val lstNotes = notesService.listNotes()
            notesViewModel.setNotes(lstNotes)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesList(notesViewModel: NotesViewModel) {
    val notesState = notesViewModel.notes.observeAsState()
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        notesState.value?.forEach {
            NoteCard(it)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun NoteCard(note: Note) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(note.title)
            Text(note.body)
        }
    }
}

@Composable
fun NoteText(noteBody: String) {
    Text(noteBody)
}
