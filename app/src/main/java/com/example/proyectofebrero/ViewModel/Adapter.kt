package com.example.proyectofebrero.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofebrero.R
import com.example.proyectofebrero.Model.Cancion

class MusicAdapter(
    private val songs: List<Cancion>, // Lista de canciones
    private val onClick: (Cancion) -> Unit, // Función que se llama al hacer clic en un ítem
    private val onPlayClick: (Int) -> Unit // Cambiar a un Int para pasar el audioResId
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    inner class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songTitle: TextView = itemView.findViewById(R.id.musicTitle)
        val songArtist: TextView = itemView.findViewById(R.id.itemSongArtist)
        val songDuration: TextView = itemView.findViewById(R.id.itemSongDuration)
        val buttonMusic: Button = itemView.findViewById(R.id.button_music)

        init {
            // Configurar el clic en el botón de reproducción del item_music
            buttonMusic.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onPlayClick(songs[position].audioResId) // Llama a el audio de la lista con el audioResId
                }
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