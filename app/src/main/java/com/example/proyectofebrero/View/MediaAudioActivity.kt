package com.example.proyectofebrero.View

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.proyectofebrero.R

class MediaAudioActivity : AppCompatActivity() {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediaaudio) // Asegúrate de que este layout exista

        // Inicializar el PlayerView
        playerView = findViewById(R.id.player_viewaudio) // Asegúrate de que este ID coincida con tu layout

        // Recuperar el ID del audio
        val audioResId = intent.getIntExtra("AUDIO_RES_ID", -1)

        // Inicializar ExoPlayer
        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        // Crear un MediaItem
        if (audioResId != -1) {
            val uri = Uri.parse("android.resource://${packageName}/$audioResId")
            val mediaItem = MediaItem.fromUri(uri)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.playWhenReady = true // Comienza a reproducir automáticamente
        } else {
            // Manejo de error si el audioResId no es válido
            Log.e("MediaAudioActivity", "Audio resource ID is invalid.")
        }
    }

    override fun onPause() {
        super.onPause()
        player.pause() // Pausar el reproductor cuando la actividad está en pausa
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release() // Liberar el reproductor al destruir la actividad
    }
}