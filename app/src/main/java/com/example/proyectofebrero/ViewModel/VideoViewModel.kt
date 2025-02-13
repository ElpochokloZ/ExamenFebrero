package com.example.proyectofebrero.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofebrero.R

class VideoViewModel : ViewModel() {
    // MutableLiveData para almacenar la lista de videos
    private val _videos = MutableLiveData<List<Videos>>()
    val videos: LiveData<List<Videos>> get() = _videos

    init {
        // Inicializar la lista de videos
        loadVideos()
    }

    private fun loadVideos() {
        // Aquí puedes cargar los videos desde una fuente (API, base de datos, etc.)
        // Por ahora, vamos a usar una lista de videos de ejemplo
        val videoList = listOf(
            Videos(videoResId = R.raw.videogolf, title = "Video 1", description = "Descripción del Video 1", duration = "4:00")
            //INTRODUCIR MAS VIDEOS
        )

        // Actualizar el LiveData con la lista de videos
        _videos.value = videoList
    }
}