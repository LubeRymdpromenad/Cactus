package com.example.cactus.data

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantData(
    var id: String? = "",
    var name: String = "",
    val description: String = "",
    val imageUrl: String = ""
): Parcelable

fun PlantData.toJson(): String {
    return Uri.encode(Gson().toJson(this))
}

val PlantDataParamType: NavType<PlantData> = object : NavType<PlantData>(false) {
    override val name: String
        get() = "uniqueUser"

    override fun get(bundle: Bundle, key: String): PlantData? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): PlantData {
        return Gson().fromJson(value, object : TypeToken<PlantData>() {}.type)
    }

    override fun put(bundle: Bundle, key: String, value: PlantData) {
        bundle.putParcelable(key, value)
    }
}
