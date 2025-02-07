package com.example.proyectofebrero.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofebrero.R
import com.example.proyectofebrero.ViewModel.Cancion

class MusicAdapter(
    private val songs: List<Cancion>, // Lista de canciones
    private val onClick: (Cancion) -> Unit // Función que se llama al hacer clic en un ítem
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    inner class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songTitle: TextView = itemView.findViewById(R.id.musicTitle)
        val songArtist: TextView = itemView.findViewById(R.id.itemSongArtist)
        val songDuration: TextView = itemView.findViewById(R.id.itemSongDuration)

        init {
            itemView.setOnClickListener {
                onClick(songs[adapterPosition]) // Llamar a la función onClick con la canción correspondiente
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val song = songs[position]
        holder.songTitle.text = song.title
        holder.songArtist.text = song.artist
        holder.songDuration.text = song.duration
    }

    override fun getItemCount(): Int = songs.size
}