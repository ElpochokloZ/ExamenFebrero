package com.example.proyectofebrero.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofebrero.R
import com.example.proyectofebrero.ViewModel.AudioViewModel

class Audio : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var audioViewModel: AudioViewModel
    private var exoPlayer: ExoPlayer? = null
    private lateinit var playerView: PlayerView

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
            }, { audioResId ->
                // Manejar el clic en el botón de reproducción
                goToPlayerAudioPage(audioResId) // Navegar a la actividad de audio
            })
            recyclerView.adapter = musicAdapter
        })

        return view
    }

    private fun goToPlayerAudioPage(audioResId: Int) {
        val intent = Intent(requireContext(), MediaAudioActivity::class.java)
        intent.putExtra("AUDIO_RES_ID", audioResId) // Pasar el ID del audio
        startActivity(intent)
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