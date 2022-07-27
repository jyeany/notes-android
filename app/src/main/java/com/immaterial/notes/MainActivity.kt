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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.immaterial.notes.models.Note
import com.immaterial.notes.screens.NoteEdit
import com.immaterial.notes.screens.NotesList
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
        ioScope.launch {
            val lstNotes = notesService.listNotes()
            notesViewModel.setNotes(lstNotes)
        }
        setContent {
            NotesApp(notesService, notesViewModel)
        }
    }
}

@Composable
fun NotesApp(notesService: NotesService, notesViewModel: NotesViewModel) {
    val navController = rememberNavController()
    NotesAndroidTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = "notesList"
            ) {
                composable("notesList") { NotesList(notesViewModel, navController) }
                composable("noteEdit") { NoteEdit() }
            }
        }
    }
}
