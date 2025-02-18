package com.example.proyectofebrero

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofebrero.View.Audio
import com.example.proyectofebrero.View.Video
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuración del BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.botNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.audio -> {
                    showAudioFragment()
                    true
                }
                R.id.video -> {
                    showVideoFragment()
                    true
                }
                else -> false
            }
        }

        // Cargar el fragmento Audio en el contenedor
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, Audio())
                .commit()
        }
    }

    private fun showAudioFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, Audio())
            .addToBackStack(null)
            .commit()
    }

    private fun showVideoFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, Video())
            .addToBackStack(null)
            .commit()
    }
}