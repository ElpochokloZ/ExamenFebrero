package com.example.proyectofebrero

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofebrero.View.Audio
import com.example.proyectofebrero.View.Video

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Cargar el fragmento Audio en el contenedor
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, Audio()) // Asegúrate de que este ID exista en tu layout
                .commit()
        }

    }

    private fun showAudioFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, Audio())
            .addToBackStack(null) // Agrega a la pila de retroceso
            .commit()
    }

    private fun showVideoFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, Video())
            .addToBackStack(null) // Agrega a la pila de retroceso
            .commit()
    }
}