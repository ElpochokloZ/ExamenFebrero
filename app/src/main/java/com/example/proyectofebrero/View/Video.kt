package com.example.proyectofebrero.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofebrero.R
import com.example.proyectofebrero.ViewModel.VideoViewModel

class Video : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var videoViewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        // Inicializar el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewVideo)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Inicializar el ViewModel
        videoViewModel = ViewModelProvider(this).get(VideoViewModel::class.java)

        // Configurar el adaptador
        videoViewModel.videos.observe(viewLifecycleOwner, Observer { videos ->
            videoAdapter = VideoAdapter(videos) { video ->
                goToPlayerPage(video.videoResId) // Navegar a la actividad del reproductor
            }
            recyclerView.adapter = videoAdapter
        })

        return view
    }

    private fun goToPlayerPage(videoResId: Int) {
        if (videoResId != -1) { // Asegúrate de que el ID sea válido
            val intent = Intent(requireContext(), MediaPlayerActivity::class.java)
            intent.putExtra("VIDEO_RES_ID", videoResId)
            startActivity(intent)
        } else {
            // Manejo de error si el videoResId no es válido
            Log.e("VideoFragment", "Invalid video resource ID: $videoResId")
        }
    }

}