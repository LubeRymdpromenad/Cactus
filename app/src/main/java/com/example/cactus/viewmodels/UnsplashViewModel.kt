package com.example.cactus.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


data class UnsplashViewState(
    val query: String = ""
)

@HiltViewModel
class UnsplashViewModel @Inject constructor(

) : ViewModel() {
    val viewState = mutableStateOf(UnsplashViewState())

    fun searchForMore(query: String?) {
        viewState.value = viewState.value.copy(query = query.orEmpty())
    }
}
