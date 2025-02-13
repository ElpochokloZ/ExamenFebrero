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
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.example.proyectofebrero.ViewModel.VideoViewModel

class Video : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter // Asegúrate de tener un adaptador para videos
    private lateinit var videoViewModel: VideoViewModel
    private var exoPlayer: ExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        // Inicializar el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewVideo)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Inicializar el ViewModel
        videoViewModel = ViewModelProvider(this).get(VideoViewModel::class.java)

        // Configurar el adaptador
        videoViewModel.videos.observe(viewLifecycleOwner, Observer { videos ->
            videoAdapter = VideoAdapter(videos) { video ->
                playVideo(video.videoResId)
            }
            recyclerView.adapter = videoAdapter
        })

        return view
    }

    private fun playVideo(videoResId: Int) {
        // Liberar el ExoPlayer anterior si existe
        exoPlayer?.release()

        // Crear una nueva instancia de ExoPlayer
        exoPlayer = ExoPlayer.Builder(requireContext()).build()

        // Crear un MediaItem
        val mediaItem = MediaItem.fromUri(Uri.parse("asset:///your_video_file.mp4")) // Cambia esto según tu fuente de video
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