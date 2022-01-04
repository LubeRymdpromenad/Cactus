package com.example.cactus.common

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


inline fun <reified T> Context.jsonToClass(@RawRes resourceId: Int): T {
    val itemType = object : TypeToken<T>() {}.type
    return Gson().fromJson(
        resources.openRawResource(resourceId).bufferedReader().use { it.readText() },
        itemType
    )
}
