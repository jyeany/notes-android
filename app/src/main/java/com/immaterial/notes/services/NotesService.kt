package com.immaterial.notes.services

import com.immaterial.notes.models.Note
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

class NotesService() {

    private val baseUrl = "http://10.0.2.2:3000"

//    suspend fun listNotes() {
//        val response = client.request("http://localhost:8080/notes") {
//            method = HttpMethod.Get
//        }
//        val notes: List<Note> = response.body()
//        notesViewModel.setNotes(notes)
//    }

    suspend fun findNote(id: Int): Note? {
        val client = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                gson()
            }
        }
        val url = "${baseUrl}/notes/${id}"
        return client.request(url).body()
    }

}