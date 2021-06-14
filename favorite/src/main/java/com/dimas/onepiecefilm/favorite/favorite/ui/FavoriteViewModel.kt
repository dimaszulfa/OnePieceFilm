package com.dimas.onepiecefilm.favorite.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dimas.onepiecefilm.core.domain.usecase.OnePieceUseCase

class FavoriteViewModel(onePieceUseCase: OnePieceUseCase):ViewModel() {
    val favoriteItems = onePieceUseCase.getFavoriteItems().asLiveData()
}
