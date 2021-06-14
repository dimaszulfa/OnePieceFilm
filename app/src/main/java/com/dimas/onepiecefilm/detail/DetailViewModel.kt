package com.dimas.onepiecefilm.detail

import androidx.lifecycle.ViewModel
import com.dimas.onepiecefilm.core.domain.model.OnePiece
import com.dimas.onepiecefilm.core.domain.usecase.OnePieceUseCase

class DetailViewModel(private val onePieceUseCase: OnePieceUseCase):ViewModel() {
    fun setFavorite(items: OnePiece, newStatus:Boolean) = onePieceUseCase.setFavoriteItems(items, newStatus)
}