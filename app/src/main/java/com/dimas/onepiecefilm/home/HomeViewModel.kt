package com.dimas.onepiecefilm.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dimas.onepiecefilm.core.domain.usecase.OnePieceUseCase

class HomeViewModel(onePieceUseCase: OnePieceUseCase): ViewModel() {
    val items = onePieceUseCase.getAllItems().asLiveData()
}