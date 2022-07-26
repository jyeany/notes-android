package com.immaterial.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.immaterial.notes.models.Note
import com.immaterial.notes.services.NotesService
import com.immaterial.notes.ui.theme.NotesAndroidTheme
import com.immaterial.notes.viewmodels.NotesViewModel
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine

val job = Job()
val ioScope = CoroutineScope(Dispatchers.IO + job)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val notesViewModel = NotesViewModel()
        val notesService = NotesService()
        val noteText = mutableStateOf("N/A")
        setContent {
            NotesAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
//                    NotesList(notesViewModel.notes.value)
                    NoteText(noteText.value)
                }
            }
        }
        ioScope.launch {
            notesService.findNote(1)
            val note = notesService.findNote(1)
            noteText.value = note?.body!!
        }
    }
}

@Composable
fun NoteText(noteBody: String) {
    Text(noteBody)
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