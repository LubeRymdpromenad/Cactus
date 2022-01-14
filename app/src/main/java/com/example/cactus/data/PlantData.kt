package com.example.cactus.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantData(
    var plantId: String = "",
    var name: String = "",
    val description: String = "",
    val imageUrl: String = ""
): Parcelable
