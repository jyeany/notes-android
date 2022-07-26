package com.immaterial.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.immaterial.notes.models.Note
import com.immaterial.notes.services.NotesService
import com.immaterial.notes.ui.theme.NotesAndroidTheme
import com.immaterial.notes.viewmodels.NotesViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine

val job = Job()
val ioScope = CoroutineScope(Dispatchers.IO + job)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val notesViewModel = NotesViewModel()
        val notesService = NotesService()
        setContent {
            NotesAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
//                    NotesList(notesViewModel.notes.value)
                }
            }
        }
        ioScope.launch {
            notesService.findNote(1)
            val note = notesService.findNote(1)
            // TODO: try updating a ViewModel here
            println(note?.title)
        }
    }
}

@Composable
fun NotesList(notes: List<Note>?) {
    notes?.forEach {
        Text(it.title)
        Text(it.body)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesAndroidTheme {
        Greeting("Android")
    }
}