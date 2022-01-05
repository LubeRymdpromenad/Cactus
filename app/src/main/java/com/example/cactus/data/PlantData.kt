package com.example.cactus.data

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantData(
    var id: String? = "",
    var name: String = "",
    val description: String = "",
    val imageUrl: String = ""
): Parcelable

class PlantDataParamType : NavType<PlantData>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): PlantData? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): PlantData {
        return Gson().fromJson(value, PlantData::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: PlantData) {
        bundle.putParcelable(key, value)
    }
}
