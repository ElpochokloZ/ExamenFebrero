package com.example.proyectofebrero.ViewModel

import android.icu.text.CaseMap.Title

data class Cancion(
    val title:String,
    val artist: String,
    val duration: String,
    val audioResId: Int
)
