package com.example.proyectofebrero.ViewModel

import android.util.Log
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
        val videoList = listOf(
            Videos(videoResId = R.raw.videogolf, title = "Video Golf", description = "Video sobre historia del golf", duration = "4:00")
            // Agrega más videos aquí
        )
        _videos.value = videoList
        Log.d("VideoViewModel", "Videos cargados: $videoList") // Agrega un log
    }
}