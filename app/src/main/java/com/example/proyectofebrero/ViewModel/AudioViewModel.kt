package com.example.proyectofebrero.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofebrero.Model.Cancion
import com.example.proyectofebrero.R

class AudioViewModel : ViewModel() {
    private val _songs = MutableLiveData<List<Cancion>>()
    val songs: LiveData<List<Cancion>> get() = _songs

    init {
        loadSongs() // Metodo para cargar las canciones al inicializar el ViewModel
    }

    private fun loadSongs() {
        // Aquí puedes cargar tus canciones de manera local
        _songs.value = listOf(
            Cancion("Voy a llevarte pa pr", "Bad Bunny", "3:45", R.raw.audiobadbunny),
            Cancion("FATAL FANTASSY","Alvaro Diaz, Tainy","2:30",R.raw.audioalvarodiaz)
            // Se pueden agregar mas canciones y hay que especificar artista, nombre de la cancion, es decir parametrizar los datos que requiera la data class Cancion
        )
    }
}