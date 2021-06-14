package com.dimas.onepiecefilm.core.source.local
import com.dimas.onepiecefilm.core.source.local.dao.OnePieceDao
import com.dimas.onepiecefilm.core.source.local.entity.OnePieceEntity
import kotlinx.coroutines.flow.Flow


class LocalDataSource(
    private val onePieceDao: OnePieceDao
){

    fun getAllItem(): Flow<List<OnePieceEntity>> = onePieceDao.getAllItems()

    fun getFavoriteItem(): Flow<List<OnePieceEntity>> = onePieceDao.getFavoriteItems()

    suspend fun insertTourism(item: List<OnePieceEntity>) = onePieceDao.insertItem(item)

    fun setFavoriteTourism(item: OnePieceEntity, newState: Boolean) {
        item.isFavorite = newState
        onePieceDao.updateFavoriteItem(item)
    }
}