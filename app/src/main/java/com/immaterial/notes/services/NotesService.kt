package com.immaterial.notes.services

import com.immaterial.notes.models.Note
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*

class NotesService() {

    private val baseUrl = "http://10.0.2.2:3000"
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun listNotes(): List<Note> {
        val url = "${baseUrl}/notes"
        val response = client.request(url) {
            method = HttpMethod.Get
        }
        return response.body()
    }

    suspend fun findNote(id: Int): Note? {
        val url = "${baseUrl}/notes/${id}"
        return client.request(url).body()
    }

}