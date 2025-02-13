package com.example.proyectofebrero.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofebrero.R

class AudioViewModel : ViewModel() {
    private val _songs = MutableLiveData<List<Cancion>>()
    val songs: LiveData<List<Cancion>> get() = _songs

    init {
        loadSongs() // Cargar las canciones al inicializar el ViewModel
    }

    private fun loadSongs() {
        // Aquí puedes cargar tus canciones, por ejemplo, desde un recurso o una API
        _songs.value = listOf(
            Cancion("Voy a llevarte pa pr", "Bad Bunny", "3:45", R.raw.audiobadbunny),
            Cancion("FATAL FANTASSY","Alvaro Diaz, Tainy","2:30",R.raw.audioalvarodiaz)
            // Agrega más canciones aquí
        )
    }
}