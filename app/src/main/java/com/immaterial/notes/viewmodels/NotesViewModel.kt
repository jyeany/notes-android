package com.immaterial.notes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.immaterial.notes.models.Note

class NotesViewModel : ViewModel() {

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData(listOf())

    val notes: LiveData<List<Note>>
        get() = _notes

    fun setNotes(value: List<Note>) {
        _notes.postValue(value)
    }

}