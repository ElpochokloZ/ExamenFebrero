package com.example.proyectofebrero.View

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofebrero.R
import com.example.proyectofebrero.ViewModel.AudioViewModel
import com.example.proyectofebrero.ViewModel.Cancion
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class Audio : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var audioViewModel: AudioViewModel
    private var exoPlayer: ExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_audio, container, false)

        // Inicializar el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Inicializar el ViewModel
        audioViewModel = ViewModelProvider(this).get(AudioViewModel::class.java)

        // Configurar el adaptador
        audioViewModel.songs.observe(viewLifecycleOwner, Observer { songs ->
            musicAdapter = MusicAdapter(songs, { song ->
                // Manejar el clic en el ítem (opcional)
            }, { song ->
                // Manejar el clic en el botón de reproducción
                setSongDetails(song.audioResId) // Asegúrate de que audioResId sea el correcto
            })
            recyclerView.adapter = musicAdapter
        })

        return view
    }

    private fun setSongDetails(audioResId: Int) {
        // Liberar el ExoPlayer anterior si existe
        exoPlayer?.release()

        // Crear una nueva instancia de ExoPlayer
        exoPlayer = ExoPlayer.Builder(requireContext()).build()

        // Crear un MediaItem
        val mediaItem = MediaItem.fromUri(Uri.parse("android.resource://${requireContext().packageName}/raw/$audioResId"))

        // Configurar el ExoPlayer con el MediaItem
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.playWhenReady = true // Asegúrate de que el ExoPlayer comience a reproducir
    }

    override fun onPause() {
        super.onPause()
        exoPlayer?.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        exoPlayer?.release()
        exoPlayer = null
    }
}