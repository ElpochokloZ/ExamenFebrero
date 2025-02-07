package com.example.proyectofebrero.View

import android.media.MediaPlayer
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

class Audio : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var audioViewModel: AudioViewModel
    private var mediaPlayer: MediaPlayer? = null

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
                setSongDetails(song.title, song.artist, song.duration, song.audioResId)
            }
            recyclerView.adapter = musicAdapter
        })

        return view
    }

    private fun setSongDetails(title: String, artist: String, duration: String, audioResId: Int) {
        // Liberar el MediaPlayer anterior si existe
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(requireContext(), audioResId)
        mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}