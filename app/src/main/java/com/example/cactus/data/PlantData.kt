package com.example.cactus.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantData(
    open var id: String? = "",
    open var name: String = "",
    val description: String,
    val imageUrl: String
): Parcelable
