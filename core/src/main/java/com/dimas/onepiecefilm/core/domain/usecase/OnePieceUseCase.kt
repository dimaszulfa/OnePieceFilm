package com.dimas.onepiecefilm.core.domain.usecase

import com.dimas.onepiecefilm.core.source.Resource
import com.dimas.onepiecefilm.core.domain.model.OnePiece
import kotlinx.coroutines.flow.Flow

interface OnePieceUseCase {
    fun getAllItems(): Flow<Resource<List<OnePiece>>>
    fun getFavoriteItems(): Flow<List<OnePiece>>
    fun setFavoriteItems(items: OnePiece, state: Boolean)
}