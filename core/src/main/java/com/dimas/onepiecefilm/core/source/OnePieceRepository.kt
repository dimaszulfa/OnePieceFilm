package com.dimas.onepiecefilm.core.source

import com.dimas.onepiecefilm.core.source.local.LocalDataSource
import com.dimas.onepiecefilm.core.source.remote.ApiResponse
import com.dimas.onepiecefilm.core.source.remote.RemoteDataSource
import com.dimas.onepiecefilm.core.source.remote.response.OnePieceResultResponse
import com.dimas.onepiecefilm.core.domain.model.OnePiece
import com.dimas.onepiecefilm.core.domain.repository.IOnePieceRepository
import com.dimas.onepiecefilm.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class OnePieceRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IOnePieceRepository{
    override fun getAllItems(): Flow<Resource<List<OnePiece>>> =
        object : NetworkBoundResource<List<OnePiece>, List<OnePieceResultResponse>>() {
            override fun loadFromDB(): Flow<List<OnePiece>> {
                return localDataSource.getAllItem().map{DataMapper.mapEntitiesToDomain(it)}
            }

            override fun shouldFetch(data: List<OnePiece>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<OnePieceResultResponse>>> =
                remoteDataSource.getList()


            override suspend fun saveCallResult(data: List<OnePieceResultResponse>) {
                val itemList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(itemList)
            }
        }.asFlow()

    override fun getFavoriteItems(): Flow<List<OnePiece>> {
        return localDataSource.getFavoriteItem().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteItems(items: OnePiece, state: Boolean) {
        val onePieceEntity = DataMapper.mapDomainToEntity(items)

        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteTourism(onePieceEntity, state)
        }

    }
}