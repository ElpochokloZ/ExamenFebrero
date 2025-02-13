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
            musicAdapter = MusicAdapter(songs) { song ->
                setSongDetails(song.audioResId)
            }
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
        val mediaItem = MediaItem.fromUri(Uri.parse("asset:///your_audio_file.mp3")) // Cambia esto según tu fuente de audio
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.play()
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