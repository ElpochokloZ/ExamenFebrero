package com.example.proyectofebrero.View

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem
import com.example.proyectofebrero.databinding.ActivityMediaBinding

class MediaPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMediaBinding
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar el ID del video
        val videoResId = intent.getIntExtra("VIDEO_RES_ID", -1)

        // Inicializar ExoPlayer
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player

        // Crear el MediaItem usando el videoResId
        if (videoResId != -1) {
            val mediaItemUri = "android.resource://${packageName}/$videoResId"
            Log.d("MediaPlayerActivity", "MediaItem URI: $mediaItemUri") // Log para verificar la URI
            val mediaItem = MediaItem.fromUri(mediaItemUri)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        } else {
            // Manejo de error si el videoResId no es válido
            Log.e("MediaPlayerActivity", "Video resource ID is invalid.")
        }
    }

    override fun onPause() {
        super.onPause()
        player.pause() // Pausar el reproductor cuando la actividad está en pausa
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release() // Liberar el reproductor
    }
}