package com.example.proyectofebrero.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofebrero.R
import com.example.proyectofebrero.Model.Videos

class VideoAdapter(
    private val videoList: List<Videos>,
    private val onVideoClick: (Videos) -> Unit // Callback para manejar el clic en el item video
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.video_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.video_description)
        private val buttonVideo: Button = itemView.findViewById(R.id.button_playvideo)

        fun bind(video: Videos) {
            titleTextView.text = video.title
            descriptionTextView.text = video.description

            // Manejar el clic en el elemento
            buttonVideo.setOnClickListener {
                onVideoClick(video) // Llamar al callback con el video correspondiente
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false) // Asegúrate de tener este layout
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        holder.bind(video) // Llamar al método bind para enlazar los datos
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}