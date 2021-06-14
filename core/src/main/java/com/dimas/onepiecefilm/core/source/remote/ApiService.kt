package com.dimas.onepiecefilm.core.source.remote

import com.dimas.onepiecefilm.core.source.remote.response.OnePieceResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/v3/search/anime?q=One%20Piece")
    suspend fun getList(): OnePieceResponse
}